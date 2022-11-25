package by.bsuir.client;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import by.pojo.Client;

import java.io.IOException;
import java.util.ArrayList;

public class ClientController {

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

    public void displaySelectedClientFromTable(MouseEvent mouseEvent) {
    }

    public void insertClientBtnClick(ActionEvent actionEvent) {
    }

    public void deleteClientBtnClick(ActionEvent actionEvent) {
    }

    public void updateClientBtnClick(ActionEvent actionEvent) {
    }
    public static final int PORT = 1022;
    public static final String IP = "localhost";
    ClientConnectionModule connection = new ClientConnectionModule(IP, PORT);

    public void initialize() {
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
        try {
            connection.connectToServer();
            ArrayList<Client> clientArrayList = connection.getAllClients();
            tvClients.setItems(FXCollections.observableList(clientArrayList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
