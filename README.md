# Versa Checkers
by Team Contra

Graphical, multiplayer, and local-vs-AI Checkers game made in Java.

##Description
Versa Checkers is a versatile Checkers game, supporting local gameplay against a powerful AI and online multiplayer play against other users. The Versa Checkers package consists of a user client, an AI client, and a game server. Users can play player-vs-AI games through local user clients, or connect to locally or remotely hosted game servers for user-vs-user games.

##Running
Packaged JAR applications for the client and server lie in the "Jar Executables" folder of the repo.

To run the client: Clone/pull, open versa-checkers/Jar Executables, and run "java -jar VersaCheckerClient.jar"

To run the server: Clone/pull, open versa-checkers/Jar Executables, and run "java -jar VersaCheckerServer.jar"

##Instructions
###Multiplayer Play:
Multiplayer games are played across two clients, connecting to a local or remote server.

####Server Host:
1. Run the server as described above
2. Press "Listen" to start listening for connections on the specified port (port 1216 by default). Connected clients (IP/Name) will appear in the Client List as they connect. 
3. Press "Stop" to disconnect all clients and stop listening.

####Player: 
1. Run the client as described above. 
2. Enter the IP address (local for LAN games or remote for WAN games) and port of the server you're connecting to.
3. Choose a unique player-name. 
4. Press "Connect" to connect to the server. If you see an "Error connecting..." message, check that your server details are correct and that the server is running.
5. Once you are connected, click on the name of any user in the Users list and press "New Game" to immediately start a game with them. Alternatively, wait for another player to start a game with you.
6. Once a game window opens, if you created the game, make the first move. Otherwise, wait for your opponent to make a move.
7. To make a move, click a valid, moveable piece (its square will turn green) and click on the square you want to move it to. Press "Confirm Move" to submit your move, or "Undo" to undo it.
8. Use the chat interface on the right of the game window to chat with your oppponent.
9. You will receive a "You win" or "You lose" notification once the game ends, or if you or your opponent forfeit. To forfeit, press the "Give Up" button at the top of the window.

##Techincal Details
###Processes
User client - consists of a Swing graphical/audio/IO front-end and a computation & networking back-end.

AI client - a computation & local networking process that implements the Minimax AI algorithm.

Game server - networked server that manages gameplay (game creation/verification, communication between clients, move validation), and is capable of hosting multiple games at once (multi-threaded). Servers are managed through a Swing GUI.

Beginning games:
A local game can be started by a user through their client, and is facilitated by the client's interactions with a locally running instance of a game server and a connected AI client. A multiplayer game requires a game server to be started & opened on a local or remote machine. User clients can then connect to the server to start or join games.

###Technologies
* Sockets
* Client-server model
* Threading
* Minimax and AlphaBeta search Algorithm 
* Swing
* JavaFX
* Model-View-Controller Model

###Project Outline
1. Learn about branching
2. Learn inter-thread communication and Checkers AI (minimax)
3. Design basic client/server protocol
4. Basic game server (multi-game, CLI, exception-safe)
5. Client backend and GUI frontend
6. AI backend
7. Improve graphics

###Tools used for the Project
1. IntelliJ for coding, project organization, and jar file generation
2. JFormDesigner for generating the JSwing GUI
3. Lots of online guides for the MinMax and AlphaBeta algorithm
4. SceneBuilder for JavaFX creation

###Future goals
1. Require opponent confirmation before starting a game (not automatic starts)
2. Finish remaking the server GUI with JavaFX, and implement more features (user/game lists, stored settings file and modifiable settings, stored player information, "About" page)
3. Remake the user GUI with JavaFX for better looking and easier-to-use interace (and cleaner code)
