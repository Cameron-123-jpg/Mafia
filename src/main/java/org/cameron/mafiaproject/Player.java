package org.cameron.mafiaproject;

public class Player {
    private String name;
    private String role;
    private boolean isAlive;
    private boolean isMafia;
    private boolean isDoctor;
    private boolean isSheriff;
    private boolean isTownsfolk;
    private boolean isVoter;
    private boolean isVoted;
    private boolean isVotedFor;
    private boolean isVotedBy;
    private boolean isVotedOut;
    private boolean isNightActionTaken;
    private boolean isDayActionTaken;
    private boolean isSpecialRole;
    private boolean isSpecialRoleAssigned;
    private boolean isSpecialRoleConfirmed;

    public Player(String name, String role) {
        this.name = name;
        this.role = role;
        this.isAlive = true;
        this.isMafia = false;
        this.isDoctor = false;
        this.isSheriff = false;
        this.isTownsfolk = false;
        this.isVoter = true; 
        this.isVoted = false;
        this.isVotedFor = false;
        this.isVotedBy = false;
        this.isVotedOut = false;
        this.isNightActionTaken = false;
        this.isDayActionTaken = false;
        this.isSpecialRole = false;
        this.isSpecialRoleAssigned = false;
        this.isSpecialRoleConfirmed = false;
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
