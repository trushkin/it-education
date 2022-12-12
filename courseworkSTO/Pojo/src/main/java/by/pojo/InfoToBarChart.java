package by.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InfoToBarChart implements Serializable {
    private int liftId;
    private int quantity;
    private LocalDateTime date;

    public int getLiftId() {
        return liftId;
    }

    public void setLiftId(int liftId) {
        this.liftId = liftId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public InfoToBarChart(int liftId, int quantity, LocalDateTime date) {
        this.liftId = liftId;
        this.quantity = quantity;
        this.date = date;
    }
}
