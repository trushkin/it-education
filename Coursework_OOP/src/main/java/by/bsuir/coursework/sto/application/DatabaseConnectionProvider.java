package by.bsuir.coursework.sto.application;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseConnectionProvider {
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
            //System.out.println("Соединение с СУБД выполнено.");
            logger.info("Successful connection to database");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            //System.out.println("JDBC драйвер для СУБД не найден!");
            logger.fatal("JDBC driver has not found!");
            return null;
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            //System.out.println("Ошибка SQL !");
            logger.error("SQL error");
            return null;
        }//
    }


}

