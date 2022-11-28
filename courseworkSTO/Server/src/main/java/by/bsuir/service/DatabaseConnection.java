package by.bsuir.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=STO;user=sqldemo;" +
            "password=1111;TrustServerCertificate=True";
    public static final String DB_Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static Logger logger = LogManager.getLogger();
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) return connection;

        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(connectionUrl);//соединениесБД
            logger.info("Successful connection to database");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            logger.fatal("JDBC driver has not found!");
            throw new IllegalStateException(e);
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            logger.error("SQL error");
            throw new IllegalStateException(e);
        }//

    }
}
