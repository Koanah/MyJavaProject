import java.util.*;

public class RewardEliminationGame_B_Question implements RewardEliminationGame {
    RENode head = null;// reference to the first node in the game
    RENode tail= null; // reference to the last node in the game
    int size=0;

    /* main Method*/
    public static void main(String[] args){
        RewardEliminationGame_B_Question regame = new RewardEliminationGame_B_Question();
        regame.init(4);
        System.out.println("original set of players: " +regame);
        regame.playGame(regame.head);
    }

    /* no argument constructor to create an empty game
     */
    public RewardEliminationGame_B_Question(){
    }

    @Override
    public void init(int n){
        for (int i=1; i<= n; i++){
            addPlayer("node_"+i);
        }
    }

    /*automatically plays the game  */
    @Override
    public void playGame(RENode player){
        if(size<2){
            System.out.println("Winner:  "+ this.head);
        }
        else{
            System.out.println("current set of players: "+this); // prints the current set of players on the console
            RENode nextPlayer = play(player);
            playGame(nextPlayer);
        }
    }
    /*adds a player to the game at the tail end of the underlying
    circular linked list*/
    @Override
    public void addPlayer(String nodeName) {
        RENode new_node = new RENode(nodeName);
        if(head==null) {
            head = tail= new_node;
            tail.next=head;
            head.previous = tail;
            size++;
        }
        else{
            tail.next=new_node;
            new_node.previous=tail;
            tail=new_node;
            tail.next=head;
            head.previous=tail;
            size++;
        }
    }

    /*adds the points obtained from the current play to the
    player's score*/
    @Override
    public void rewardPlayer(RENode player, double points) {
        player.score= player.score+ points;
    }

    /* makes a node a terminator*/
    @Override
    public void makeTerminator(RENode player) {
        if(player.score>=(player.upgradeScore+8)){
            player.terminator=true;
            player.upgradeScore=player.score;

        }else {
            player.terminator=false;
        }

    }

    /*a player is withdrawn from the game due to poor performance
     * a score less than -20 gets a player withdrawn from the game*/
    @Override
    public void withdrawPlayer(RENode player) {
        if(player.score<=-8){
            removePlayer(player);

        }

    }

    /*Terminates a chosen player.
    * A player can only be terminated if it is NOT the current player or
    the next player to play game.
    */
    public void terminatePlayer(RENode currentNode, RENode nextNode){
        int currentNodeIndex = this.getNodeIndex(currentNode);
        int nextNodeIndex = this.getNodeIndex(nextNode);
        if(head == null)
            System.out.println("The environment has no players");
        else if(head== tail)
            System.out.println("You can not remove the only player remaining in the environment");
        else {
            int nodeToRemoveIndex=this.getNodeIndex(currentNode);
            if(size>2) {
                while (currentNodeIndex == nodeToRemoveIndex | nodeToRemoveIndex == nextNodeIndex) {
                    Random r = new Random();
                    nodeToRemoveIndex = r.nextInt(size ) ;
                }
            }
            else {
                nodeToRemoveIndex = nextNodeIndex;
            }
            removePlayer(getNodeAt(nodeToRemoveIndex));
        }

    }

    /*A player plays the game,
        chooses  next player to play the game,
        chooses a player to eliminate if the player becomes a terminator,
        gets eliminated is score is less than -8
    */
    @Override
    public RENode play(RENode player) {
        RENode nextPlayer;
        Random rand = new Random();
        int diceRoll = rand.nextInt(8 ) + 1;
        //rolling the dice and upgrading score

        if (diceRoll <= 4) {
            player.score-=diceRoll;
            nextPlayer=player.previous;
            withdrawPlayer(player);

        }else {
            player.score += (diceRoll - 4);
            makeTerminator(player);

            if (player.terminator) {
                terminatePlayer(player, player.next);
                //System.out.println(player.nodeName );
                player.terminator=false;
            }
            nextPlayer=player.next;
        }
        return nextPlayer;

    }

    /*Removes a player from the game for some reason.
     * A player is withdrawn (due to under performance) or is eliminated*/
    private void removePlayer(RENode node){
       if(node==head){
           tail.next=head.next;
           (head.next).previous=tail;
           head=head.next;
       }
       else if(node==tail){
           head.previous=tail.previous;
           (tail.previous).next=head;
           tail=tail.previous;

       }
       else {
           node.next.previous =node.previous;
           node.previous.next = node.next;
       }
        size--;

    }

    public String toString(){
        if (head == null)
            return "{}";
        else {
            String players = "{" + head;
            RENode current = head.next;
            for (int i = 1; i < size; i++) {
                players = players + ", " + current.toString();
                current = current.next;
            }
            players = players + "}";
            return players;
        }
    }

    /* Uses a GameIterator to get the index of a given Player*/
    int getNodeIndex(RENode node){
            GameIterator it =iterator();
            int indice = 0;
            RENode current = it.current;
                while (it.hasNext()) {
                    if (node == current) {
                        indice = it.currentIndex;
                        break;
                    }
                    current = current.next;
                    it.currentIndex++;
                }
            return indice;
        }
    

    /*Uses a GameIterator to get the player at a specific index*/
    RENode getNodeAt(int index){
            if(index<0 || index>size){
                throw new IndexOutOfBoundsException("index =" +  index + " and should be between 0 and size: " + size);
            }
            if(index ==0){
                return head;
            }
            GameIterator it=iterator();
            RENode player = head.next;
            it.index=1;
            while(it.hasNext()) {
                    if (index==it.index) {
                        return player;
                    }
                    player = player.next;
                    it.index++;
            }
            return player;
    }

    /*Returns the number of players in the game*/
    public int size() {
        return size;
    }

    public GameIterator iterator() {
        return new GameIterator(size);
    }

    private class GameIterator implements Iterator {
        private RENode current =head;
        int currentIndex = 0;
        int index = 0;

        /* No argument constructor for the iterator*/
        public GameIterator(){
            GameIterator itr = new GameIterator(0);
        }

        /*A constructor for the gameIterator that returns an iterator for the game */
        public GameIterator(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                        + size);
            for (int nextIndex = 0; nextIndex < index; ++nextIndex) {
                current = current.next;
            }
        }

        public boolean hasNext() {
            return current.next != null;
        }

        public RENode next() {
           RENode e=current.next;
            return e;
        }

        /* adds a player at the current iterator position*/
        public void add(String nodeName) {
            RENode player=new RENode(nodeName);

            if(current==head){
                player.next=head;
                head.previous=player;
                player.previous=tail;
                head=player;
                tail.next=head;

            }else if(current==tail){
                tail.next=player;
                player.previous=tail;
                tail=player;
                tail.next=head;
                head.previous=tail;

            }
            else{
                player.next=current;
                player.previous=current.previous;
                (current.previous).next=player;
                current.previous=player;
                current=player;
            
        }
        size++;
    }
        /* removes a node at the current iterator position*/
        public void remove() {
            (current.next).previous=current.previous;
            (current.previous).next=current.next;
            current=current.next;
            size--;
            
        }

        public boolean hasPrevious() {
            return current.previous != null;
        }

        public RENode previous() {
            return current.previous;
        }
    }
}
