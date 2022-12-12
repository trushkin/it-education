package by.bsuir.client.controllers;

import by.pojo.Operation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.controllers.AddScheduleController.selectedOperations;
import static by.bsuir.client.service.AlertImp.showAlert;

public class ViewAllOperations {
    @FXML
    private TableColumn<Operation, Integer> availableColOperationCost;

    @FXML
    private TableColumn<Operation, Integer> availableColOperationId;

    @FXML
    private TableColumn<Operation, String> availableColOperationName;

    @FXML
    private Button btnAddToSelected;

    @FXML
    private Button btnRemoveFromSelected;

    @FXML
    private TableColumn<Operation, Integer> selectedColOperationCost;

    @FXML
    private TableColumn<Operation, Integer> selectedColOperationId;

    @FXML
    private TableColumn<Operation, String> selectedColOperationName;

    @FXML
    private TableView<Operation> tvAvailable;

    @FXML
    private TableView<Operation> tvSelected;
    ArrayList<Operation> availableOperation = connection.getAllOperations();
    public void initialize() {
        selectedOperations.clear();
        availableColOperationId.setResizable(false);
        availableColOperationId.setSortable(false);
        availableColOperationId.setStyle("-fx-alignment: center");
        availableColOperationId.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("operationId"));

        availableColOperationName.setResizable(false);
        availableColOperationName.setSortable(false);
        availableColOperationName.setStyle("-fx-alignment: center");
        availableColOperationName.setCellValueFactory(new PropertyValueFactory<Operation, String>("name"));

        availableColOperationCost.setResizable(false);
        availableColOperationCost.setSortable(false);
        availableColOperationCost.setStyle("-fx-alignment: center");
        availableColOperationCost.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("cost"));

        selectedColOperationId.setResizable(false);
        selectedColOperationId.setSortable(false);
        selectedColOperationId.setStyle("-fx-alignment: center");
        selectedColOperationId.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("operationId"));

        selectedColOperationName.setResizable(false);
        selectedColOperationName.setSortable(false);
        selectedColOperationName.setStyle("-fx-alignment: center");
        selectedColOperationName.setCellValueFactory(new PropertyValueFactory<Operation, String>("name"));

        selectedColOperationCost.setResizable(false);
        selectedColOperationCost.setSortable(false);
        selectedColOperationCost.setStyle("-fx-alignment: center");
        selectedColOperationCost.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("cost"));

        tvAvailable.setItems(FXCollections.observableList(availableOperation));
    }
    public void addToSelected(ActionEvent actionEvent) {
        if (tvAvailable.getSelectionModel().getSelectedItem() == null) {
            showAlert("Ошибка", "Пожалуйста, выберите услугу из таблицы сверху", Alert.AlertType.WARNING);
        } else {
            selectedOperations.add(tvAvailable.getSelectionModel().getSelectedItem());
            availableOperation.remove(tvAvailable.getSelectionModel().getSelectedItem());
            tvSelected.setItems(selectedOperations);
            tvAvailable.setItems(FXCollections.observableList(availableOperation));
            tvAvailable.getSelectionModel().clearSelection();
        }
    }

    public void removeFromSelected(ActionEvent actionEvent) {
        if (tvSelected.getSelectionModel().getSelectedItem() == null) {
            showAlert("Ошибка", "Вы еще не выбрали запчасти", Alert.AlertType.WARNING);
        } else {
            availableOperation.add(tvSelected.getSelectionModel().getSelectedItem());
            selectedOperations.remove(tvSelected.getSelectionModel().getSelectedItem());
            tvAvailable.setItems(FXCollections.observableList(availableOperation));
            tvSelected.setItems(selectedOperations);
            tvSelected.getSelectionModel().clearSelection();
        }
    }
}
