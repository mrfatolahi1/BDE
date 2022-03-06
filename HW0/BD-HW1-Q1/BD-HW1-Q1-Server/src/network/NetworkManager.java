package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {
    private final ServerSocket serverSocket;

    public NetworkManager() throws IOException {
        this.serverSocket = new ServerSocket(8000);
    }

    public Socket waitForAClient(){
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            return null;
        }
    }
}
