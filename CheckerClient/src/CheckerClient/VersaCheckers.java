package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaCheckers
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VersaCheckers extends JPanel{
    public static final int EMPTY = 0;
    public static final int RED= 1;
    public static final int RKing = 2;
    public static final int BLUE = 3;
    public static final int BKing = 4;

    public static final boolean PLAYER = true;
    public static final boolean BOTPLAYER = false;

    private int[][] board;
    private String player1 = "";
    private String player2 = "";
    private boolean turn = true;
    public boolean gameover = false;

    private int[] selected = null;

    private Image red_piece = null;
    private Image blue_piece = null;
    private Image red_piece_king = null;
    private Image blue_piece_king = null;

    public VersaCheckers(){

        try {
            red_piece = ImageIO.read(this.getClass().getResource("images/red_piece.png"));
            blue_piece = ImageIO.read(this.getClass().getResource("images/blue_piece.png"));
            red_piece_king = ImageIO.read(this.getClass().getResource("images/red_piece_king.png"));
            blue_piece_king = ImageIO.read(this.getClass().getResource("images/blue_piece_king.png"));
        } catch (IOException e) {
            System.out.println("could not open file");
        }

        int [][] temp = {
                {3, 0, 3, 0, 3, 0, 3, 0},
                {0, 3, 0, 3, 0, 3, 0, 3},
                {3, 0, 3, 0, 3, 0, 3, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1}
        };
        board = temp;
        player1 = "player";
        player2 = "bot";
        turn = true;
    }

    private String boardToString(int[][] b){
        String save = "[";
        for(int [] y : b){
            save += "[";
            for (int x: y){
                save += x+",";
            }
            save = save.substring(0,save.length()-1);
            save += "],";
        }
        save = save.substring(0, save.length()-1);
        save += "]";
        return save;
    }

    public String getRotated(String current){
        String save = current;
        save = save.replace("1", "-1");
        save = save.replace("3", "1");
        save = save.replace("-1", "3");
        save = save.replace("2", "-2");
        save = save.replace("4", "2");
        save = save.replace("-2", "4");
        save = new StringBuffer(save).reverse().toString();
        save = save.replace("[", "*");
        save = save.replace("]", "[");
        save = save.replace("*", "]");
        return save;
    }

    public void rotateBoard(){
        String res = getRotated(boardToString(getBoard()));
        int[][] realBoard = new int[8][8];
        res = res.substring(1,res.length()-1);
        String[] rows = res.split("\\],\\[");
        rows[0] = rows[0].substring(1, rows[0].length());
        rows[7] = rows[7].substring(0, rows[7].length()-1);

        for (int y = 0; y < 8; y++) {
            String chars[] = rows[y].split(",");
            for (int x = 0; x < 8; x++) {
                realBoard[y][x] = Integer.parseInt(chars[x]);
            }
        }
        setBoard(realBoard);
    }

    public boolean getTurn(){
        return turn;
    }

    public void setTurn(boolean x){
        turn = x;
    }

    public int getBlueScore(){
        int score = 0;
        for (int row = 0; row < 8; ++row){
            for (int col = 0; col < 8; ++col){
                if (board[row][col] == 3 ||board[row][col] == 4)
                    ++score;
            }
        }
        return score;
    }

    public int getRedScore(){
        int score = 0;
        for (int row = 0; row < 8; ++row){
            for (int col = 0; col < 8; ++col){
                if (board[row][col] == 1||board[row][col] == 2)
                    ++score;
            }
        }
        return score;
    }



    public int[] getSelected() {
        return selected;
    }

    public void setSelected(int x, int y) {
        if (x == -1 && y == -1) {
            selected = null;
        } else {
            selected = new int[2];
            selected[0] = x;
            selected[1] = y;
        }
        repaint();
    }

    public void addPiece(int x, int y, int type) {
        if (board[y][x] == 0 && (type == 1 || type == 2 || type == 3 || type ==3)) {
            board[y][x] = type;
            repaint();
        } else {
            System.err.println("Error: A piece is already there");
        }
    }

    public int removePiece(int x, int y) {
        if (board[y][x] != 0) {
            int res = board[y][x];
            board[y][x] = 0;
            repaint();
            return res;
        } else {
            System.err.println("Error: no piece exists there");
            return 0;
        }
    }

    public int[][] getBoard() {
        int[][] b = new int[8][8];
        for (int y = 0; y < 8; y++) {
            System.arraycopy(board[y], 0, b[y], 0, 8);
        }
        return b;
    }

    public String getFormattedBoard() {
        String res = "[";
        for (int[] y : board) {
            res += "[";
            for (int x: y) {
                res += x+",";
            }
            res = res.substring(0, res.length()-1);
            res += "],";
        }
        res = res.substring(0, res.length()-1);
        res += "]";
        return res;
    }

    public void setBoard(int[][] newBoard) {
        this.board = newBoard;
        repaint();
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Draw the background
        g.setColor(Color.BLACK);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x+y) % 2 == 0) {
                    g.fillRect(x*50, y*50, 50, 50);
                }
            }
        }

        //Draw the selection
        g.setColor(Color.GREEN);
        if (selected != null) {
            g.fillRect(selected[0]*50, selected[1]*50, 50, 50);
        }

        //Draw the pieces
        if (board != null) {
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board.length; x++) {
                    if (board[y][x] == 1) {
                        g.drawImage(red_piece, x*50+5, y*50+5, x*50+45, y*50+45,
                                0, 0, red_piece.getWidth(null), red_piece.getHeight(null), null);
                    } else if (board[y][x] == 2) {
                        g.drawImage(red_piece_king, x*50+5, y*50+5, x*50+45, y*50+45,
                                0, 0, red_piece_king.getWidth(null), red_piece_king.getHeight(null), null);
                    } else if (board[y][x] == 3) {
                        g.drawImage(blue_piece, x*50+5, y*50+5, x*50+45, y*50+45,
                                0, 0, blue_piece.getWidth(null), blue_piece.getHeight(null), null);
                    } else if (board[y][x] == 4) {
                        g.drawImage(blue_piece_king, x*50+5, y*50+5, x*50+45, y*50+45,
                                0, 0, blue_piece_king.getWidth(null), blue_piece_king.getHeight(null), null);
                    }
                }
            }
        }
    }
}
