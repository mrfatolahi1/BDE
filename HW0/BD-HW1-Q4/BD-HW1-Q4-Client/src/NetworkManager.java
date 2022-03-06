import request.Request;
import response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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


    public void receiveResponse(Response response) {
        try {
            mainController.receiveResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(Request request) {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            waitForResponse();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void waitForResponse() throws IOException, ClassNotFoundException {
        Object object = objectInputStream.readObject();
        receiveResponse((Response) object);
    }
}
