package by.bsuir.client.controllers;

import by.pojo.Client;
import by.pojo.Operation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.service.AlertImp.showAlert;

public class OperationController {
    @FXML
    private Button btnOperationDelete;

    @FXML
    private Button btnOperationInsert;

    @FXML
    private Button btnOperationUpdate;

    @FXML
    private TableColumn<Operation, Integer> colOperationCost;

    @FXML
    private TableColumn<Operation, Integer> colOperationId;

    @FXML
    private TableColumn<Operation, String> colOperationName;

    @FXML
    private TextField tfOperationCost;

    @FXML
    private TextField tfOperationName;

    @FXML
    private TableView<Operation> tvOperations;

    public void initialize() {
        colOperationId.setResizable(false);
        colOperationId.setSortable(false);
        colOperationId.setStyle("-fx-alignment: center");
        colOperationId.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("operationId"));

        colOperationName.setResizable(false);
        colOperationName.setSortable(false);
        colOperationName.setStyle("-fx-alignment: center");
        colOperationName.setCellValueFactory(new PropertyValueFactory<Operation, String>("name"));

        colOperationCost.setResizable(false);
        colOperationCost.setSortable(false);
        colOperationCost.setStyle("-fx-alignment: center");
        colOperationCost.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("cost"));

        tfOperationCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfOperationCost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        refreshTable();
    }

    public void refreshTable() {
        ArrayList<Operation> operationArrayList = connection.getAllOperations();
        tvOperations.setItems(FXCollections.observableList(operationArrayList));
    }

    @FXML
    void deleteOperationBtnClick(ActionEvent event) {
        if (!tfOperationCost.getText().equals("") && !tfOperationName.getText().equals("")) {
            boolean flag = connection.deleteOperation(tvOperations.getSelectionModel().getSelectedItem().getOperationId());
            if (flag == true) {
                showAlert("", "Запись об услуге удалена успешно", Alert.AlertType.INFORMATION);
            }
            tfOperationName.clear();
            tfOperationCost.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
    }


    @FXML
    void insertOperationBtnClick(ActionEvent event) {
        if (!tfOperationCost.getText().equals("") && !tfOperationName.getText().equals("")) {
            Operation operation = new Operation(tfOperationName.getText(), Integer.parseInt(tfOperationCost.getText()));
            boolean flag = connection.createOperation(operation);
            if (flag == true) {
                showAlert("", "Услуга добавлена успешно", Alert.AlertType.INFORMATION);
            }
            tfOperationName.clear();
            tfOperationCost.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
        refreshTable();
    }

    @FXML
    void updateOperationBtnClick(ActionEvent event) {
        if (!tfOperationCost.getText().equals("") && !tfOperationName.getText().equals("")) {
            Operation operation = new Operation(tvOperations.getSelectionModel().getSelectedItem().getOperationId(), tfOperationName.getText(), Integer.parseInt(tfOperationCost.getText()));
            boolean flag = connection.updateOperation(operation);
            if (flag == true) {
                showAlert("", "Запись об услуге обновлена успешно", Alert.AlertType.INFORMATION);
            }
            tfOperationName.clear();
            tfOperationCost.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
    }


    public void displaySelectedOperationFromTable(MouseEvent mouseEvent) {
        if (tvOperations.getSelectionModel().getSelectedItem() != null) {
            Operation selectedOperation = tvOperations.getSelectionModel().getSelectedItem();
            tfOperationName.setText(selectedOperation.getName());
            tfOperationCost.setText(String.valueOf(selectedOperation.getCost()));
        }
    }
}
