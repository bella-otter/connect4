import java.util.*;
public class game {
    int[][] board;
    public game() {
        board = new int[6][7];
    }
    //1=RED -1=BLUE, easier to keep track of who wins this way
    public String toString() {
        String result = "";
        for(int i=0; i<board.length; i++) {
            result += Arrays.toString(board[i]) + "\n";
        }
        return result;
    }
    public int placePiece(int place, int piece) {
        for (int i=5; i>=0; i--) {//go from the bottom until get to empty
            if (board[i][place] == 0) {
                board[i][place] = piece;
                //here we will check for the win
                return checkWin(place, i, piece);
            }
        }
        return 0;

    }
    public int checkWin(int x, int y, int piece) {
        int connect = -1;//variable to keep track of how many-
        int xx = x; //"in a row"
        //setting connect to -1 so that we count the original piece twice
        int yy = y;//variables for iterating through the matrix
        
        
        

        while(xx>=0 &&  board[yy][xx]==piece) {//iterates through matrix to left
            connect++;//                    of the piece placed
            if (connect >=4) {
                return piece;
            }
            xx--;
        }
        xx = x;
        //while loop for going to the right
        while(xx<7 && board[yy][xx]==piece) {//count og piece twice
            connect++;
            if (connect >=4) {
                return piece;
            }
            xx++;
        }
        xx = x;
        //now for vertical
        connect = 0;//only counting through once so it's gotta be 0
        while(yy<=5 && board[yy][xx]==piece) {//must be >=, otherwise
            connect++;//    wouldn't count values at 0
            if (connect >=4) {
                return piece;
            }
            yy++;
        }
        yy = y;
        //diagonal downleft
        connect = -1;
        while (yy>=0 && xx >= 0 && board[yy][xx]==piece) {
            connect++;
            if (connect >=4) {
                return piece;
            }
            yy--;
            xx--;
        }
        yy = y;
        xx = x;
        while (yy<6 && xx <7 && board[yy][xx]==piece) {
            connect++;
            if (connect >=4) {
                return piece;
            }
            yy++;
            xx++;
        }
        //now for diagonal upleft/downright
        yy = y;
        xx = x;
        connect = -1;
        while (yy<6 && xx>=0 && board[yy][xx]==piece) {
            connect++;
            if (connect >=4) {
                return piece;
            }
            yy++;
            xx--;
        }
        yy = y;
        xx = x;
        while (yy>=0 && xx<7 && board[yy][xx]==piece) {
            connect++;
            if (connect >=4) {
                return piece;
            }
            yy--;
            xx++;
        }
        //lastly, check if the board is full and return 0 for a tie if it is.
        int i = 0;
        while (i<=6&&board[0][i]!=0) {
            i++;
        }
        if (i==7) {
            return 0;//0 is tie value
        }
        return -3;//-3 signals that the game is still being played
    }
    public int miniMax(int player, int alpha, int beta, int win) {
        int value = 0;
        int winn = -3;
        if (win!=-3) {
            return win;
        }
        if (player == 1) {
            value = -4;
            for (int i = 0; i<7; i++) {
                if (board[0][i]==0) {//as long as that column isnt filled up
                    winn = placePiece(i, 1);
                    value = max(value, miniMax(-1, alpha, beta, winn));
                    remove(i);
                    
                    if (value >= beta) {
                        break;
                    }
                    alpha = max(alpha, value);
                }
            }
            //System.out.print(value +" ");
            return value;
        }
        else {
            value = 4;
            for (int i=0; i<7; i++) {
                if (board[0][i]==0) {
                    winn = placePiece(i, -1);
                    value = min(value, miniMax(1, alpha, beta, winn));
                    remove(i);
                    
                    
                    if (value <= alpha) {
                        break;
                    }
                    beta = min(value, beta);
                    
                }
            }
            //System.out.print(value +" ");
            return value;
        }
    }
    public void remove(int place) {
        int i = 0;//defining outside so that can be used out of for loop
        for (; i<6; i++) {
        if (board[i][place]!=0) {
            board[i][place] = 0;
            break;
        }
    }
    }
    public static int max(int a, int b) {
        if (a >= b) {
            return a;
        }
        return b;
    }
    public static int min(int a, int b) {
        if (a <= b) {
            return a;
        }
        return b;
    }
    public static int notPlayer(int playr) {
        if(playr == 1) {
            return -1;
        }
        else if (playr == -1) {
            return 1;
        }
        return 0;
    }
}
