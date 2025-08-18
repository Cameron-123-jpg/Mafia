# Mafia Terminal Game

This is a recreation of the popular card game mafia (or werewolf) using Java and your computer's terminal

Currently, this project uses maven and you can run it by entering 
mvn compile
mvn exec:java -Dexec.mainClass="org.cameron.mafiaproject.Mafia"

While playing, currently there's no reinput so try to type exactly what prompts tell you to (ig, a number for player count and yes or no only)
I will add a mercy feature so that accidentally mistyping doesn't crash java and reset the game

Hoping to scale this project and use javascript and springboot to create a multiplayer version

## Requirements
- Java 17+ (tested)
- Maven

## Features
- Player setup with custom or default names
- Automatic role assignment (Mafia, Doctor, Sheriff, Townsfolk)
- Private role reveal system (pass the device between players)
- Basic round system scaffolded (night and day phases)

## Roadmap
- [ ] Add input validation (so typos don’t crash the game)
- [ ] Complete night actions (Mafia kill, Doctor save, Sheriff investigation)
- [ ] Implement discussion and voting system
- [ ] Add win condition checks
- [ ] Refactor to remove global variables (allow multiple games)
- [ ] Scale project into a multiplayer web version (Spring Boot + JavaScript)

## Future Plans
After the terminal version is finished, I plan to:
- Convert the game logic into a Spring Boot backend
- Build a JavaScript frontend for multiplayer play
- Deploy it so games can be played online