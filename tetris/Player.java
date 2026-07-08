package tetris;

public class Player {
    private int pts;

    public Player(){
        this.pts = 0;
    }

    public void updatePts(int pts){
        this.pts += pts;
    }

    public int getPoints(){
        return this.pts;
    }
}
