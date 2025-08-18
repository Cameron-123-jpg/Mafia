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

        totalMafiaAllowed = (int) Math.floor(numPlayers / 4.0);
        List<Integer> availableIndices = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++)
            availableIndices.add(i);

        if (specialRoles.equals("yes")) {
            for (int i = 0; i < totalMafiaAllowed && !availableIndices.isEmpty(); i++) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Mafia");
                mafiaCount++;
            }

            if (!availableIndices.isEmpty()) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Doctor");
                doctorCount++;
            }

            if (!availableIndices.isEmpty()) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Sheriff");
                sheriffCount++;
            }

            townsFolkCount = availableIndices.size();
        } else {
            for (int i = 0; i < totalMafiaAllowed && !availableIndices.isEmpty(); i++) {
                int idx = availableIndices.remove(random.nextInt(availableIndices.size()));
                players.get(idx).setRole("Mafia");
                mafiaCount++;
            }
            townsFolkCount = availableIndices.size();
        }

        // Passing laptop and sharing the roles
        clearScreen();
        System.out.println("Please hand the device to each player to view their role.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        clearScreen();
        for (Player player : players) {
            System.out.println(player.getName() + ", your role is: " + player.getRole());
            System.out.println("Press Enter and hand the device to the next player...");
            scanner.nextLine();
            clearScreen();
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            clearScreen();
        }
        // Final check before game starts
        System.out.println("All players have been assigned roles.");
        System.out.println(
                "Please confirm that the amount of roles assigned is correct. The mafia should be 1/4 of players rounded down and there's only one of special roles.");
        String roleConfirmation = scanner.nextLine().trim().toLowerCase();
        if (!roleConfirmation.equals("yes")) {
            System.out.println("Game Cancelled, please restart");
        } else {
            System.out.println("Roles confirmed. Starting the game...");
            loadingScreen();
            round();
        }

    }

    // This section is used to set roles to players, could probably be optimized
    // more as it checks again if role counts are the same
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

    //Currently, has the outline of round but needs the actual implementation
    public static void round() {
        while (townsFolkCount > mafiaCount && mafiaCount > 0) {
            System.out.println("Night has fallen. Mafia, choose your target.");
            System.out.println("Mafia have chosen their target.");

            if (doctorCount > 0) {
                System.out.println("Doctor, choose someone to save.");
                System.out.println("Doctor has made their choice.");
            }

            if (sheriffCount > 0) {
                System.out.println("Sheriff, choose someone to investigate.");
                System.out.println("Sheriff has made their choice.");
            }
            System.out.println("Night actions have been resolved.");
            System.out.println("Day has dawned. Discuss and vote to eliminate a suspect.");
            System.out.println("Voting has concluded.");
            System.out.println("Voting results have been resolved.");
        }
        if (mafiaCount == 0) {
            System.out.println("Townsfolk win!");
        } else if (townsFolkCount <= mafiaCount) {
            System.out.println("Mafia win!");
        }
        System.out.println("Game over. Thanks for playing!");
    }

    // Utility Section, adding things that might be useful later
    // Clears screen but doesn't remove previous console output
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Prints all players and their roles, useful for testing
    public void printPlayers() {
        for (Player player : players) {
            System.out.println("Player Name: " + player.getName());
        }
    }

    // Simple loading screen for starting game
    public static void loadingScreen() {
        System.out.println("Loading");
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clearScreen();
        System.out.println("Game Started!");
    }
}