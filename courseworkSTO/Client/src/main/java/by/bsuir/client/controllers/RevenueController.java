package by.bsuir.client.controllers;

import by.pojo.InfoToBarChart;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static by.bsuir.client.ClientApp.connection;

public class RevenueController {
    @FXML
    private BarChart barChart;

    public void initialize() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Подъемник 1");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Подъемник 2");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Подъемник 3");
        ArrayList<InfoToBarChart> statistics = connection.getRevenueInfoToBarChart(LocalDateTime.now().plusDays(1));
        for (int daysQuantity = 0; daysQuantity < 7; daysQuantity++) {
            for (InfoToBarChart temp : statistics) {
                if (temp.getLiftId() == 1) {
                    series1.getData().add(new XYChart.Data(temp.getDate().getDayOfMonth() + "." + temp.getDate().getMonthValue() + "." + temp.getDate().getYear(), temp.getQuantity()));
                } else if (temp.getLiftId() == 2) {
                    series2.getData().add(new XYChart.Data(temp.getDate().getDayOfMonth() + "." + temp.getDate().getMonthValue() + "." + temp.getDate().getYear(), temp.getQuantity()));
                } else if (temp.getLiftId() == 3) {
                    series3.getData().add(new XYChart.Data(temp.getDate().getDayOfMonth() + "." + temp.getDate().getMonthValue() + "." + temp.getDate().getYear(), temp.getQuantity()));
                }
            }
            statistics = connection.getRevenueInfoToBarChart(LocalDateTime.now().minusDays(daysQuantity));
        }
        barChart.getData().addAll(series1, series2, series3);

    }
}
