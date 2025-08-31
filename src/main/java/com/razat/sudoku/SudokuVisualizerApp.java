package com.razat.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.razat.sudoku.AppConfig.*;

public class SudokuVisualizerApp extends JFrame {
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private JButton exitButton;
    private char[][] initialBoard;
    private JLabel messageLabel;

    public SudokuVisualizerApp(char[][] board, ActionListener startAction, ActionListener stopAction) {
        this.initialBoard = deepCopyBoard(board);
        setTitle(APP_TITLE);
        setSize(SIZE * CELL_SIZE, SIZE * CELL_SIZE + 100); // Extra space for buttons
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 24));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setText(board[row][col] == EMPTY_CELL ? "" : String.valueOf(board[row][col]));
                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        add(gridPanel, BorderLayout.CENTER);
        messageLabel = new JLabel(MESSAGE_WELCOME, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(messageLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        startButton.addActionListener(startAction);
        stopButton.addActionListener(stopAction);
        exitButton.addActionListener(e -> System.exit(0));
        resetButton.addActionListener(e -> {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    char val = initialBoard[row][col];
                    cells[row][col].setText(val == '.' ? "" : String.valueOf(val));
                }
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateBoard(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char val = board[row][col];
                cells[row][col].setText(val == '.' ? "" : String.valueOf(val));
            }
        }
    }

    public void visualizeStep(char[][] board) {
        updateBoard(board);
        try {
            Thread.sleep(AppConfig.VISUALIZER_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public char[][] getUserBoard() {
        char[][] userBoard = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String text = cells[row][col].getText().trim();
                userBoard[row][col] = (text.isEmpty()) ? '.' : text.charAt(0);
            }
        }
        return userBoard;
    }

    private char[][] deepCopyBoard(char[][] source) {
        char[][] copy = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(source[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    public void setMessage(String message) {
        SwingUtilities.invokeLater(() -> messageLabel.setText(message));
    }
}