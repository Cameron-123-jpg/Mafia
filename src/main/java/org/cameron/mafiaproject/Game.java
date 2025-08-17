package org.cameron.mafiaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.cameron.mafiaproject.Player;

public class Game {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static String specialRoles;
    private static int mafiaCount;
    private static int totalMafiaAllowed;
    private static int doctorCount;
    private static int sheriffCount;
    private static int townsFolkCount;
    private static List <Player> players = new ArrayList<>();

    public void start() {
        System.out.println("New Game of Mafia has Started!");
        System.out.print("Enter number of players:");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Would you like special roles? It's advisable you have at least 6 people... (yes/no)");
        specialRoles = scanner.nextLine().trim().toLowerCase();

        playerSetup(numPlayers);

    }
    
    public static void playerSetup(int numPlayers){
        System.out.println("You have selected " + numPlayers + " players.");
        System.out.println("Is this correct? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            System.out.println("Starting game with " + numPlayers + " players...");
            System.out.println("Would you like to name your players? (yes/no)");
            String nameChoice = scanner.nextLine().trim().toLowerCase();

            if (nameChoice.equals("yes")) {
                for (int i = 0; i < numPlayers; i++) {
                    System.out.println("Enter name for player " + (i + 1) + ":");
                    Player player = new Player(scanner.nextLine().trim(), "Townsfolk");
                    players.add(player);
                    roleSetup(player, numPlayers);
                    if (totalMafiaAllowed != mafiaCount){

                    }
        }
            } else {
                System.out.println("Players will remain unnamed.");
                for (int j = 0; j < numPlayers; j++) {
                    Player player = new Player("Player " + (j + 1), "Townsfolk");
                    System.out.println(("Player " + (j + 1) + " is created with name: " + player.getName()));
                    players.add(player);
                    roleSetup(player, numPlayers);

                }
            }
        }
        else {
            System.out.println("Game setup cancelled. Please restart to set up again.");
        }

    }
    public static void roleSetup(Player player, int numPlayers){
        if (specialRoles.equals("yes")) {
            totalMafiaAllowed = numPlayers / 4; 
            int randomNumber = random.nextInt(numPlayers);
            switch (randomNumber) {
                case 0 -> {
                    if (mafiaCount < totalMafiaAllowed) {
                        player.setRole("Mafia");
                        mafiaCount++;
                    }
                }
                case 1 -> {
                    if (doctorCount < 1){
                        player.setRole("Doctor");
                        doctorCount++;
                    }
                }
                case 2 -> {
                    if (sheriffCount < 1){
                        player.setRole("Sheriff");
                        sheriffCount++;
                    }
                }
                default -> {
                    player.setRole("Townsfolk");
                    townsFolkCount++;
                }
            }
        } else {
            totalMafiaAllowed = numPlayers / 4;
        }

    }
    //Utility Section
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printPlayers() {
        for (Player player : players) {
            System.out.println("Player Name: " + player.getName() + ", Role: " + player.getRole());
        }
    }
}