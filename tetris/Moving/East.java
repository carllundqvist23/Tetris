package tetris.Moving;

import tetris.Node;
import tetris.bodies.Body;

public class East implements Move {
    public East(){}

    @Override
    public void move(Body body, int width) {
        if(okayToMoveRight(body, width)){
            for(Node n : body.nodes){
                n.moveRight();
            }
        }
    }

    private boolean okayToMoveRight(Body body, int width){
        for(Node n : body.nodes){
            if(n.x == width - 1){
                return false;
            }
        }
        return true;
    }
    
}