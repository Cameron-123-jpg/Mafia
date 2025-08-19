# 🎭 Mafia (Terminal Edition)

A terminal-based recreation of the classic party game **Mafia** (also known as Werewolf), written in Java.  
This is the first version of the project — designed for **local, pass-the-laptop play**.  

Future plans include scaling into a full **multiplayer web version** with Spring Boot + JavaScript. 🚀  

---

## Requirements
- **Java 17+** (tested)  
- **Maven** (for build/run)

---

## How to Run
Clone the repository and navigate into the project folder:  
```bash
git clone https://github.com/Cameron-123-jpg/Mafia.git
cd mafia-terminal
mvn compile
mvn exec:java -Dexec.mainClass="org.cameron.mafiaproject.Mafia"

---

## Current Features
- Local player setup with **custom or default names**
- Automatic **role assignment** (Mafia, Doctor, Sheriff, Townsfolk)
- Private role reveal system (pass-the-device style)
- Basic **night/day cycle** with Mafia kill, Doctor save, and Sheriff investigation
- Win condition checks (Mafia vs Town balance)

---

## Future Roadmap

Here’s what’s planned for upcoming versions of the Mafia game:  

## Terminal Version
- Add **input validation** so typos don’t crash the game  
- Add a **mercy feature** (option to retry/restart after invalid input)  
- Improve **town voting system** (support ties, revotes, random tie-breakers)  
- Add more **special roles** (e.g., Jester, Vigilante, Bodyguard)  
- Refactor to remove global variables → support multiple games in one session  

## Multiplayer Web Version
- Build a **Spring Boot backend** to manage game state & roles  
- Create a **JavaScript/React frontend** for player interaction  
- Add **real-time multiplayer** with WebSockets  
- Include **chat & discussion rooms** for Day Phase  
- Deploy online for friends to join remotely

## Stretch Goals
- Role-based animations & UI polish  
- Game lobby with **player invites**  
- Spectator mode  
- Mobile-friendly version  

---

## Notes
This is the **first draft** of the Mafia project — it’s not perfect.  
I’ll continue improving it and eventually develop a **full web version** so players can connect online.  
Stay tuned for updates!