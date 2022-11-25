package by.bsuir.coursework.sto.main;

import by.bsuir.coursework.sto.schedule.SchedulePrint;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class ScheduleRow {
    public String time;
    Map<Integer, SchedulePrint> schedulePerLift = new HashMap<>();

    public ScheduleRow(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Map<Integer, SchedulePrint> getSchedulePerLift() {
        return schedulePerLift;
    }

    public StringProperty getSchedulePerLift(int i) {
        if (schedulePerLift.keySet().contains(i))
            return new SimpleStringProperty(schedulePerLift.get(i).toString());
        else
            return new SimpleStringProperty("<свободно>");
    }
}
