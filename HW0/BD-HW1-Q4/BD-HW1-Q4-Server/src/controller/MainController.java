package controller;

import models.Railway;
import network.NetworkManager;
import request.Request;
import response.Response;
import response.ResponseStatus;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class MainController {
    private final Railway[] railways;
    private final NetworkManager networkManager;
    private final LinkedList<ClientHandler> connectedClients;

    public MainController(int numberOfTwoSidedRailWays) throws IOException {
        this.networkManager = new NetworkManager();
        this.connectedClients = new LinkedList<>();
        this.railways = new Railway[numberOfTwoSidedRailWays];

        for (int i = 0; i < numberOfTwoSidedRailWays; i++) {
            railways[i] = new Railway(i);
        }
    }

    public void start(int numberOfTrains) {
        int number = 0;
        while (number < numberOfTrains){
            Socket socket = networkManager.waitForAClient();
            System.out.println(number + " Connected");
            (new Thread(() -> {
                try {
                    ClientHandler client = new ClientHandler(MainController.this, socket, connectedClients.size());
                    client.start();
                } catch (IOException | ClassNotFoundException ignored) {}
            })).start();
            number++;
        }
        while (connectedClients.size() < numberOfTrains){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.manageRequests();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void manageRequests() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println(getMap());
            for (Railway railway : railways) {
                if (railway.getCurrentRequest() != null) {
                    railway.setRemainingTime(railway.getRemainingTime() - 1);
                    if (railway.getRemainingTime() == 0) {
                        connectedClients.get(railway.getCurrentRequest().getId()).returnResponse(new Response(ResponseStatus.FINISHED));
                        railway.setNewRequest();
                        if (railway.getCurrentRequest() != null){
                            connectedClients.get(railway.getCurrentRequest().getId()).returnResponse(new Response(ResponseStatus.STARTED));
                        }
                    }
                }
            }
        }
    }

    private synchronized String getMap(){
        String output = "-----------------------------------------------------------------------\n";
        for (Railway railway : railways){
            String line = "";
            if (railway.getCurrentRequest() != null){
                line = line + "Railway " + railway.getId() + " Current Train: " + railway.getCurrentRequest().getId() + " // Queued Trains: ";
            }else {
                line = line + "Railway " + railway.getId() + " Current Train: " + null + " // Queued Trains: ";
            }
            for (Request request : railway.getRequestsQueue()){
                line = line + request.getId() + ", ";
            }
            line = line.substring(0, line.length() - 1);
            output = output + line + "\n";
        }
        return output;
    }

    public LinkedList<ClientHandler> getConnectedClients() {
        return connectedClients;
    }

    public synchronized void addToConnectedClients(ClientHandler clientHandler){
        connectedClients.add(clientHandler);
    }

    public synchronized void receiveRequests(Request request){
        sortedAdd(this.railways[request.getNumberOfRailWay()].getRequestsQueue(), request);
        if (this.railways[request.getNumberOfRailWay()].getCurrentRequest() == null){
            this.railways[request.getNumberOfRailWay()].setNewRequest();
        }
        System.out.println(getMap());
    }

    private synchronized void sortedAdd(LinkedList<Request> linkedList, Request request){
        if (linkedList.isEmpty()){
            linkedList.add(request);
            return;
        }
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).getTime() > request.getTime()){
                linkedList.add(i, request);
                return;
            }
            if (i + 1 == linkedList.size()){
                linkedList.add(request);
            }
        }
    }
}
