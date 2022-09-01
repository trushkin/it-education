package by.bsuir.coursework.sto.stoProperties;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;


public class Properties {
    private static int startWorkTime = 9;
    private static int endWorklTime = 18;


    public static int getStartWorkTime() {
        return startWorkTime;
    }

    public static void setStartWorkTime(int startWorkTime) {
        Properties.startWorkTime = startWorkTime;
    }

    public static int getEndWorkTime() {
        return endWorklTime;
    }

    public static void setEndWorkTime(int endWorkTime) {
        Properties.endWorklTime = endWorkTime;
    }

    public static ObservableList<LocalTime> getTimeline()
    {
        ObservableList<LocalTime> timeline = FXCollections.observableArrayList();
        for (int i = getStartWorkTime(); i < getEndWorkTime(); i++)
        {
            timeline.add(LocalTime.of(i, 0));
            timeline.add(LocalTime.of(i, 30));
        }
       // timeline.add(LocalTime.of(getEndWorkTime(), 0));
        return timeline;
    }


}
