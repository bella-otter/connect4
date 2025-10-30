import java.util.*;
public class test {
    public static void main (String[] args) {
        game g = new game();
        
        //little simulation of a game
      
  g.placePiece(0, 2);
  g.placePiece(0, 2);
  g.placePiece(0, 2);
  g.placePiece(0, 2);
  g.placePiece(0, 2);
  g.placePiece(0, 2);
  g.placePiece(6, 2);
  g.placePiece(6, 2);
  g.placePiece(6, 2);
  g.placePiece(6, 2);
  g.placePiece(6, 2);
  g.placePiece(6, 2);
  g.placePiece(5, 2);
  g.placePiece(5, 2);
  g.placePiece(5, 2);
  g.placePiece(5, 2);
  g.placePiece(5, 2);
  g.placePiece(5, 2);
  //g.placePiece(3, 1);//computer will be 1
    System.out.println(g);
    int bestMove = -2;
    int val = 0;
    int bestval = -3;
    int p = 1;
    int win = -10;
    Scanner scan = new Scanner(System.in);
    //System.out.println("(-1) move: ");
    //int move = scan.nextInt();
    for (int i=1; i<=5; i++) {
      if (g.board[0][i]==0) {
        win = g.placePiece(i, 1);
        val = g.miniMax(-1, -3, 3, win);
        System.out.println(i +" "+ val);
        g.remove(i);
        if (val>bestval) {
          bestval = val;
          bestMove = i;
        }
      }
    }
    int move = bestMove;
    bestval = -2;
      while(g.placePiece(move, p)==-3) {
          System.out.println(g);
          if (p==-1) {
              p=1;
              for (int i=1; i<=5; i++) {
                if (g.board[0][i]==0) {
                  win = g.placePiece(i, 1);
                  val = g.miniMax(-1, -3, 3, win);
                  System.out.println(i +" "+ val);
                  g.remove(i);
                  if (val>bestval) {
                    bestval = val;
                    bestMove = i;
                  }
                }
              }
              move = bestMove;
              bestval = -2;
          }
          else {
              p = -1;
              System.out.println("(-1) move: ");
              move = scan.nextInt();
          }
      }
      System.out.println(g);
      scan.close();

    }
}
