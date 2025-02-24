package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tetris.Node;

public class Rosa extends Body{
    Node mainNode;
    int nbr = 0;
    
    public Rosa(){
        this.color = new Color(214, 106, 255);
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        this.mainNode = new Node(randomNumber + 1, 1, color);
        nodes.add(mainNode);
        nodes.add(new Node(mainNode.x - 1, mainNode.y, color));
        nodes.add(new Node(mainNode.x + 1, mainNode.y, color));
        nodes.add(new Node(mainNode.x, mainNode.y - 1, color));
    }

    public void rotate(int x){
        if(mainNode.x == 0 || mainNode.x == 9){
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
            nodes.add(mainNode);
            nodes.add(new Node(mainNode.x, mainNode.y - 1, color));
            nodes.add(new Node(mainNode.x, mainNode.y + 1, color));
            nodes.add(new Node(mainNode.x + 1, mainNode.y, color));
        }
        else if(nbr % 4 == 2){
            nodes.add(mainNode);
            nodes.add(new Node(mainNode.x - 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x + 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x, mainNode.y + 1, color));
        }
        else if(nbr % 4 == 3){
            nodes.add(mainNode);
            nodes.add(new Node(mainNode.x - 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x, mainNode.y - 1, color));
            nodes.add(new Node(mainNode.x, mainNode.y + 1, color));
        }
        else if(nbr % 4 == 0){
            nodes.add(mainNode);
            nodes.add(new Node(mainNode.x - 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x + 1, mainNode.y, color));
            nodes.add(new Node(mainNode.x, mainNode.y - 1, color));
        }
    }
}
