package by.pojo;

import java.io.Serializable;

public class Parts implements Serializable {
    private int partId;
    private String name;
    private int cost;
    private String producingCountry;

    public Parts(int partId, String name, int cost, String producingCountry) {
        this.partId = partId;
        this.name = name;
        this.cost = cost;
        this.producingCountry = producingCountry;
    }

    public Parts(String name, int cost, String producingCountry) {
        this.name = name;
        this.cost = cost;
        this.producingCountry = producingCountry;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getProducingCountry() {
        return producingCountry;
    }

    public void setProducingCountry(String producingCountry) {
        this.producingCountry = producingCountry;
    }
}
