package com.razat.sudoku.Runner;

import com.razat.sudoku.Sudoku9;
import com.razat.sudoku.SudokuVisualizerApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.razat.sudoku.AppConfig.*;

public class Runner {
    private Thread solverThread;

    private void solveSudoku(char[][] board, SudokuVisualizerApp visualizer) {
        if (board.length != 9 || board[0].length != 9) return;
        Sudoku9 sudoku = new Sudoku9(board);
        visualizer.setMessage(MESSAGE_SOLVING);
        if (sudoku.solve(sudoku, 0, 0, visualizer)) {
            visualizer.setMessage(MESSAGE_SOLVED);
        } else {
            visualizer.setMessage(MESSAGE_NO_SOLUTION);
        }

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            char[][] board = {
                    {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                    {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                    {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                    {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                    {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                    {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                    {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                    {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                    {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
            };

            Runner runner = new Runner();

            // Create a temporary holder for the visualizer
            final SudokuVisualizerApp[] visualizerHolder = new SudokuVisualizerApp[1];

            ActionListener startAction = e -> {
                if (runner.solverThread == null || !runner.solverThread.isAlive()) {
                    runner.solverThread = new Thread(() -> {
                        char[][] userBoard = visualizerHolder[0].getUserBoard();
                        runner.solveSudoku(userBoard, visualizerHolder[0]);
                    });
                    //runner.solverThread = new Thread(() -> runner.solveSudoku(board, ));
                    runner.solverThread.start();
                }
            };

            ActionListener stopAction = e -> {
                if (runner.solverThread != null && runner.solverThread.isAlive()) {
                    runner.solverThread.interrupt();
                }
            };

            // Now initialize the visualizer with the action listeners
            visualizerHolder[0] = new SudokuVisualizerApp(board, startAction, stopAction);
        });
    }


}