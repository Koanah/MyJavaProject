import java.util.Iterator;

public interface RewardEliminationGame {
    /*initialises the game with a specific number of players*/
    public void init(int numPlayers);

    /*adds a player to the game*/
    public void addPlayer(String nodeName);

    /*automatically plays the game and prints the winning player*/
    public void playGame(RENode player);

    /*reward a player with a specific score*/
    public  void rewardPlayer(RENode player, double score);

    /*A player plays the game,
    chooses next player to play the game,
    chooses a player to eliminate if the player becomes a terminator,
    gets eliminated is score is less than -10
    */
    public RENode play(RENode player);

    /*a player is removed due to poor performance*/
    public void withdrawPlayer (RENode player);

    /*makes a player a terminator if the player has reached a certain score
     * a player that becomes a terminator can choose to remove another player
     * */
    public void makeTerminator(RENode player);

}