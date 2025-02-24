package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tetris.Node;

public class Red extends Body {
    private int nbr = 0;
    private Node mainNode;

    public Red(){
        this.color = Color.red;
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        this.mainNode = new Node(randomNumber + 1, 1, color);
        
        nodes.add(new Node(randomNumber    , 1, color));
        nodes.add(mainNode);
        nodes.add(new Node(randomNumber + 1, 0, color));
        nodes.add(new Node(randomNumber + 2, 0, color));
    }
    
    public void rotate(int x) {
        nodes.clear();
        nbr = Math.abs(nbr + x);
        if(nbr % 2 == 1){
            nodes.add(mainNode);
            nodes.add(new Node(mainNode.x, mainNode.y + 1, color));
            nodes.add(new Node(mainNode.x + 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x + 1, mainNode.y - 1, color));
        }
        else{
            nodes.add(new Node(mainNode.x, mainNode.y + 1, color));
            nodes.add(mainNode);
            nodes.add(new Node(mainNode.x + 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x - 1, mainNode.y + 1, color));
        }
    }

    public void downOneStep() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'downOneStep'");
    }
    
}
