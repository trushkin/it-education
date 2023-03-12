module by.bsuir.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires pojo;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.apache.commons.codec;
    requires java.datatransfer;
    requires itextpdf;
    requires icu4j;

    requires spring.core;
    requires spring.beans;
    requires spring.context;

    opens by.bsuir.client to javafx.fxml, spring.core;
    exports by.bsuir.client;
    exports by.bsuir.client.service;
    opens by.bsuir.client.service to javafx.fxml, spring.core;
    exports by.bsuir.client.controllers;
    opens by.bsuir.client.controllers to javafx.fxml, spring.core;

}