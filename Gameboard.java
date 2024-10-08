package com.example.grid;

public class Gameboard {

    private Player[][] grid;
    private final int DIM = 5;
    private Player whoseTurnIsIt;

    public Gameboard() {
        //Create a 5x5 gameboard of BLANK cells
        grid = new Player[DIM][DIM];
        for (int i = 0; i < DIM; ++i) {
            for (int j = 0; j < DIM; ++j) {
                grid[i][j] = Player.BLANK;
            }
        }

        //Arbitrarily, we make X the first player.
        whoseTurnIsIt = Player.X;
    }

    public void submitMove(char move, Player p) {
        if (move >= '1' && move <= '5') {
            //vertical move, move tokens down
            int col = (int) (move - '1');
            Player newVal = p;
            for (int i = 0; i < DIM; ++i) {
                if (grid[i][col] == Player.BLANK) {
                    grid[i][col] = newVal;
                    break;
                } else {
                    Player tmp = grid[i][col];
                    grid[i][col] = newVal;
                    newVal = tmp;
                }
            }

        } else { //A-E
            //horizontal move, move tokens right
            int row = (int) (move - 'A');
            Player newVal = p;
            for (int i = 0; i < DIM; ++i) {
                if (grid[row][i] == Player.BLANK) {
                    grid[row][i] = newVal;
                    break;
                } else {
                    Player tmp = grid[row][i];
                    grid[row][i] = newVal;
                    newVal = tmp;
                }
            }
        }

        //change whose turn it is
        if (whoseTurnIsIt == Player.X) {
            whoseTurnIsIt = Player.O;
        } else {
            whoseTurnIsIt = Player.X;
        }
    }

    public Player checkForWin() {
        boolean XWins = false;
        boolean OWins = false;
        //check all rows
        for (int i = 0; i < DIM; ++i) {
            if (grid[i][0] != Player.BLANK) {
                Player winner = grid[i][0];
                for (int j = 0; j < DIM; ++j) {
                    if (grid[i][j] != winner) {
                        winner = Player.BLANK;
                        break;
                    }
                }
                if (winner != Player.BLANK) {
                    if (winner == Player.X) {
                        XWins = true;
                    }
                    if (winner == Player.O) {
                        OWins = true;
                    }//5 in a row!

                }

            }
        }

        //check all columns
        for (int i = 0; i < DIM; ++i) {
            if (grid[0][i] != Player.BLANK) {
                Player winner = grid[0][i];
                for (int j = 0; j < DIM; ++j) {
                    if (grid[j][i] != winner) {
                        winner = Player.BLANK;
                        break;
                    }
                }
                if (winner != Player.BLANK) {
                    if (winner == Player.X) {
                        XWins = true;
                    }
                    if (winner == Player.O) {
                        OWins = true;
                    } //5 in a column!
                }
            }
        }

        //check top-left -> bottom-right diagonal
        if (grid[0][0] != Player.BLANK) {
            Player winner = grid[0][0];
            for (int i = 0; i < DIM; ++i) {
                if (grid[i][i] != winner) {
                    winner = Player.BLANK;
                    break;
                }
            }
            if (winner != Player.BLANK) {
                if (winner == Player.X) {
                    XWins = true;
                }
                if (winner == Player.O) {
                    OWins = true;
                } //5 in a diagonal!
            }
        }

        //check bottom-left -> top-right diagonal
        if (grid[DIM - 1][0] != Player.BLANK) {
            Player winner = grid[DIM - 1][0];
            for (int i = 0; i < DIM; ++i) {
                if (grid[DIM - 1 - i][i] != winner) {
                    winner = Player.BLANK;
                    break;
                }
            }
            if (winner != Player.BLANK) {
                if (winner == Player.X) {
                    XWins = true;
                }
                if (winner == Player.O) {
                    OWins = true;
                } //5 in a diagonal!
            }
        }

        if (XWins && ! OWins){
            return Player.X;
        }
        else if (OWins && ! XWins){
            return Player.O;
        }
        else if (OWins && XWins){
            return Player.TIE;
        } else { return Player.BLANK;
        }
    }

    public void consoleDraw() {
        System.out.print("  ");
        for (int i = 0; i < DIM; ++i) {
            System.out.print(i + 1);
        }
        System.out.println();
        System.out.print(" /");
        for (int i = 0; i < DIM; ++i) {
            System.out.print("-");
        }
        System.out.println("\\");
        for (int i = 0; i < DIM; ++i) {
            System.out.print(((char) ('A' + i)) + "|");
            for (int j = 0; j < DIM; ++j) {
                if (grid[i][j] == Player.BLANK) {
                    System.out.print(" ");
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println("|");
        }
        System.out.print(" \\");
        for (int i = 0; i < DIM; ++i) {
            System.out.print("-");
        }
        System.out.println("/");

    }

    public Player getCurrentPlayer() {
        return whoseTurnIsIt;
    }
}
