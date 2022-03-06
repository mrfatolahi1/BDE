package controller;

import chopstick.ChopStick;
import dish.Dish;
import dish.DishStatus;
import events.clientevent.ClientEvent;
import events.clientevent.ClientEventStatus;
import events.serverevent.ServerEvent;
import events.serverevent.ServerEventStatus;
import network.NetworkManager;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MainController {
    private final NetworkManager networkManager;
    private final ArrayList<ClientHandler> connectedClients;
    private final Dish[] dishes;
    private final ChopStick[] chopSticks;

    public MainController() throws IOException {
        this.networkManager = new NetworkManager();
        this.connectedClients = new ArrayList<>();
        this.dishes = new Dish[5];
        this.chopSticks = new ChopStick[5];
    }

    public void start() {
        int number = 1;
        while (number <= 5){
            Socket socket = networkManager.waitForAClient();
            System.out.println(number + " Connected");
            (new Thread(() -> {
                try {
                    ClientHandler client = new ClientHandler(MainController.this, socket, connectedClients.size()+1);
                    client.start();
                } catch (IOException | ClassNotFoundException ignored) {}
            })).start();
            number++;
        }
        while (connectedClients.size() < 5){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.initialize();
        }
    }


    public ArrayList<ClientHandler> getConnectedClients() {
        return connectedClients;
    }

    public synchronized void addToConnectedClients(ClientHandler clientHandler){
        System.out.println("clientHandler.getId() = " + clientHandler.getId());
        connectedClients.add(clientHandler);
    }

    private void initialize(){
        System.out.println("initialize");
        for (int i = 0; i < 5; i++) {
            chopSticks[i] = new ChopStick(null, null);
        }
        dishes[0] = new Dish(chopSticks[4], chopSticks[0]);
        dishes[1] = new Dish(chopSticks[0], chopSticks[1]);
        dishes[2] = new Dish(chopSticks[1], chopSticks[2]);
        dishes[3] = new Dish(chopSticks[2], chopSticks[3]);
        dishes[4] = new Dish(chopSticks[3], chopSticks[4]);

        dishes[0].setRightDish(dishes[1]);
        dishes[1].setRightDish(dishes[2]);
        dishes[2].setRightDish(dishes[3]);
        dishes[3].setRightDish(dishes[4]);
        dishes[4].setRightDish(dishes[0]);

        dishes[0].setLeftDish(dishes[4]);
        dishes[1].setLeftDish(dishes[0]);
        dishes[2].setLeftDish(dishes[1]);
        dishes[3].setLeftDish(dishes[2]);
        dishes[4].setLeftDish(dishes[3]);

//        for (ClientHandler clientHandler : connectedClients){
//            System.out.println(clientHandler);
//        }

        for (int i = 0; i < 5; i++) {
            dishes[i].setStatus(DishStatus.HAS_NO_CHOPSTICK);
        }
        for (int i = 0; i < 5; i++) {
            connectedClients.get(i).setDish(dishes[i]);
        }
//        for (int i = 0; i < 5; i++) {
//            System.out.println("connectedClients.get(i).getDish() = " + connectedClients.get(i).getDish());
//        }
//        this.manage();
    }

    public ServerEvent manageClientEvents(Dish dish, ClientEvent clientEvent){
        if (clientEvent.getStatus() == ClientEventStatus.ASK_FOR_RIGHT_CHOPSTICK){
            if (dish.getStatus() == DishStatus.HAS_NO_CHOPSTICK){
                if (dish.getRightDish().getRightChopStick().getCurrentDish() == dish.getRightDish()){
                    return new ServerEvent(ServerEventStatus.YOU_HAVE_NO_CHOPSTICK, 0);
                }else
                if(dish.getRightDish().getRightChopStick().getCurrentDish() == null){

                }else {
                    dish.setStatus(DishStatus.HAS_RIGHT_CHOPSTICK);
                    dish.getRightChopStick().setCurrentDish(dish);
                    return new ServerEvent(ServerEventStatus.YOU_HAVE_RIGHT_CHOPSTICK, 0);
                }
            }else {
                return null;
            }
        }else
        if (clientEvent.getStatus() == ClientEventStatus.ASK_FOR_LEFT_CHOPSTICK){
            if (dish.getStatus() == DishStatus.HAS_RIGHT_CHOPSTICK){
                if (dish.getLeftChopStick().getCurrentDish() == null){
                    return new ServerEvent(ServerEventStatus.YOU_HAVE_BOTH_CHOPSTICKS, 0);
                }
            }else {
                return null;
            }
        }else
        if (clientEvent.getStatus() == ClientEventStatus.DROP_ALL_CHOPSTICKS){
            dish.setStatus(DishStatus.HAS_NO_CHOPSTICK);
            if (dish.getRightChopStick().getCurrentDish() == dish){
                dish.getRightChopStick().setCurrentDish(null);
            }
            if (dish.getRightChopStick().getCurrentDish() == dish){
                dish.getRightChopStick().setCurrentDish(null);
            }
            return new ServerEvent(ServerEventStatus.YOU_HAVE_NO_CHOPSTICK, 0);
        }
        else
        if (clientEvent.getStatus() == ClientEventStatus.START_EATING){
            if (dish.getStatus() == DishStatus.HAS_BOTH_CHOPSTICKS){
                return new ServerEvent(ServerEventStatus.YOU_CAN_EAT, clientEvent.getTime());
            }
            return null;
        }
        return null;
    }
}
