package by.bsuir.client.controllers;

import by.pojo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.bsuir.client.service.AlertImp.showAlert;
import static by.bsuir.client.ClientApp.connection;

public class SignUpController {
    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    public static String md5Apache(String password) {
        return DigestUtils.md5Hex(password);
    }

    public void OnBtnSignUpClick(ActionEvent actionEvent) throws IOException, SQLException {
        if (!tfUsername.getText().equals("") && !tfPassword.getText().equals("")) {
            User newUser = new User(tfUsername.getText(), md5Apache(tfPassword.getText()), "Receptionist");
            boolean flag = connection.createUser(newUser);
            if (flag == true) {
                showAlert("", "Пользователь создан успешно", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) tfUsername.getScene().getWindow();
                stage.close();
            }
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
    }
}

