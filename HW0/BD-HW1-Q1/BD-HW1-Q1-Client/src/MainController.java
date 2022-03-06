import events.clientevent.ClientEvent;
import events.clientevent.ClientEventStatus;
import events.serverevent.ServerEvent;
import events.serverevent.ServerEventStatus;

import java.io.IOException;
import java.util.Scanner;

public class MainController {
    private final NetworkManager networkManager;
    private ServerEventStatus status;
    private final Scanner scanner;

    public MainController() throws IOException {
        this.networkManager = new NetworkManager(this);
        this.status = ServerEventStatus.YOU_HAVE_NO_CHOPSTICK;
        this.scanner = new Scanner(System.in);
    }

    public void getRequestFromUser(){
        showMenu();
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sendClientEvent(new ClientEvent(ClientEventStatus.ASK_FOR_RIGHT_CHOPSTICK, 0));
            case 2 -> sendClientEvent(new ClientEvent(ClientEventStatus.ASK_FOR_LEFT_CHOPSTICK, 0));
            case 3 -> sendClientEvent(new ClientEvent(ClientEventStatus.DROP_ALL_CHOPSTICKS, 0));
            case 4 -> {
                System.out.println("Enter Time:");
                int time = scanner.nextInt();
                sendClientEvent(new ClientEvent(ClientEventStatus.START_EATING, time));
            }
        }
    }

    private void showMenu(){
        clearConsole();
        System.out.println("--------------------------------------");
        System.out.println("\u001B[33m" + "Status: " + status);
        System.out.println("\u001B[35m");
        System.out.println(
                """
                1. ASK_FOR_RIGHT_CHOPSTICK
                2. ASK_FOR_LEFT_CHOPSTICK
                3. DROP_ALL_CHOPSTICKS
                4. START_EATING"""
        + "\u001B[0m");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println();
    }

    private void clearConsole(){
        for (int i = 0; i < 500; i++) {
            System.out.println();
        }
    }

    public void sendClientEvent(ClientEvent clientEvent){
        networkManager.sendClientEvent(clientEvent);
    }



    public void receiveServerEvent(ServerEvent serverEvent){
        this.status = serverEvent.getStatus();
        getRequestFromUser();
    }
}
