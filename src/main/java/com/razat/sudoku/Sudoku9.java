package com.razat.sudoku;

import static com.razat.sudoku.AppConfig.EMPTY_CELL;
import static com.razat.sudoku.AppConfig.SIZE;
import static com.razat.sudoku.AppConfig.DIGITS;


public class Sudoku9 {

    char[][] board;
    int[] rowBitMap = new int[SIZE];
    int[] colBitMap = new int[SIZE];
    int[][] boxBitMap = new int[3][3];

    public Sudoku9(char[][] board) {
        this.board = board;
        initialize();
    }

    void initialize() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char cell = board[row][col];
                if (cell == EMPTY_CELL) continue;
                int digit = cell - '0';
                addDigit(row, col, digit);
            }
        }
    }

    void addDigit(int row, int col, int digit) {
        board[row][col] = DIGITS[digit];
        rowBitMap[row] |= (1 << digit);
        colBitMap[col] |= (1 << digit);
        boxBitMap[row / 3][col / 3] |= (1 << digit);
    }

    void removeDigit(int row, int col, int digit) {
        board[row][col] = EMPTY_CELL;
        rowBitMap[row] ^= (1 << digit);
        colBitMap[col] ^= (1 << digit);
        boxBitMap[row / 3][col / 3] ^= (1 << digit);
    }

    boolean isValid(int row, int col, int digit) {
        return ((rowBitMap[row] & (1 << digit)) == 0) &&
                ((colBitMap[col] & (1 << digit)) == 0) &&
                ((boxBitMap[row / 3][col / 3] & (1 << digit)) == 0);
    }

    public boolean solve(Sudoku9 sudoku, int row, int col, SudokuVisualizerApp visualizer) {
        if (row == 9) return true;
        if (col == 9) return solve(sudoku, row + 1, 0, visualizer);
        if (sudoku.board[row][col] != EMPTY_CELL)
            return solve(sudoku, row, col + 1, visualizer);

        for (int digit = 1; digit <= 9; digit++) {
            if (sudoku.isValid(row, col, digit)) {
                sudoku.addDigit(row, col, digit);
                visualizer.visualizeStep(sudoku.board);
                if (solve(sudoku, row, col + 1, visualizer)) return true;
                sudoku.removeDigit(row, col, digit);
                visualizer.visualizeStep(sudoku.board);
            }
        }
        return false;
    }

}
