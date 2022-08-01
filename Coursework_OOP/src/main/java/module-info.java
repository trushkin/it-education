module com.example.coursework_oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens by.bsuir.coursework.sto to javafx.fxml;
    exports by.bsuir.coursework.sto;
    exports by.bsuir.coursework.sto.models;
    opens by.bsuir.coursework.sto.models to javafx.fxml;
    exports by.bsuir.coursework.sto.gui;
    opens by.bsuir.coursework.sto.gui to javafx.fxml;
    exports by.bsuir.coursework.sto.dto;
    opens by.bsuir.coursework.sto.dto to javafx.fxml;
}