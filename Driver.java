//Orrin Landon T. Uy S17 ID12111287
//This is the Driver code

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Farming Game #12111287");
        System.out.println("Please enter your name: ");
        String name = input.next();
        GameState game = new GameState(name);
        game.run();
        System.out.println("Game over");
        input.close();
    }
}
