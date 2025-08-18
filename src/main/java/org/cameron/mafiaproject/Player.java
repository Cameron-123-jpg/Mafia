package org.cameron.mafiaproject;

public class Player {
    private String name;
    private String role;
    private boolean isAlive;

    public Player(String name, String role) {
        this.name = name;
        this.role = role;
        this.isAlive = true;
    }

    public String getName() {
        return name;
}
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
