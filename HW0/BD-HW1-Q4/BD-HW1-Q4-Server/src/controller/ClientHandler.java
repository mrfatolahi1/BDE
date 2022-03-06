package controller;


import request.Request;
import response.Response;
import response.ResponseStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler {
    private final MainController mainController;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Socket socket;
    private boolean mustDie;
    private int id;

    public ClientHandler(MainController mainController, Socket socket, int id) throws IOException {
        this.mainController = mainController;
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.id = id;
        this.mustDie = false;
    }

    public void start() throws IOException, ClassNotFoundException {
        mainController.addToConnectedClients(this);
        manageClientEvents();
    }

    private void manageClientEvents() throws IOException, ClassNotFoundException {
        while (!mustDie) {
            Request request = (Request) objectInputStream.readObject();
            System.out.println();
            System.out.println("Client: "+ id + "   " + request);
            System.out.println();
            request.setId(id);
            mainController.receiveRequests(request);
        }
    }

    private void close(){
        mainController.getConnectedClients().remove(this);
        try {
            socket.close();
        } catch (IOException ignored) {}
        mustDie = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void returnResponse(Response response){
        try {
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutputStream.writeObject(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
