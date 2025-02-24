package tetris.bodies;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tetris.Node;

public class Cross extends Body {

    public Cross(){
        this.color = new Color(214, 106, 255);
        this.nodes = new ArrayList<>();
        Random random = new Random();
        int randomNumber = random.nextInt(7);
        nodes.add(new Node(randomNumber + 1, 1, color));
        nodes.add(new Node(randomNumber + 1, 0, color));
        nodes.add(new Node(randomNumber + 1, 2, color));
        nodes.add(new Node(randomNumber, 1, color));
        nodes.add(new Node(randomNumber + 2, 1, color));
    }

    @Override
    public void rotate(int x) {}
    
}
