module com.example.coursework_oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens by.bsuir.coursework.sto to javafx.fxml;
    exports by.bsuir.coursework.sto;
    exports by.bsuir.coursework.sto.car;
    opens by.bsuir.coursework.sto.car to javafx.fxml;
    exports by.bsuir.coursework.sto.lift;
    opens by.bsuir.coursework.sto.lift to javafx.fxml;
    exports by.bsuir.coursework.sto.client;
    opens by.bsuir.coursework.sto.client to javafx.fxml;
    exports by.bsuir.coursework.sto.database;
    opens by.bsuir.coursework.sto.database to javafx.fxml;
    exports by.bsuir.coursework.sto.schedule;
    opens by.bsuir.coursework.sto.schedule to javafx.fxml;
    exports by.bsuir.coursework.sto.mainScreen;
    opens by.bsuir.coursework.sto.mainScreen to javafx.fxml;
    exports by.bsuir.coursework.sto.stoProperties;
    opens by.bsuir.coursework.sto.stoProperties to javafx.fxml;
}