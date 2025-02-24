package tetris;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tetris.Moving.East;
import tetris.Moving.Move;
import tetris.Moving.West;

public class Board {
    private int rows;
    private int cols;
    private int cellSize = 60;
    public Game game;
    private JFrame frame;
    private JPanel panel;
    private Move mover;
    
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.game = new Game();
        createBoard();
    }

    private void createBoard() {
        // Skapa huvudfönstret (JFrame)
        this.frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(cellSize * cols + 75, cellSize * rows + 98);

    

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

                for(Node n : game.body.nodes){
                    g.setColor(n.color);
                    g2d.fillRect(n.x * cellSize, n.y * cellSize, cellSize, cellSize);
                }


                
                for(Node n : game.getNodesOccupied()){
                    g.setColor(n.color);
                    g2d.fillRect(n.x * cellSize, n.y * cellSize, cellSize, cellSize);
                }
                
                g.setColor(Color.green);

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
            }
        };

        // Lägg till panelen i fönstret
        frame.add(panel);


        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e); // Hantera tangenttryckning
            }
        });
        // Gör fönstret synligt
        frame.setVisible(true);
    }

    public boolean update(){
        panel.repaint();
        return game.gameOver;
    }

    public void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.mover = new West();
            mover.move(game.body, cols);
            update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.mover = new East();
            mover.move(game.body, cols);
            update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            game.rotate(1);
            update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            game.rotate(-1);
            update();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            spacePressed();
            update();
          }
    }
    
    public void spacePressed(){
        game.moveToBottom();
    }
}