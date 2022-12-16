package by.bsuir.charts;

import by.bsuir.service.ClientConnector;
import by.bsuir.service.DatabaseConnection;
import by.bsuir.service.ManageCommand;
import by.pojo.InfoToBarChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetWorkLoadInfoToBarCharCommand implements ManageCommand {
    private ClientConnector clientConnector;
    private static Logger logger = LogManager.getLogger();

    public GetWorkLoadInfoToBarCharCommand(ClientConnector clientConnector) {
        this.clientConnector = clientConnector;
    }

    @Override
    public void execute() {
        LocalDateTime border1 = null;
        try {
            border1 = clientConnector.receiveObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime border2 = border1.minusDays(1);
        ArrayList<InfoToBarChart> infoToBarChartArrayList = new ArrayList<>();
        String selectSql = "select LiftId, count(ScheduleID) as total, dateadd(DAY,0, datediff(day,0, StartDate)) as created\n" +
                "from Schedule WHERE StartDate BETWEEN '" + border2.getYear() + "-" + border2.getMonthValue() + "-" + border2.getDayOfMonth() + "' AND '"+ border1.getYear() +"-"+ border1.getMonthValue() +"-"+ border1.getDayOfMonth() + "'\n" +
                "group by dateadd(DAY,0, datediff(day,0, StartDate)), LiftID";
        logger.debug(selectSql);
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(selectSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                infoToBarChartArrayList.add(new InfoToBarChart(resultSet.getInt("LiftId"), resultSet.getInt("total"), resultSet.getTimestamp("created").toLocalDateTime()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            clientConnector.sendObject(infoToBarChartArrayList);
            logger.debug("Info to barChart sent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
