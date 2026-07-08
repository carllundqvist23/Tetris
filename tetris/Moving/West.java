package tetris.Moving;

import tetris.Node;
import tetris.bodies.Body;

public class West implements Move {
    
    public West(){}

    @Override
    public void move(Body body, int width) {
        if(okayToMoveLeft(body)){
            for(Node n : body.nodes){
                n.moveLeft();
            }
        }
    }

    private static boolean okayToMoveLeft(Body body){
        for(Node n : body.nodes){
            if(n.x == 0){
                return false;
            }
        }
        return true;
    }
    
}
