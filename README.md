# Versa Checkers
by Team TBD?

Graphical, multiplayer, and local-vs-AI Checkers game made in Java.

##Description
Versa Checkers is a versatile Checkers game, supporting local gameplay against a powerful AI and online multiplayer play against other users. The Versa Checkers package consists of a user client, an AI client, and a game server. Users can play player-vs-AI games through local user clients, or connect to locally or remotely hosted game servers for user-vs-user games.

###Processes
User client - consists of a Processing graphical/audio/IO front-end and a computation & networking back-end.

AI client - a computation & local networking process that implements the Minimax AI algorithm.

Game server - networked server that manages gameplay (game creation/verification, communication between clients, move validation), and is capable of hosting multiple games at once (multi-threaded). Servers are managed through a CLI.

Beginning games:
A local game can be started by a user through their client, and is facilitated by the client's interactions with a locally running instance of a game server and a connected AI client. A multiplayer game requires a game server to be started & opened on a local or remote machine. User clients can then connect to the server to start or join games, or be matched to games by the server.

###Technologies
* Sockets
* TLS Encryption
* Client-server model
* Threading
* Minimax (and/or related AI algorithms/tools)
* Processing
* Material design (for GUI)
