package tetris;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tetris.bodies.Body;

public class Predictor{
    private List<Node> bodyNodes;
    private List<Node> nodesOccupied;

    public Predictor(Body body, List<Node> nodesOccuupied){
        bodyNodes = new ArrayList<>();
        for(Node n : body.nodes){
            Node copyNode = new Node(n.x, n.y, n.color);
            bodyNodes.add(copyNode);
        }
        this.nodesOccupied = nodesOccuupied;
    }

    public boolean oneStepDownOkay(){
        for(Node n : bodyNodes){
            Node node = new Node(n.x, n.y + 1, Color.BLACK);
            if(nodesOccupied.contains(node) || n.y == 19){
                return false;
            }
        }
        return true;
    }

    public List<Node> returnCoordinates(){
        while(oneStepDownOkay()){
            for(Node n : bodyNodes){
                n.downOne();
            }
        }
        return bodyNodes;
    }
}