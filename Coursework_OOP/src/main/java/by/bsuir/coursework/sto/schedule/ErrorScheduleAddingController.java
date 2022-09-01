package by.bsuir.coursework.sto.schedule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ErrorScheduleAddingController {
    @FXML
    private Button btnOk;

    @FXML
    void okBtnClick(ActionEvent event) {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }
}
