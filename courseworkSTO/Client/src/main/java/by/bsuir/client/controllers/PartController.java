package by.bsuir.client.controllers;

import by.pojo.Operation;
import by.pojo.Parts;
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

public class PartController {
    @FXML
    private Button btnPartDelete;

    @FXML
    private Button btnPartInsert;

    @FXML
    private Button btnPartUpdate;

    @FXML
    private TextField tfPartCost;

    @FXML
    private TextField tfPartName;

    @FXML
    private TextField tfProducingCountry;
    @FXML
    private TableColumn<Parts, Integer> colPartCost;

    @FXML
    private TableColumn<Parts, Integer> colPartId;

    @FXML
    private TableColumn<Parts, String> colPartName;
    @FXML
    private TableColumn<Parts, String> colProducingCountry;
    @FXML
    private TableView<Parts> tvParts;

    public void initialize() {

        colPartId.setResizable(false);
        colPartId.setSortable(false);
        colPartId.setStyle("-fx-alignment: center");
        colPartId.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partId"));

        colPartCost.setResizable(false);
        colPartCost.setSortable(false);
        colPartCost.setStyle("-fx-alignment: center");
        colPartCost.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("cost"));

        colProducingCountry.setResizable(false);
        colProducingCountry.setSortable(false);
        colProducingCountry.setStyle("-fx-alignment: center");
        colProducingCountry.setCellValueFactory(new PropertyValueFactory<Parts, String>("producingCountry"));

        colPartName.setResizable(false);
        colPartName.setSortable(false);
        colPartName.setStyle("-fx-alignment: center");
        colPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("name"));

        tfPartCost.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfPartCost.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        refreshTable();
    }

    public void refreshTable() {
        ArrayList<Parts> partsArrayList = connection.getAllParts();
        tvParts.setItems(FXCollections.observableList(partsArrayList));
    }

    @FXML
    void deletePartBtnClick(ActionEvent event) {
        if (!tfPartCost.getText().equals("") && !tfPartName.getText().equals("") && !tfProducingCountry.getText().equals("")) {
            boolean flag = connection.deletePart(tvParts.getSelectionModel().getSelectedItem().getPartId());
            if (flag == true) {
                showAlert("", "Запись запчасти удалена успешно", Alert.AlertType.INFORMATION);
            }
            tfProducingCountry.clear();
            tfPartName.clear();
            tfPartCost.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void displaySelectedPartFromTable(MouseEvent event) {
        if (tvParts.getSelectionModel().getSelectedItem() != null) {
            Parts selectedPart = tvParts.getSelectionModel().getSelectedItem();
            tfPartName.setText(selectedPart.getName());
            tfPartCost.setText(String.valueOf(selectedPart.getCost()));
            tfProducingCountry.setText(selectedPart.getProducingCountry());
        }
    }

    @FXML
    void insertPartBtnClick(ActionEvent event) {
        if (!tfPartCost.getText().equals("") && !tfPartName.getText().equals("") && !tfProducingCountry.getText().equals("")) {
            Parts part = new Parts( tfPartName.getText(), Integer.parseInt(tfPartCost.getText()), tfProducingCountry.getText());
            boolean flag = connection.createPart(part);
            if (flag == true) {
                showAlert("", "Запчасть добавлена успешно", Alert.AlertType.INFORMATION);
            }
            tfProducingCountry.clear();
            tfPartName.clear();
            tfPartCost.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
        refreshTable();
    }

    @FXML
    void updatePartBtnClick(ActionEvent event) {
        if (!tfPartCost.getText().equals("") && !tfPartName.getText().equals("") && !tfProducingCountry.getText().equals("")) {
            Parts part = new Parts(tvParts.getSelectionModel().getSelectedItem().getPartId(), tfPartName.getText(), Integer.parseInt(tfPartCost.getText()), tfProducingCountry.getText());
            boolean flag = connection.updatePart(part);
            if (flag == true) {
                showAlert("", "Запись о запчасти обновлена успешно", Alert.AlertType.INFORMATION);
            }
            tfProducingCountry.clear();
            tfPartName.clear();
            tfPartCost.clear();
            refreshTable();
        } else {
            showAlert("Ошибка ввода", "Пожалуйста, заполните все поля", Alert.AlertType.WARNING);
        }
    }

}
