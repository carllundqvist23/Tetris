package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tetris.Node;

public class J extends Body{
    private Node maiNode;
    private int nbr = 0;

    public J(int cols){
        super(cols);
        this.color = Color.white;
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(cols - 2) + 1;
        this.maiNode = new Node(randomNumber + 1, 1, color);
        nodes.add(maiNode);
        nodes.add(new Node(maiNode.x - 1, maiNode.y - 1, color));
        nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
        nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
    }

    
    public void rotate(int x) {
        if(maiNode.x == 0 || maiNode.x == cols - 1){
            return;
        }
        if(x < 0){
            nbr = nbr + 3;
        }
        else{
            nbr++;
        }
        nodes.clear();
        if(nbr % 4 == 1){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y + 1, color));
        }
        else if(nbr % 4 == 2){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y + 1, color));
        }
        else if(nbr % 4 == 3){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x - 1, maiNode.y + 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y + 1, color));
        }
        else if(nbr % 4 == 0){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x - 1, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
        }
    }    
}
