package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tetris.Node;

public class Square extends Body {
    public Square(){
        this.color = Color.yellow;
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(8);
        nodes.add(new Node(randomNumber + 0, 1, color));
        nodes.add(new Node(randomNumber + 1, 1, color));
        nodes.add(new Node(randomNumber + 0, 0, color));
        nodes.add(new Node(randomNumber + 1, 0, color));
    }
    public void rotate(int x){}

    public void moveLeft(){
        for(Node n : nodes){
            n.moveLeft();
        }
    }

    public void moveRight(){
        for(Node n : nodes){
            n.moveRight();
        }
    }
}
