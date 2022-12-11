package by.bsuir.client.controllers;


import by.pojo.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static by.bsuir.client.service.AlertImp.showAlert;
import static by.bsuir.client.ClientApp.connection;

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

        tfMobNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfMobNum.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        refreshTable();

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

    public void insertClientBtnClick(ActionEvent actionEvent) {
        if (!tfName.getText().equals("") && !tfSurname.getText().equals("") && !tfPatronymic.getText().equals("") && !tfMobNum.getText().equals("")) {
           Client newClient = new Client(tfName.getText(), tfSurname.getText(), tfPatronymic.getText(), tfMobNum.getText());
            boolean flag = connection.createClient(newClient);
            if(flag == true){
                showAlert("", "Клиент добавлен успешно", Alert.AlertType.INFORMATION);
            }
            tfName.clear();
            tfSurname.clear();
            tfPatronymic.clear();
            tfMobNum.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, зааолните все поля", Alert.AlertType.WARNING);
        }
        refreshTable();
    }
    public void refreshTable() {
        ArrayList<Client> clientArrayList = connection.getAllClients();
        tvClients.setItems(FXCollections.observableList(clientArrayList));
    }

    public void deleteClientBtnClick(ActionEvent actionEvent) {
        if (!tfName.getText().equals("") && !tfSurname.getText().equals("") && !tfPatronymic.getText().equals("") && !tfMobNum.getText().equals("")) {
            boolean flag = connection.deleteClient(tvClients.getSelectionModel().getSelectedItem().getClientID());
            if(flag == true){
                showAlert("", "Запись о клиенте удалена успешно", Alert.AlertType.INFORMATION);
            }
            tfName.clear();
            tfSurname.clear();
            tfPatronymic.clear();
            tfMobNum.clear();
            refreshTable();
        }
        else {
            showAlert("Ошибка ввода", "Пожалуйста, зааолните все поля", Alert.AlertType.WARNING);
        }
    }

    public void updateClientBtnClick(ActionEvent actionEvent) {
        if (!tfName.getText().equals("") && !tfSurname.getText().equals("") && !tfPatronymic.getText().equals("") && !tfMobNum.getText().equals("")) {
            Client client = new Client(tfName.getText(), tfSurname.getText(), tfPatronymic.getText(), tfMobNum.getText(), tvClients.getSelectionModel().getSelectedItem().getClientID());
            boolean flag  = connection.updateClient(client);
            if(flag == true){
                showAlert("", "Запись о клиенте удалена успешно", Alert.AlertType.INFORMATION);
            }
            tfName.clear();
            tfSurname.clear();
            tfPatronymic.clear();
            tfMobNum.clear();
            refreshTable();
        }
        else {
            showAlert("Ошибка ввода", "Пожалуйста, зааолните все поля", Alert.AlertType.WARNING);
        }
    }
}
