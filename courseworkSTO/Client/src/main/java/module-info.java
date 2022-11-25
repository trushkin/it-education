module by.bsuir.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires pojo;

    opens by.bsuir.client to javafx.fxml;
    exports by.bsuir.client;

}