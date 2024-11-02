package TicTacToeProject;
import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel statusPanel  = new JPanel();
    JPanel gameBoard = new JPanel(new GridLayout(3,3));
    JButton[][] tiles = new JButton[3][3];
    JLabel turnIndicator = new JLabel("Tic Tac Toe");

    boolean gameOver = false;
    boolean isTied = false;
    int turn = 0;
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    int playerX_Score = 0;
    int playerO_Score = 0;

    public TicTacToe() {
        frame.setResizable(false); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,450);

        turnIndicator.setForeground(Color.WHITE);
        statusPanel.add(turnIndicator);
        statusPanel.setBackground(Color.DARK_GRAY);
        
        for(int r = 0; r < tiles.length; ++r) {
            for(int c = 0; c < tiles[r].length; ++c) {
                JButton tile = new JButton();
                tile.setBackground(Color.DARK_GRAY);
                tile.setFont(new Font("Times New Roman",Font.BOLD,50));
                tile.setForeground(Color.WHITE);
                tile.setFocusable(false);
                tiles[r][c] = tile;
                gameBoard.add(tile);
                tile.addActionListener(e -> {
                    JButton clickedTile = (JButton)e.getSource();
                    if(clickedTile.getText().isEmpty()) {
                        ++turn;
                        clickedTile.setText(currentPlayer);
                        checkWinner();
                        gameOver();
                    if(!gameOver) {
                        currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                        turnIndicator.setText(currentPlayer+" 's turn");
                    }
                       
                    }       
                });
            }
        }
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(gameBoard, BorderLayout.CENTER); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private boolean gameOver() {
       if(gameOver) {
            if(isTied) {}
            else if(currentPlayer.equals(playerX)) ++playerX_Score;
            else ++playerO_Score;
            Timer timer = new Timer(2000, e -> { // 2000 ms delay (2 seconds)
                String msg = "Game Over :( \n----- SCORE BOARD -----\n      playerX_Score : " 
                            + playerX_Score + "\n     playerO_Score : " 
                            + playerO_Score + "\nDo you wanna play again?";
    
                int option = JOptionPane.showConfirmDialog(frame, msg, "Game Over", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            });
            timer.setRepeats(false); // Make sure the timer only runs once
            timer.start();
        }
        return gameOver;
    }
    private void resetGame() {
        for(int r = 0; r < tiles.length; r++) {
            for(int c = 0; c < tiles[r].length; c++) {
                JButton temp = tiles[r][c];
                temp.setText("");
                temp.setForeground(Color.WHITE);
                temp.setBackground(Color.DARK_GRAY);
            }
        }

        gameOver = false;
        isTied = false;
        turn = 0;
        turnIndicator.setText("Tic Tac Toe");
        turnIndicator.setForeground(Color.WHITE);
    }
    private void checkWinner() {

       // Winner in row 
       for(int r = 0; r < tiles.length; r++) {
            if(tiles[r][0].getText().isEmpty()) continue;
            if(tiles[r][0].getText().equals(tiles[r][1].getText()) &&
               tiles[r][1].getText().equals(tiles[r][2].getText())) {
                    for(int i = 0; i < 3; ++i) {
                        setWinner(tiles[r][i]);
                    }
                    turnIndicator.setText(currentPlayer+" is the Winner !");
                    turnIndicator.setForeground(Color.GREEN);
                    gameOver = true;
                    return;
            }
       }

       // Winner in column
       for(int c = 0; c < tiles.length; c++) {
            if(tiles[0][c].getText().isEmpty()) continue;
            if(tiles[0][c].getText().equals(tiles[1][c].getText()) &&
               tiles[1][c].getText().equals(tiles[2][c].getText())) {
                    for(int j = 0; j < 3; j++) {
                        setWinner(tiles[j][c]);
                    }
                    turnIndicator.setText(currentPlayer+" is the Winner !");
                    turnIndicator.setForeground(Color.GREEN);
                    gameOver = true;
                    return;
            }
        }

        // Winner in diagonal
        if(!tiles[0][0].getText().isEmpty() && 
            tiles[0][0].getText().equals(tiles[1][1].getText()) &&
            tiles[1][1].getText().equals(tiles[2][2].getText())) {
                setWinner(tiles[0][0]);
                setWinner(tiles[1][1]);
                setWinner(tiles[2][2]);
                turnIndicator.setText(currentPlayer+" is the Winner !");
                turnIndicator.setForeground(Color.GREEN);
                gameOver = true;
                return;
        }

        // Winner in antiDiagonal
        if(!tiles[0][2].getText().isEmpty() && 
            tiles[0][2].getText().equals(tiles[1][1].getText()) &&
            tiles[1][1].getText().equals(tiles[2][0].getText())) {
                setWinner(tiles[0][2]);
                setWinner(tiles[1][1]);
                setWinner(tiles[2][0]);
                turnIndicator.setText(currentPlayer+" is the Winner !");
                turnIndicator.setForeground(Color.GREEN);
                gameOver = true;
                return;
        }

        // else Tie
        if(turn == 9) {
            for(int r = 0; r < tiles.length; r++) {
                for(int c = 0; c < tiles[r].length; c++) {
                     tiles[r][c].setForeground(Color.ORANGE);
                     tiles[r][c].setBackground(Color.GRAY);
                }
            }
            turnIndicator.setText("Tie !");
            turnIndicator.setForeground(Color.ORANGE);
            isTied = true;
            gameOver = true;
        }

    }
    private void setWinner(JButton tile) {
         tile.setForeground(Color.decode("#124429"));
         tile.setBackground(Color.GRAY);
    }
    public static void main(String[] args) {
        new TicTacToe();
    }
}