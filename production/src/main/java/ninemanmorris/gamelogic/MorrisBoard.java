package ninemanmorris.gamelogic;

import java.util.ArrayList;

import ninemanmorris.move.Move;

/**
 * Represents the 9 men's morris board
 */
public class MorrisBoard {
    
    private static final int BOARD_LENGTH = 7;
    private static final int BOARD_WIDTH = 7;

    private Position[][] board;

    private boolean switchTurn;

    /**
     * Constuctor for creating a MorrisBoard
     */
    public MorrisBoard() {
        board = new Position[BOARD_LENGTH][BOARD_WIDTH];
        switchTurn = false;

        createPositions();
        createNeighbours();
    }

    /**
     * Create neighbours for every position on the board
     */
    private void createNeighbours() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setHorizontalNeighbours(identifyNeighbour(i, j, false));
                    board[i][j].setVerticalNeighbours(identifyNeighbour(i, j, true));
                }
            }
        }
    }

    /**
     * Helper method to identify the vertical or horizontal neighbour of the current position
     * @param row - The row to identify
     * @param col - The col to identify
     * @param isRow - true to identify vertical, false to identify horizontal
     * @return The array of neighbour position for hte current position
     */
    private Position[] identifyNeighbour(int row, int col, boolean isRow) {
        ArrayList<Position> neighbours = new ArrayList<>();
        int rowAdder = (isRow) ? 1 : 0;
        int colAdder = (!isRow) ? 1 : 0;

        int currentRow = (isRow) ? row - 1 : row;
        int currentCol = (!isRow) ? col - 1 : col;

        while (currentRow >= 0 && currentCol >= 0) {
            if (board[currentRow][currentCol] != null) {
                neighbours.add(board[currentRow][currentCol]);
                break;
            } if (currentCol == 3 && currentRow == 3) {
                break;
            }

            currentRow -= rowAdder;
            currentCol -= colAdder;
        }

        currentRow = (isRow) ? row + 1 : row;
        currentCol = (!isRow) ? col + 1 : col;

        while (currentRow < BOARD_LENGTH && currentCol < BOARD_WIDTH) {
            if (board[currentRow][currentCol] != null) {
                neighbours.add(board[currentRow][currentCol]);
                break;
            } if (currentCol == 3 && currentRow == 3) {
                break;
            }

            currentRow += rowAdder;
            currentCol += colAdder;
        }

        return neighbours.toArray(new Position[neighbours.size()]);
    }

    /**
     * Create the positions on the board
     */
    private void createPositions() {
        circularCreatePositions(0, 0, 3);
        circularCreatePositions(1, 1, 2);
        circularCreatePositions(2, 2, 1);
    }

    /**
     * Helper method to create position in a circular manner
     * @param row - the row to start
     * @param col - the col to start
     * @param step - The amount of steps to take 
     */
    private void circularCreatePositions(int row, int col, int step) {
        int progress = 1;
        int dist = 2;

        while (progress <= 4) {
            if (progress % 2 == 1) {
                row += step;
            } else {
                col += step;
            }

            board[row][col] = new Position();
            dist -= 1;

            if (dist == 0) {
                progress += 1;
                dist = 2;

                if (progress > 2) {
                    step = Math.abs(step) * -1;
                }
            }
        }
    }

    /**
     * Execute the given move (without row and col)
     * @param move - the move to execute
     * @return The next move that can be executed
     */
    public Move executeMove(Move move) {
        int[] coordinates = move.getMovePosition();
        return executeMove(move, coordinates[0], coordinates[1]);
    }

    /**
     * Execute the given move (with row and col)
     * @param move - the move to execute
     * @param row - the row of the position
     * @param col - the col of the position
     * @return The next move that can be executed
     */
    public Move executeMove(Move move, int row, int col) {
        Position position = null;
        Move output = null;

        if (board[row][col] == null) {
            return move;
        } 

        position = board[row][col];
        output = move.performMove(position);
        switchTurn = move.getSwitchTurn();
        move.resetSwitchTurn();

        return output;
    }

    /**
     * Get if the player should switch turn
     * @return true if player should switch, false otherwise
     */
    public boolean getSwitchTurn() {
        return switchTurn;
    }

    /**
     * Reset player switch turn
     */
    public void resetSwitchTurn() {
        this.switchTurn = false;
    }

    /**
     * Generate a boolean table that represents on the token placed on the board,
     * where true represents red token, false represent blue and null represent blank
     * @return A table of tokens
     */
    public Boolean[][] generatePlayerBoard() {
        Boolean[][] output = new Boolean[BOARD_LENGTH][BOARD_WIDTH];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                if (board[i][j] != null && board[i][j].getIsRedToken() != null) {
                    if (board[i][j].getIsRedToken()) {
                        output[i][j] = true;
                    } else {
                        output[i][j] = false;
                    }
                }
            }
        }

        return output;
    }
}
