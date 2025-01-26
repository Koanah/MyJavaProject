public class RENode {
    String nodeName; // Name of the player
    double score;    // number of points accumulated
    boolean terminator; //a terminator status of a player
    double upgradeScore; //the previous score that gave a player the terminator status
    RENode next; // a reference to the next player
    RENode previous; // a reference to the previous player.

    public RENode(String nodeName) {
        this.nodeName = nodeName;
        this.score = score;
        this.terminator = false;
        this.upgradeScore = 0.0;
        this.next = null;
        this.previous = null;
    }

    public RENode(String nodeName,  RENode next, RENode previous) {
        this.nodeName = nodeName;
        this.score = score;
        this.upgradeScore = 0.0;
        this.terminator = false;
        this.next = next;
        this.previous = previous;
    }
    public String toString() {
        return "[" + nodeName + ">" + score + ">" + terminator + "]";
    }
}
