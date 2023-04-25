package ninemanmorris.gamelogic;

import java.util.ArrayList;

import ninemanmorris.move.Move;

public class MorrisBoard {
    
    private static final int BOARD_LENGTH = 7;
    private static final int BOARD_WIDTH = 7;

    private Position[][] board;

    public MorrisBoard() {
        board = new Position[BOARD_LENGTH][BOARD_WIDTH];

        createPositions();
        createNeighbours();
    }

    private void createNeighbours() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null) {
                    board[i][j].setHorizontalNeighbours(identifyNeighbour(i, j, false));
                    board[i][j].setVerticalNeighbours(identifyNeighbour(i, j, true));
                }
            }
        }
    }

    private Position[] identifyNeighbour(int row, int col, boolean isRow) {
        ArrayList<Position> neighbours = new ArrayList<>();
        int rowAdder = (isRow) ? 1 : 0;
        int colAdder = (!isRow) ? 1 : 0;

        row = (isRow) ? row - 1 : row;
        col = (!isRow) ? col - 1 : col;

        while (row >= 0 && col >= 0) {
            if (board[row][col] != null) {
                neighbours.add(board[row][col]);
                break;
            }

            row -= rowAdder;
            col -= colAdder;
        }

        row = (isRow) ? row + 1 : row;
        col = (!isRow) ? col + 1 : col;

        while (row < BOARD_LENGTH && col < BOARD_WIDTH) {
            if (board[row][col] != null) {
                neighbours.add(board[row][col]);
                break;
            }

            row += rowAdder;
            col += colAdder;
        }

        return neighbours.toArray(new Position[neighbours.size()]);
    }

    private void createPositions() {
        circularCreatePositions(0, 0, 3);
        circularCreatePositions(1, 1, 2);
        circularCreatePositions(2, 2, 1);
    }

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

    public Move executeMove(Move move) {
        return null;
    }

    public Move executeMove(Move move, int x, int y) {
        return null;
    }

    public Boolean[][] generatePlayerBoard(boolean isRed) {
        return null;
    }
}
