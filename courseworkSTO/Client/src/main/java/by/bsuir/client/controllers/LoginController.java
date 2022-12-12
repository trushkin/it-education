package by.bsuir.client.controllers;

import by.pojo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.service.AlertImp.showAlert;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;
public static String role;


    public void onBtnLoginClick(ActionEvent actionEvent) throws IOException, SQLException {
        if (validateLogin()) {
            FXMLLoader loader = new FXMLLoader();
            URL temp;
            if(role.equals("Manager") || role.equals("Receptionist"))
            {
                temp = getClass().getResource("/by/bsuir/client/MainForm.fxml");
            }
            else {
                temp = getClass().getResource("/by/bsuir/client/SysAdminForm.fxml");
            }
            loader.setLocation(temp);
            Parent root = loader.load();
            Stage stage = (Stage) tfUsername.getScene().getWindow();
            stage.close();
            //создание нового окна
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.WINDOW_MODAL);
            //блокировка главного окна
            newStage.initOwner(this.tfUsername.getScene().getWindow());
            //ожидание закрытия окна
            newStage.setTitle("АСУ Автосервис");
            newStage.showAndWait();
        }

    }


    public void OnBtnSignUpClick(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        URL temp = getClass().getResource("/by/bsuir/client/sign-up.fxml");

        loader.setLocation(temp);
        Parent root = loader.load();

        //создание нового окна
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        //блокировка главного окна
        stage.initOwner(this.btnLogin.getScene().getWindow());
        //ожидание закрытия окна
        stage.showAndWait();
    }

    public static String md5Apache(String password) {
        return DigestUtils.md5Hex(password);
    }

    public boolean validateLogin() throws SQLException {
        if (!tfUsername.getText().equals("") && !tfPassword.getText().equals("")) {
            User user = connection.login(new User(tfUsername.getText(), md5Apache(tfPassword.getText())));
            if (user != null) {
                role = user.getRole();
                return true;
            } else {
                showAlert("Пользователь не найден!", "Проверьте введенные значения", Alert.AlertType.WARNING);
                return false;
            }
        }
        showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        return false;
    }

}
