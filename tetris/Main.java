package tetris;

public class Main {
    private static Board board = new Board(20, 10);
  
    public static void main(String[] args) {
        boolean gameOver = false;
        
        while(!gameOver){
          try {
            long now = System.currentTimeMillis();
            long dead = now + 400 - board.player.getPoints() % 10;
            gameOver = board.update();
            now = System.currentTimeMillis();
            if (dead > now){
              Thread.sleep(dead - now);
            }
            
            
            board.game.move(19);
            
 
          } 
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println("Game Over!");
    }
}
