package by.bsuir.coursework.sto.authorization;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
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

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;



    public void onBtnLoginClick(ActionEvent actionEvent) throws IOException, SQLException {
       if(validateLogin()) {
            FXMLLoader loader = new FXMLLoader();
            URL temp = getClass().getResource("/by/bsuir/coursework/sto/ScheduleForm.fxml");

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
            newStage.showAndWait();
        }

    }


    public void OnBtnSignUpClick(ActionEvent actionEvent) throws IOException, SQLException {

        Stage stage = (Stage)tfUsername.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by/bsuir/coursework/sto/sign-up.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }
    public boolean validateLogin() throws SQLException {
        //String selectSql = "SELECT count(1) FROM Users WHERE Login = '23'";
       String selectSql = "SELECT * FROM Users WHERE Login = '" + tfUsername.getText() + "' AND Password = '" + tfPassword.getText() +"'";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        ResultSet code = statement.executeQuery();

        if (!code.isBeforeFirst()) {
           showAlert("User not found, try again!", "User not found, try again!");
            return false;
        }
        return true;
    }
    private void showAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
        });
    }
}
