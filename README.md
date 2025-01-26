# MyJavaProject
Reward elimination game

Description
>>The Reward Elimination Game is a competitive game where players accumulate scores through random dice rolls. The game continues until one player remains, with players being eliminated based on their scores.
>> Players may also achieve "terminator" status, which allows them to eliminate others. The game uses a circular linked list to manage players.


How It Works
>>Game Setup: The game starts with a predefined number of players, who are represented by nodes in a circular linked list.
>> Each player has a score, a terminator status, and links to the next and previous players.

>>Game Loop: Players take turns rolling a dice. If the roll is low, their score decreases, and they may be eliminated if their score drops below a threshold.
>>If the roll is high, their score increases, and they can achieve "terminator" status, allowing them to eliminate another player from the game.

>>Winning: The game continues until only one player remains, who is declared the winner.


Classes

RewardEliminationGame.java (Interface)
>>Defines the essential methods that any game class must implement, such as initializing the game, adding players, and playing the game.

RENode.java (Node Class)
>>Represents a player in the game.
>>Each player has a score, a terminator status, and references to the next and previous players in the circular linked list.


RewardEliminationGame_B_Question.java (Runnable Class)
>>Implements the game logic. Manages the circular linked list of players, handles player actions, and automates the game play process.


How to Run
Clone this repository to your local machine.
Navigate to the project folder.
Compile the Java files using javac *.java.
Run the game with the command: java RewardEliminationGame_B_Question.


Methods
>>init(int n): Initializes the game with n players.Players are added as nodes to the circular linked list.

>>addPlayer(String nodeName): Adds a new player to the game by creating a new node and adding it to the tail of the circular linked list.

>>playGame(RENode player): Automates the game process by recursively playing until only one player remains. Prints the current set of players at each step.

>>play(RENode player): Simulates a player's turn by rolling a dice and updating the player's score. Handles the process of terminating players or withdrawing them based on their score.

>>terminatePlayer(RENode currentNode, RENode nextNode): Terminates a player by removing them from the game, unless the player is the current or next player.

>>rewardPlayer(RENode player, double points): Adds a specified number of points to a player's score.

>>makeTerminator(RENode player): Makes a player a terminator if their score exceeds a certain threshold.

>>withdrawPlayer(RENode player): Withdraws a player from the game if their score is too low (below -8).

