public class position {
    boolean available;
    char val;
    Player playedby;

    public position(Player playedby)
    {
        this.available = true;
        this.val = playedby.getBoardpiece();
        this.playedby = playedby;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public char getVal() {
        return this.val;
    }

    public Player getPlayer() {
        return this.playedby;
    }

    public void IsUsed(){
        this.available = false;
    }
}
