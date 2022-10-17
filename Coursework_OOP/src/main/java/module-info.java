module com.example.coursework_oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.logging.log4j;


    opens by.bsuir.coursework.sto to javafx.fxml;
    exports by.bsuir.coursework.sto;
    exports by.bsuir.coursework.sto.car;
    opens by.bsuir.coursework.sto.car to javafx.fxml;
    exports by.bsuir.coursework.sto.lift;
    opens by.bsuir.coursework.sto.lift to javafx.fxml;
    exports by.bsuir.coursework.sto.client;
    opens by.bsuir.coursework.sto.client to javafx.fxml;
    exports by.bsuir.coursework.sto.application;
    opens by.bsuir.coursework.sto.application to javafx.fxml;
    exports by.bsuir.coursework.sto.schedule;
    opens by.bsuir.coursework.sto.schedule to javafx.fxml;
    exports by.bsuir.coursework.sto.main;
    opens by.bsuir.coursework.sto.main to javafx.fxml, javafx.controls;
}