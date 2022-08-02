package by.bsuir.coursework.sto.gui;

import by.bsuir.coursework.sto.models.Client;
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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddClientController implements Initializable {

    @FXML
    private Button btnClientDelete;

    @FXML
    private Button btnClientInsert;

    @FXML
    private TableColumn<Client, Integer> colClientID;

    @FXML
    private TableColumn<Client, String> colFIO;

    @FXML
    private TableColumn<Client, String> colMobNum;

    @FXML
    private TextField tfFIO;

    @FXML
    private TextField tfMobNum;

    @FXML
    private TableView<Client> tvClients;

    @FXML
    private Label lblErrorClient;


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
            clientObservableList = Client.loadClientsInForm();
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
            return;
        }


        colClientID.setResizable(false);
        colClientID.setSortable(false);
        colClientID.setStyle("-fx-alignment: center");
        colClientID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientID"));

        colFIO.setResizable(false);
        colFIO.setSortable(false);
        colFIO.setStyle("-fx-alignment: center");
        colFIO.setCellValueFactory(new PropertyValueFactory<Client, String>("FIO"));

        colMobNum.setResizable(false);
        colMobNum.setSortable(false);
        colMobNum.setStyle("-fx-alignment: center");
        colMobNum.setCellValueFactory(new PropertyValueFactory<Client, String>("mobNum"));

        tvClients.setItems(clientObservableList);
    }

    @FXML
    void insertClientBtnClick(ActionEvent event) {
        if (tfMobNum.getText() != "" && tfFIO.getText() != "") {
            try {
                Client.addClient(tfFIO.getText(), tfMobNum.getText());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                System.out.println("Ошибка SQL !");
            }
            tfFIO.clear();
            tfMobNum.clear();
            displayClients();
            lblErrorClient.setText("");
        } else lblErrorClient.setText("Не все поля заполнены!");
    }

    public void displaySelectedClientFromTable(MouseEvent mouseEvent) {
        if (tvClients.getSelectionModel().getSelectedItem() != null) {
            Client selectedClient = tvClients.getSelectionModel().getSelectedItem();
            tfFIO.setText(selectedClient.getFIO());
            tfMobNum.setText(selectedClient.getMobNum());
        }
    }

    public void deleteClientBtnClick(ActionEvent actionEvent) {
        if (tfMobNum.getText() != "" && tfFIO.getText() != "" && tvClients.getSelectionModel().getSelectedItem() != null) {
            Client selectedClient = tvClients.getSelectionModel().getSelectedItem();
            try {
                Client.deleteClient(selectedClient.getClientID());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                System.out.println("Ошибка SQL !");
            }
            tfFIO.clear();
            tfMobNum.clear();
            displayClients();
            lblErrorClient.setText("");
        } else lblErrorClient.setText("Не все поля заполнены!");
    }

    public void updateClientBtnClick(ActionEvent actionEvent) {
        if (tfMobNum.getText() != "" && tfFIO.getText() != "" && tvClients.getSelectionModel().getSelectedItem() != null) {
            Client selectedClient = tvClients.getSelectionModel().getSelectedItem();
            try {
                Client.updateClient(selectedClient.getClientID(), tfFIO.getText(), tfMobNum.getText());
            } catch (SQLException e) {
                e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
                System.out.println("Ошибка SQL !");
            }
            tfFIO.clear();
            tfMobNum.clear();
            displayClients();
            lblErrorClient.setText("");
        } else lblErrorClient.setText("Не все поля заполнены!");
    }
}





