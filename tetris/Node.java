package tetris;
import java.awt.Color;

public class Node{
    public int x;
    public int y;
    public Color color;

    public Node(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean downOne(){
        if(y < 19){
            this.y = this.y + 1;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        return x == other.x && y == other.y;
}

    public void moveLeft(){
        this.x = this.x - 1;
    }

    public void moveRight(){
        this.x = this.x + 1;
    }
    public void moveDown(){
        this.y = this.y + 1;
    }

    public boolean sameY(int y){
        if(this.y == y){
            return true;
        }
        return false;
    }
}