package by.pojo;

import java.io.Serializable;
import java.time.LocalTime;

public class Properties implements Serializable {
    private LocalTime startWorkTime;
    private LocalTime endWorkTime;

    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(LocalTime startWorkTime) {
        this.startWorkTime = startWorkTime;
    }
    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    public void setEndWorkTime(LocalTime endWorkTime) {
        this.endWorkTime = endWorkTime;
    }
}
