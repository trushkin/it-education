package by.bsuir.client.controllers;

import by.pojo.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static by.bsuir.client.ClientApp.connection;
import static by.bsuir.client.controllers.AddScheduleController.selectedParts;
import static by.bsuir.client.service.AlertImp.showAlert;

public class ViewAllPartController {
    @FXML
    private TableColumn<Parts, Integer> selectedColPartCost;

    @FXML
    private TableColumn<Parts, Integer> selectedColPartId;

    @FXML
    private TableColumn<Parts, String> selectedColPartName;

    @FXML
    private TableColumn<Parts, String> selectedColProducingCountry;

    @FXML
    private TableColumn<Parts, Integer> availableColPartCost;

    @FXML
    private TableColumn<Parts, Integer> availableColPartId;

    @FXML
    private TableColumn<Parts, String> availableColPartName;

    @FXML
    private TableColumn<Parts, String> availableColProducingCountry;

    @FXML
    private Button btnAddToSelected;

    @FXML
    private Button btnRemoveFromSelected;

    @FXML
    private TableView<Parts> tvAvailable;

    @FXML
    private TableView<Parts> tvSelected;
    ArrayList<Parts> availableParts = connection.getAllParts();

    //ObservableList<Parts> selectedParts = FXCollections.observableArrayList();
    public void initialize() {
        selectedParts.clear();
        availableColPartId.setResizable(false);
        availableColPartId.setSortable(false);
        availableColPartId.setStyle("-fx-alignment: center");
        availableColPartId.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partId"));

        availableColPartCost.setResizable(false);
        availableColPartCost.setSortable(false);
        availableColPartCost.setStyle("-fx-alignment: center");
        availableColPartCost.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("cost"));

        availableColProducingCountry.setResizable(false);
        availableColProducingCountry.setSortable(false);
        availableColProducingCountry.setStyle("-fx-alignment: center");
        availableColProducingCountry.setCellValueFactory(new PropertyValueFactory<Parts, String>("producingCountry"));

        availableColPartName.setResizable(false);
        availableColPartName.setSortable(false);
        availableColPartName.setStyle("-fx-alignment: center");
        availableColPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("name"));

        selectedColPartId.setResizable(false);
        selectedColPartId.setSortable(false);
        selectedColPartId.setStyle("-fx-alignment: center");
        selectedColPartId.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partId"));

        selectedColPartCost.setResizable(false);
        selectedColPartCost.setSortable(false);
        selectedColPartCost.setStyle("-fx-alignment: center");
        selectedColPartCost.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("cost"));

        selectedColProducingCountry.setResizable(false);
        selectedColProducingCountry.setSortable(false);
        selectedColProducingCountry.setStyle("-fx-alignment: center");
        selectedColProducingCountry.setCellValueFactory(new PropertyValueFactory<Parts, String>("producingCountry"));

        selectedColPartName.setResizable(false);
        selectedColPartName.setSortable(false);
        selectedColPartName.setStyle("-fx-alignment: center");
        selectedColPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("name"));

        tvAvailable.setItems(FXCollections.observableList(availableParts));
    }

    public void addToSelected(ActionEvent actionEvent) {
        if (tvAvailable.getSelectionModel().getSelectedItem() == null) {
            showAlert("Ошибка", "Пожалуйста, выберите запчасть из таблицы сверху", Alert.AlertType.WARNING);
        } else {
            selectedParts.add(tvAvailable.getSelectionModel().getSelectedItem());
            availableParts.remove(tvAvailable.getSelectionModel().getSelectedItem());
            tvSelected.setItems(selectedParts);
            tvAvailable.setItems(FXCollections.observableList(availableParts));
            tvAvailable.getSelectionModel().clearSelection();
        }
    }

    public void removeFromSelected(ActionEvent actionEvent) {
        if (tvSelected.getSelectionModel().getSelectedItem() == null) {
            showAlert("Ошибка", "Вы еще не выбрали запчасти", Alert.AlertType.WARNING);
        } else {
            availableParts.add(tvSelected.getSelectionModel().getSelectedItem());
            selectedParts.remove(tvSelected.getSelectionModel().getSelectedItem());
            tvAvailable.setItems(FXCollections.observableList(availableParts));
            tvSelected.setItems(selectedParts);
            tvSelected.getSelectionModel().clearSelection();
        }
    }
}
