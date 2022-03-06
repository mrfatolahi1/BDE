import events.clientevent.ClientEvent;
import events.serverevent.ServerEvent;

import java.io.*;
import java.net.Socket;

public class NetworkManager {
    private final MainController mainController;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;

    public NetworkManager(MainController mainController) throws IOException {
        this.mainController = mainController;
        start();
    }

    public void start() throws IOException {
        this.socket = new Socket("localhost", 8000);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }


    public void receiveServerEvent(ServerEvent serverEvent) {
        try {
            System.out.println("received = " + serverEvent);
            mainController.receiveServerEvent(serverEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendClientEvent(ClientEvent clientEvent) {
        try {
            objectOutputStream.writeObject(clientEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            waitForServerAnswer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void waitForServerAnswer() throws IOException, ClassNotFoundException {
        Object object = objectInputStream.readObject();
        receiveServerEvent((ServerEvent) object);
    }
}
