package br.com.dio.model;

public class SudokuBoard {

    private final Cell[][] board = new Cell[9][9];

    public SudokuBoard(int[][] initial) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int value = initial[r][c];
                board[r][c] = new Cell(value, value != 0);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isComplete() {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] toMatrix() {
        int[][] matrix = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                matrix[r][c] = board[r][c].getValue();
            }
        }
        return matrix;
    }
}
