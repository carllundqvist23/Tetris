package tetris;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tetris.bodies.Body;
import tetris.bodies.J;
import tetris.bodies.L;
import tetris.bodies.Red;
import tetris.bodies.Rosa;
import tetris.bodies.Square;
import tetris.bodies.Stick;

public class Game {
    private List<Body> bodies = new ArrayList<>();
    private List<Node> nodesOccupied = new ArrayList<>();
    public Body body;
    public boolean gameOver = false;
    private int cols;

    public Game(int cols){
        this.cols = cols;
        this.body = newBody();
    }


    public boolean oneStepDownOkay(){
        for(Node n : body.nodes){
            Node node = new Node(n.x, n.y + 1, Color.BLACK);
            if(nodesOccupied.contains(node) || n.y == 19){
                return false;
            }
        }
        return true;
    }

    public void moveToBottom(){
        while(oneStepDownOkay()){
            body.moveDown();
        }
        endOfBody();
    }

    public List<Body> getBodies(){
        return bodies;
    }

    public List<Node> getNodesOccupied(){
        return nodesOccupied;
    }

    public Body newBody(){
        Random random = new Random();
        int x = random.nextInt(7);
        Body body;
        if(x == 0){
            body = new Rosa(cols);
            bodies.add(body);
        }
        else if(x == 1){
            body = new Rosa(cols);
            bodies.add(body);
        }
        else if(x == 2){
            body = new L(cols);
            bodies.add(body);
        }
        else if(x == 3){
            body = new Red(cols);
            bodies.add(body);
        }
        else if(x == 4){
            body = new Square(cols);
            bodies.add(body);
        }
        else if(x == 5){
            body = new J(cols);
            bodies.add(body);
        }
        else{
            body = new Stick(cols);
            bodies.add(body);
        }

        return body;
    }

    public void endOfBody(){
        for(Node n : body.nodes){
            nodesOccupied.add(n);
        }
        for(Node n : body.nodes){
            if(wholeRowOccupied(n.y)){
                eraseRow(n.y);
            }
        }
        gameOver = body.isGameOver();
        body = newBody();
    }

    public void move(int x){
        if(!oneStepDownOkay()){
            endOfBody();
        }
        else{
            body.moveDown();
        }
    }

    public void rotate(int x){
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
        for(int x = 0; x < 35; x++){
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
}
