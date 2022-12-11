module by.bsuir.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires pojo;
    requires java.sql;
    requires org.controlsfx.controls;

    opens by.bsuir.client to javafx.fxml;
    exports by.bsuir.client;
    exports by.bsuir.client.service;
    opens by.bsuir.client.service to javafx.fxml;
    exports by.bsuir.client.controllers;
    opens by.bsuir.client.controllers to javafx.fxml;

}