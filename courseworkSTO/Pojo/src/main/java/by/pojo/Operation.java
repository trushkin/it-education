package by.pojo;

import java.io.Serializable;

public class Operation implements Serializable {
    private int operationId;
    private String name;
    private int cost;

    public Operation(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Operation(int operationId, String name, int cost) {
        this.operationId = operationId;
        this.name = name;
        this.cost = cost;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
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
}
