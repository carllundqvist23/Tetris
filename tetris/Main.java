package tetris;

import tetris.bodies.Body;

public class Main {
  private static Player player = new Player();
  private static Game game = new Game(20, player);
  private static Board board = new Board(20, 20, game);
  private static Body body = game.newBody();
  
    public static void main(String[] args) {
        
        while(!game.gameOver){
          try {
            long now = System.currentTimeMillis();
            long dead = now + 400 - player.getPoints() % 10;
            board.paint();
            now = System.currentTimeMillis();
            if (dead > now){
              Thread.sleep(dead - now);
            }
            game.move(19, body);
          } 
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Game Over!");
    }
}
