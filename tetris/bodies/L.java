package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tetris.Node;

public class L extends Body {
    private Node maiNode;
    private int nbr = 0;

    public L(){
        this.color = Color.CYAN;
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        this.maiNode = new Node(randomNumber + 1, 1, color);
        nodes.add(maiNode);
        nodes.add(new Node(randomNumber, 1, color));
        nodes.add(new Node(randomNumber + 2, 1, color));
        nodes.add(new Node(randomNumber + 2, 0, color));

        
        
    }

    
    public void rotate(int x) {
        if(maiNode.x == 0 || maiNode.x == 9){
            return;
        }
        if(x < 0){
            nbr = nbr + 3;
        }
        else{
            nbr++;
        }
        nodes.clear();
        if(nbr % 4 == 0){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
        }
        else if(nbr % 4 == 1){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x, maiNode.y + 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y + 1, color));
        }
        else if(nbr % 4 == 2){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x - 1, maiNode.y + 1, color));
        }
        else if(nbr % 4 == 3){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x - 1, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y + 1, color));
        }
    }    
}
