package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import tetris.Node;

public class Stick extends Body{
    private Node maiNode;
    private int nbr = 0;

    public Stick(int cols){
        super(cols);
        this.color = new Color(98, 247, 0);
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(cols - 5) + 3;
        this.maiNode = new Node(randomNumber + 1, 1, color);
        nodes.add(maiNode);
        nodes.add(new Node(maiNode.x - 2, maiNode.y, color));
        nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
        nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
        nodes.add(new Node(maiNode.x + 2, maiNode.y, color));
    }
    
    public void rotate(int x) {
        if(maiNode.x < 2 || maiNode.x > cols - 3){
            return;
        }
        nbr++;
        nodes.clear();
        if(nbr % 2 == 1){
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x, maiNode.y - 2, color));
            nodes.add(new Node(maiNode.x, maiNode.y - 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y + 1, color));
            nodes.add(new Node(maiNode.x, maiNode.y + 2, color));
        }
        else{
            nodes.add(maiNode);
            nodes.add(new Node(maiNode.x - 2, maiNode.y, color));
            nodes.add(new Node(maiNode.x - 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x + 1, maiNode.y, color));
            nodes.add(new Node(maiNode.x + 2, maiNode.y, color));
        }
    }
}