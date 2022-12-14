package by.bsuir.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ViewServerLogController {
    @FXML
    private TextArea taLogs;

    public void initialize() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("C:\\workspace\\git\\it-education\\courseworkSTO\\Server\\logs\\Server.log");

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String st;
            while ((st = bufferedReader.readLine()) != null) {
                taLogs.appendText(st + "\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
