import controller.MainController;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Number Of Two Sided Rail Ways:");
        int numberOfTwoSidedRailWays = scanner.nextInt();
        System.out.println("Enter Number Of Trains:");
        int numberOfTrains = scanner.nextInt();

        MainController mainController = new MainController(numberOfTwoSidedRailWays);
        mainController.start(numberOfTrains);
    }
}
