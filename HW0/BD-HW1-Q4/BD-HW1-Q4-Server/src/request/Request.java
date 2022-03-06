package request;

import java.io.Serializable;

public class Request implements Serializable {
    private int id;
    private final int numberOfRailWay;
    private final int time;

    public Request(int numberOfRailWay, int time) {
        this.numberOfRailWay = numberOfRailWay;
        this.time = time;
    }

    public int getNumberOfRailWay() {
        return numberOfRailWay;
    }

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "numberOfRailWay=" + numberOfRailWay +
                ", time=" + time +
                '}';
    }
}
