package org.cameron.mafiaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static String specialRoles;
    private static int mafiaCount;
    private static int totalMafiaAllowed;
    private static int doctorCount;
    private static int sheriffCount;
    private static int townsFolkCount;
    private static List<Player> players = new ArrayList<>();

    public void start() {
        System.out.println("New Game of Mafia has Started!");
        System.out.print("Enter number of players:");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Would you like special roles? It's advisable you have at least 6 people... (yes/no)");
        specialRoles = scanner.nextLine().trim().toLowerCase();

        playerSetup(numPlayers);

    }

    public static void playerSetup(int numPlayers) {
        System.out.println("You have selected " + numPlayers + " players.");
        System.out.println("Is this correct? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println("Game setup cancelled. Please restart to set up again.");
            return;
        }

        System.out.println("Starting game with " + numPlayers + " players...");
        System.out.println("Would you like to name your players? (yes/no)");
        String nameChoice = scanner.nextLine().trim().toLowerCase();

        players.clear();
        mafiaCount = 0;
        doctorCount = 0;
        sheriffCount = 0;
        townsFolkCount = 0;

        // Create players with unique names
        for (int i = 0; i < numPlayers; i++) {
            String name;
            if (nameChoice.equals("yes")) {
                System.out.println("Enter name for player " + (i + 1) + ":");
                name = scanner.nextLine().trim();
            } else {
                name = "Player " + (i + 1);
            }
            players.add(new Player(name, "Townsfolk"));
        }

        // Assign roles
        totalMafiaAllowed = (int) Math.floor(numPlayers / 4.0);
        List<Integer> availableIndices = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) availableIndices.add(i);

        if (specialRoles.equals("yes")) {
            // Assign Mafia
            for (int i = 0; i < totalMafiaAllowed && !availableIndices.isEmpty(); i++) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Mafia");
                mafiaCount++;
            }
            // Assign Doctor
            if (!availableIndices.isEmpty()) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Doctor");
                doctorCount++;
            }
            // Assign Sheriff
            if (!availableIndices.isEmpty()) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Sheriff");
                sheriffCount++;
            }
            // Remaining are Townsfolk
            townsFolkCount = availableIndices.size();
        } else {
            // Only Mafia and Townsfolk
            for (int i = 0; i < totalMafiaAllowed && !availableIndices.isEmpty(); i++) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Mafia");
                mafiaCount++;
            }
            townsFolkCount = availableIndices.size();
        }

        // Print role counts
        if (specialRoles.equals("yes")) {
            System.out.println("Mafia: " + mafiaCount + ", Sheriff: " + sheriffCount + ", Doctor: " + doctorCount + ", Townsfolk: " + townsFolkCount);
        } else {
            System.out.println("Mafia: " + mafiaCount + ", Townsfolk: " + townsFolkCount);
        }
    }

    // This section is used to set roles to players
    public static void roleSetup(Player player, int numPlayers) {
        if (specialRoles.equals("yes")) {
            totalMafiaAllowed = (int) Math.floor(numPlayers / 4.0);
            int randomNumber = random.nextInt(numPlayers);
            switch (randomNumber) {
                case 0 -> {
                    if (mafiaCount < totalMafiaAllowed) {
                        player.setRole("Mafia");
                        mafiaCount++;
                    }
                }
                case 1 -> {
                    if (doctorCount < 1) {
                        player.setRole("Doctor");
                        doctorCount++;
                    }
                }
                case 2 -> {
                    if (sheriffCount < 1) {
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
            totalMafiaAllowed = (int) Math.floor(numPlayers / 4.0);
            int randomNumber = random.nextInt(numPlayers);
            switch (randomNumber) {
                case 0 -> {
                    if (mafiaCount < totalMafiaAllowed) {
                        player.setRole("Mafia");
                        mafiaCount++;
                    }
                }
                default -> {
                    player.setRole("Townsfolk");
                    townsFolkCount++;
                }
            }
        }

    }

    // Utility Section
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