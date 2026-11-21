package tetris;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tetris.Moving.East;
import tetris.Moving.Move;
import tetris.Moving.West;


import tetris.bodies.Body;
import tetris.bodies.Green;
import tetris.bodies.J;
import tetris.bodies.L;
import tetris.bodies.Red;
import tetris.bodies.Rosa;
import tetris.bodies.Square;
import tetris.bodies.Stick;

public class Game {
    private List<Node> nodesOccupied = new ArrayList<>();
    public  List<Body> bodies = new ArrayList<>();
    public List<Predictor> predictors = new ArrayList<>();
    public boolean gameOver = false;
    private int cols;
    private Player player;
    private Move mover;
    
    public Game(int cols, Player player){
        this.cols = cols;
        this.player = player;
        bodies.add(new Rosa(cols));
        bodies.add(new Rosa(cols));
        predictors.add(new Predictor(bodies.get(0), nodesOccupied));
        predictors.add(new Predictor(bodies.get(1), nodesOccupied));
    }
    
    public void move(int index){
        Body body = bodies.get(index);
        if(!oneStepDownOkay(body)){
            endOfBody(body, index);
        }
        else{
            body.moveDown();
        }
    }

    public boolean oneStepDownOkay(Body body){
        for(Node n : body.nodes){
            Node node = new Node(n.x, n.y + 1, Color.BLACK);
            if(nodesOccupied.contains(node) || n.y == 19){
                return false;
            }
        }
        return true;
    }

    public void endOfBody(Body body, int index){
        int nbrErasedRows = 0;
        for(Node n : body.nodes){
            nodesOccupied.add(n);
        }
        for(Node n : body.nodes){
            if(wholeRowOccupied(n.y)){
                nbrErasedRows++;
                eraseRow(n.y);
            }
        }
        player.updatePts(nbrErasedRows * 10);
        gameOver = body.isGameOver();
        newBody(index);

        //predictor = new Predictor(body, nodesOccupied);
    }

    public void moveToBottom(Body body, int index){
        while(oneStepDownOkay(body)){
            body.moveDown();
        }
        endOfBody(body, index);
    }

    public List<Node> getNodesOccupied(){
        return nodesOccupied;
    }

    public void newBody(int index){
        Random random = new Random();
        int x = random.nextInt(7);
        Body body;
        if(x == 0){
            body = new Rosa(cols);
        }
        else if(x == 1){
            body = new L(cols);
        }
        else if(x == 2){
            body = new Red(cols);
        }
        else if(x == 3){
            body = new Square(cols);
        }
        else if(x == 4){
            body = new J(cols);
        }
        else if(x == 5){
            body = new Green(cols);
        }
        else{
            body = new Stick(cols);
        }
        predictors.set(index, new Predictor(body, nodesOccupied));
        bodies.set(index, body);
    }


    public void rotate(int x, Body body){
        body.rotate(x);
    }

    public boolean wholeRowOccupied(int y){
        List<Node> listOfNodesOnRowY = wholeRowOfNodes(y);
        for(Node n : listOfNodesOnRowY){
            if(!nodesOccupied.contains(n)){
                return false;
            }
        }
        return true;
    } 
    
    public List<Node> wholeRowOfNodes(int y){
        List<Node> list = new ArrayList<>();
        for(int x = 0; x < cols; x++){
            list.add(new Node(x, y, Color.BLACK));
        }
        return list;
    }

    public void eraseRow(int y){
        List<Node> temporary = new ArrayList<>();
        for(Node n : nodesOccupied){
            if(n.sameY(y)){
                temporary.add(n);
            }
        }
        for(Node n : temporary){
            nodesOccupied.remove(n);
        }
        for(Node n : nodesOccupied){
            if(n.y < y){
                n.y = n.y + 1;
            }
        }
    }

    public List<Node> predictionCoordinates(){
        List<Node> bothPredictors = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            for(Node n : predictors.get(i).returnCoordinates()){
                bothPredictors.add(n);
            }
        }

        return bothPredictors;
    }

    public void updatePredictor(int index){
        predictors.set(index, new Predictor(bodies.get(index), nodesOccupied));
    }

    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.mover = new West();
            mover.move(bodies.get(0), cols);
            updatePredictor(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.mover = new East();
            mover.move(bodies.get(0), cols);
            updatePredictor(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            rotate(1, bodies.get(0));
            updatePredictor(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            rotate(-1, bodies.get(0));
            updatePredictor(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moveToBottom(bodies.get(0), 0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            this.mover = new West();
            mover.move(bodies.get(1), cols);
            updatePredictor(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            this.mover = new East();
            mover.move(bodies.get(1), cols);
            updatePredictor(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W){
            rotate(1, bodies.get(1));
            updatePredictor(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            rotate(-1, bodies.get(1));
            updatePredictor(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_Q){
            moveToBottom(bodies.get(1), 1);
        }
    }
}