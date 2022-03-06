package controller;

import dish.Dish;
import events.clientevent.ClientEvent;
import events.serverevent.ServerEvent;

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
    private Dish dish;

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
            ClientEvent clientEvent = (ClientEvent) objectInputStream.readObject();
            System.out.println();
            System.out.println("Client: "+ id + "\n" + clientEvent);
            System.out.println();
            sendResponseToClient(mainController.manageClientEvents(this.dish, clientEvent));
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void sendResponseToClient(ServerEvent serverEvent) throws IOException {
        objectOutputStream.reset();
        objectOutputStream.writeObject(serverEvent);
    }
}
