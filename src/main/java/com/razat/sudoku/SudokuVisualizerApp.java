package com.razat.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.razat.sudoku.AppConfig.*;
import static com.razat.sudoku.UIConfig.*;

public class SudokuVisualizerApp extends JFrame {
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton startButton, stopButton, resetButton, exitButton;
    private char[][] initialBoard;
    private JLabel messageLabel;

    public SudokuVisualizerApp(char[][] board, ActionListener startAction, ActionListener stopAction) {
        this.initialBoard = deepCopyBoard(board);
        setTitle(APP_TITLE);
        setSize(SIZE * CELL_SIZE, SIZE * CELL_SIZE + 100);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(createGridPanel(board), BorderLayout.CENTER);
        add(createMessageLabel(), BorderLayout.NORTH);
        add(createButtonPanel(startAction, stopAction), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createGridPanel(char[][] board) {
        JPanel gridPanel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(CELL_FONT);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                char value = board[row][col];
                cell.setText(value == EMPTY_CELL ? "" : String.valueOf(value));
                cell.setEditable(true);
                cell.setForeground(CELL_TEXT_COLOR);
                cell.setBackground(CELL_BG_COLOR);

                int gridRow = row / 3;
                int gridCol = col / 3;
                if ((gridRow + gridCol) % 2 == 0) {
                    cell.setBackground(SUBGRID_BG_COLOR);
                }

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        return gridPanel;
    }

    private JLabel createMessageLabel() {
        messageLabel = new JLabel(MESSAGE_WELCOME, SwingConstants.CENTER);
        messageLabel.setFont(MESSAGE_FONT);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        messageLabel.setForeground(MESSAGE_TEXT_COLOR);
        messageLabel.setBackground(MESSAGE_BG_COLOR);
        messageLabel.setOpaque(true);
        return messageLabel;
    }

    private JPanel createButtonPanel(ActionListener startAction, ActionListener stopAction) {
        JPanel buttonPanel = new JPanel();

        startButton = createStyledButton("Start", BUTTON_START_BG, startAction);
        stopButton = createStyledButton("Stop", BUTTON_STOP_BG, stopAction);
        resetButton = createStyledButton("Reset", BUTTON_RESET_BG, e -> resetBoard());
        exitButton = createStyledButton("Exit", BUTTON_EXIT_BG, e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        return buttonPanel;
    }

    private JButton createStyledButton(String text, Color bgColor, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(bgColor);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.addActionListener(action);
        return button;
    }

    private void resetBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char val = initialBoard[row][col];
                cells[row][col].setText(val == EMPTY_CELL ? "" : String.valueOf(val));
            }
        }
        setMessage(MESSAGE_WELCOME);
    }

    public void updateBoard(char[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char val = board[row][col];
                cells[row][col].setText(val == EMPTY_CELL ? "" : String.valueOf(val));
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
                userBoard[row][col] = (text.isEmpty()) ? EMPTY_CELL : text.charAt(0);
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