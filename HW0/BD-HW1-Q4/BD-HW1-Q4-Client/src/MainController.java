import request.Request;
import response.Response;
import response.ResponseStatus;

import java.io.IOException;
import java.util.Scanner;

public class MainController {
    private final NetworkManager networkManager;
    private final Scanner scanner;

    public MainController() throws IOException {
        this.networkManager = new NetworkManager(this);
        this.scanner = new Scanner(System.in);
    }

    public void getRequestFromUser(){
        System.out.println("Create a Request:");
        System.out.println("Enter Number Of Railway:");
        int numberOfRailWay = scanner.nextInt();
        System.out.println("Enter Time:");
        int time = scanner.nextInt();
        Request request = new Request(numberOfRailWay, time);
        this.sendRequest(request);
    }



    public void sendRequest(Request request){
        networkManager.sendRequest(request);
    }



    public void receiveResponse(Response response) throws IOException, ClassNotFoundException {
        System.out.println(response.getStatus());
        if (response.getStatus() == ResponseStatus.STARTED) {
            networkManager.waitForResponse();
        }else {
            getRequestFromUser();
        }
    }
}
