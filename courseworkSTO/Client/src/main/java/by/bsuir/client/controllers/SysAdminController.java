package by.bsuir.client.controllers;

import by.pojo.Operation;
import by.pojo.Parts;
import by.pojo.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.controllers.LoginController.md5Apache;
import static by.bsuir.client.service.AlertImp.showAlert;

public class SysAdminController {
    @FXML
    private Button btnDeleteUser;

    @FXML
    private Button btnInsertUser;

    @FXML
    private Button btnUpdateUser;

    @FXML
    private Button btnViewServerLogs;

    @FXML
    private ComboBox<String> cbRole;

    @FXML
    private TableColumn<User, Integer> colUserId;

    @FXML
    private TableColumn<User, String> colUserLogin;

    @FXML
    private TableColumn<User, String> colUserRole;

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPassword;

    @FXML
    private TableView<User> tvUsers;
    public void initialize() {
        colUserId.setResizable(false);
        colUserId.setSortable(false);
        colUserId.setStyle("-fx-alignment: center");
        colUserId.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));

        colUserLogin.setResizable(false);
        colUserLogin.setSortable(false);
        colUserLogin.setStyle("-fx-alignment: center");
        colUserLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));

        colUserRole.setResizable(false);
        colUserRole.setSortable(false);
        colUserRole.setStyle("-fx-alignment: center");
        colUserRole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));

        cbRole.setItems(rolesList());
        refreshTable();
    }
    public void refreshTable() {
        ArrayList<User> users = connection.getAllUsers();
        tvUsers.setItems(FXCollections.observableList(users));
    }
    @FXML
    void onDeleteBtnClick(ActionEvent event) {
        if (tvUsers.getSelectionModel().getSelectedItem()!=null) {
            boolean flag = connection.deleteUser(tvUsers.getSelectionModel().getSelectedItem().getUserID());
            if (flag == true) {
                showAlert("", "Пользователь удален успешно", Alert.AlertType.INFORMATION);
            }
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
        refreshTable();
    }

    @FXML
    void onInsertBtnClick(ActionEvent event) {
        if (!tfLogin.getText().equals("") && !tfPassword.getText().equals("") && cbRole.getValue() != null) {
            User newUser = new User(tfLogin.getText(), md5Apache(tfPassword.getText()), cbRole.getValue());
            boolean flag = connection.createUser(newUser);
            if (flag == true) {
                showAlert("", "Пользователь создан успешно", Alert.AlertType.INFORMATION);
            }
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
        refreshTable();
    }

    @FXML
    void onUpdateBtnClick(ActionEvent event) {
        if (!tfLogin.getText().equals("") && !tfPassword.getText().equals("") && cbRole.getValue() != null && tvUsers.getSelectionModel().getSelectedItem()!=null) {
            User user = new User(tvUsers.getSelectionModel().getSelectedItem().getUserID(), tfLogin.getText(), md5Apache(tfPassword.getText()), cbRole.getValue());
            boolean flag = connection.updateUser(user);
            if (flag == true) {
                showAlert("", "Запись о запчасти обновлена успешно", Alert.AlertType.INFORMATION);
            }
            tfLogin.clear();
            tfPassword.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void onViewServerLogsBtnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/ViewServerLogForm.fxml");
        loader.setLocation(temp);
        Parent root = loader.load();
        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Логи сервера");
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.tfPassword.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
    }
    ObservableList rolesList(){
        List<String> list = new ArrayList<>();
        list.add("Manager");
        list.add("Receptionist");
        return FXCollections.observableArrayList(list);
    }
}
