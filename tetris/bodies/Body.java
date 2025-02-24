package tetris.bodies;

import java.util.List;

import tetris.Node;

import java.awt.Color;

public abstract class Body {
    public List<Node> nodes;
    protected Node main;
    protected Color color;

    public abstract void rotate(int x);
    public void downOneStep(){
        for(Node n : nodes){
            if(n.downOne() == false){
                break;
            }
        }
    }

    public boolean isGameOver(){
        for(Node n : nodes){
            if(n.y == 0){
                return true;
            }
        }
        return false;
    }

    
    public boolean reachedBottom(){
        for(Node n : nodes){
            if(n.y == 19){
                return true;
            }
        }
        return false;
    }

    public void moveDown(){
        for(Node n :  nodes){
            n.moveDown();
        }
    }
        
}
