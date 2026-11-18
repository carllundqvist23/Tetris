package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tetris.bodies.Body;


public class Board {
    private int rows;
    private int cols;
    private int cellSize = 80;
    public Game game;
    private JFrame frame;
    private JPanel panel;
    
    public Board(int rows, int cols, Game game) {
        this.rows = rows;
        this.cols = cols;
        this.game = game;
        createBoard();
    }

    private void createBoard() {
        // Skapa huvudfönstret (JFrame)
        this.frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(cellSize * cols, cellSize * rows + 75);

    

        // Skapa en anpassad JPanel för att rita rutnätet
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0x28, 0x27, 0x27));
                super.paintComponent(g); // Målar panelen
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                g.fillRect(0, 0, cellSize * cols + 75, cellSize * rows + 105);
                for(Body b : game.bodies){
                    for(Node n : b.nodes){
                        g.setColor(n.color);
                        g2d.fillRect(n.x * cellSize, n.y * cellSize, cellSize, cellSize);
                    }
                    

                }
                for(Node n : game.getNodesOccupied()){
                    g.setColor(n.color);
                    g2d.fillRect(n.x * cellSize, n.y * cellSize, cellSize, cellSize);
                }

                
                
                g.setColor(Color.pink);

                // Rita horisontella linjer
                for (int i = 0; i <= rows; i++) {
                    int y = i * cellSize;
                    g2d.drawLine(0, y, width, y);
                }


                // Rita vertikala linjer
                for (int i = 0; i <= cols; i++) {
                    int x = i * cellSize;
                    g2d.drawLine(x, 0, x, height);
                }

                for(Node n : game.predictionCoordinates()){
                    g2d.setColor(n.color);
                    //g2d.drawRect(n.x *cellSize, n.y *cellSize, cellSize, cellSize);
                    g2d.fillRect(n.x *cellSize + 10, n.y *cellSize + 10, cellSize - 20, cellSize - 20);
                    //g2d.fillOval(n.x * cellSize + 15, n.y * cellSize + 15, 30, 30);
                }
            }
        };

        // Lägg till panelen i fönstret
        frame.add(panel);


        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                game.handleKeyPress(e); // Hantera tangenttryckning
                paint();
            }
        });
        // Gör fönstret synligt
        frame.setVisible(true);
    }

    public boolean paint(){
        panel.repaint();
        return game.gameOver;
    }
}