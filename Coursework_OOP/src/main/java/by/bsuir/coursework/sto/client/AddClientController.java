package by.bsuir.coursework.sto.client;

import by.bsuir.coursework.sto.application.AlertImp;
import by.bsuir.coursework.sto.client.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddClientController implements Initializable {

    @FXML
    private Button btnClientDelete;

    @FXML
    private Button btnClientInsert;

    @FXML
    private TableColumn<Client, Integer> colClientID;

    @FXML
    private TableColumn<Client, String> colClientName;

    @FXML
    private TableColumn<Client, String> colClientPatronymic;

    @FXML
    private TableColumn<Client, String> colClientSurname;

    @FXML
    private TableColumn<Client, String> colMobNum;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPatronymic;

    @FXML
    private TextField tfSurname;

    @FXML
    private TextField tfMobNum;

    @FXML
    private TableView<Client> tvClients;

    @FXML
    private Label lblErrorClient;

    public static Logger logger = LogManager.getLogger();
    ObservableList<Client> clientObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayClients();
        // заполнение textField только цифрами
        tfMobNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfMobNum.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void displayClients() {
        try {
            clientObservableList = FXCollections.observableArrayList(Client.loadClientsInForm());
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            //System.out.println("Ошибка SQL !");
            logger.error("Displaying clients error");
            return;
        }


        colClientID.setResizable(false);
        colClientID.setSortable(false);
        colClientID.setStyle("-fx-alignment: center");
        colClientID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientID"));

        colClientName.setResizable(false);
        colClientName.setSortable(false);
        colClientName.setStyle("-fx-alignment: center");
        colClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));

        colClientSurname.setResizable(false);
        colClientSurname.setSortable(false);
        colClientSurname.setStyle("-fx-alignment: center");
        colClientSurname.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));

        colClientPatronymic.setResizable(false);
        colClientPatronymic.setSortable(false);
        colClientPatronymic.setStyle("-fx-alignment: center");
        colClientPatronymic.setCellValueFactory(new PropertyValueFactory<Client, String>("patronymic"));

        colMobNum.setResizable(false);
        colMobNum.setSortable(false);
        colMobNum.setStyle("-fx-alignment: center");
        colMobNum.setCellValueFactory(new PropertyValueFactory<Client, String>("mobNum"));

        tvClients.setItems(clientObservableList);
    }

    @FXML
    void insertClientBtnClick(ActionEvent event) {
        if (!Objects.equals(tfMobNum.getText(), "") && !Objects.equals(tfName.getText(), "") && !Objects.equals(tfSurname.getText(), "") && !Objects.equals(tfPatronymic.getText(), "")) {
            try {
                Client.addClient(tfName.getText(),tfSurname.getText(), tfPatronymic.getText(), tfMobNum.getText());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                //System.out.println("Ошибка SQL !");
                logger.error("Inserting client error");
            }
            tfName.clear();
            tfSurname.clear();
            tfPatronymic.clear();
            tfMobNum.clear();
            displayClients();

        } else AlertImp.showAlert("Добавление клиента", "Не все поля заполнены!");
    }

    public void displaySelectedClientFromTable(MouseEvent mouseEvent) {
        if (tvClients.getSelectionModel().getSelectedItem() != null) {
            Client selectedClient = tvClients.getSelectionModel().getSelectedItem();
            tfName.setText(selectedClient.getName());
            tfSurname.setText(selectedClient.getSurname());
            tfPatronymic.setText(selectedClient.getPatronymic());
            tfMobNum.setText(selectedClient.getMobNum());
        }
    }

    public void deleteClientBtnClick(ActionEvent actionEvent) {
        if (!Objects.equals(tfMobNum.getText(), "") && !Objects.equals(tfName.getText(), "") && !Objects.equals(tfSurname.getText(), "") && !Objects.equals(tfPatronymic.getText(), "") && tvClients.getSelectionModel().getSelectedItem() != null) {
            Client selectedClient = tvClients.getSelectionModel().getSelectedItem();
            try {
                Client.deleteClient(selectedClient.getClientID());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                logger.error("Deleting client error");
            }
            tfName.clear();
            tfSurname.clear();
            tfPatronymic.clear();
            tfMobNum.clear();
            displayClients();
        } else AlertImp.showAlert("Удаление клиента", "Не все поля заполнены!");
    }

    public void updateClientBtnClick(ActionEvent actionEvent) {
        if (!Objects.equals(tfMobNum.getText(), "") && !Objects.equals(tfName.getText(), "") && !Objects.equals(tfSurname.getText(), "") && !Objects.equals(tfPatronymic.getText(), "") && tvClients.getSelectionModel().getSelectedItem() != null) {
            Client selectedClient = tvClients.getSelectionModel().getSelectedItem();
            try {
                Client.updateClient(selectedClient.getClientID(), tfName.getText(), tfSurname.getText(), tfPatronymic.getText(), tfMobNum.getText());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
               // System.out.println("Ошибка SQL !");
                logger.error("Updating client error");
            }
            tfName.clear();
            tfSurname.clear();
            tfPatronymic.clear();
            tfMobNum.clear();
            displayClients();
        } else AlertImp.showAlert("Редактирование клиента", "Не все поля заполнены!");;
    }
}





