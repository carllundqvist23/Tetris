package tetris;

public class Main {
    private static Board board = new Board(20, 10);
  
    public static void main(String[] args) {
        boolean gameOver = false;
        while(!gameOver){
          try {
            Thread.sleep(200 - board.player.getPoints());
            gameOver = board.update();
            Thread.sleep(200 - board.player.getPoints());
 
          } 
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        
          board.game.move(19);
          gameOver = board.update();
        }
        System.out.println("Game Over!");
    }
}
