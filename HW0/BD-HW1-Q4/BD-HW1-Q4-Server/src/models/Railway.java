package models;

import request.Request;

import java.util.ArrayList;
import java.util.LinkedList;

public class Railway {
    private final int id;
    private final LinkedList<Request> requestsQueue;
    private Request currentRequest;
    private int remainingTime;

    public Railway(int id) {
        this.id = id;
        this.requestsQueue = new LinkedList<>();
    }

    public LinkedList<Request> getRequestsQueue() {
        return requestsQueue;
    }

    public int getId() {
        return id;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }

    public void setCurrentRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setNewRequest(){
        if (requestsQueue.isEmpty()){
            this.currentRequest = null;
            this.remainingTime = 0;
            return;
        }
        this.currentRequest = requestsQueue.removeFirst();
        this.remainingTime = currentRequest.getTime();
    }
}
