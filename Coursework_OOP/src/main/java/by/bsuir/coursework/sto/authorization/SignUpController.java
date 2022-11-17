package by.bsuir.coursework.sto.authorization;

import by.bsuir.coursework.sto.application.DatabaseConnectionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    public void OnBtnSignUpClick(ActionEvent actionEvent) throws IOException, SQLException {
        String selectSql = "INSERT INTO Users(Login, Password) VALUES ('" + tfUsername.getText() + "','"
                + tfPassword.getText() + "')";
        PreparedStatement statement = DatabaseConnectionProvider.getConnection().prepareStatement(selectSql);
        int code = statement.executeUpdate();
      if (code == 1) {
          Stage stage = (Stage)tfUsername.getScene().getWindow();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/by/bsuir/coursework/sto/login.fxml"));
          Scene scene = null;
          try {
              scene = new Scene(loader.load());
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          stage.setScene(scene);
          stage.show();
//        FXMLLoader loader = new FXMLLoader();
//        URL temp = getClass().getResource("/by/bsuir/coursework/sto/login.fxml");
//        loader.setLocation(temp);
//        Parent root = loader.load();
//        Stage stage = (Stage) tfUsername.getScene().getWindow();
//        stage.close();
//        //создание нового окна
//        Stage newStage = new Stage();
//        newStage.setScene(new Scene(root));
//        newStage.initModality(Modality.WINDOW_MODAL);
//        //блокировка главного окна
//        newStage.initOwner(this.tfUsername.getScene().getWindow());
//        //ожидание закрытия окна
//        newStage.showAndWait();
    }
    }
}
