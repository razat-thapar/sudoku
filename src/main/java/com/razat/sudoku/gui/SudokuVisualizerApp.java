package com.razat.sudoku.gui;

import com.razat.sudoku.configs.AppConfig;
import com.razat.sudoku.factories.ThemeFactory;
import com.razat.sudoku.themes.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.razat.sudoku.configs.AppConfig.*;
import static com.razat.sudoku.configs.UIConfig.*;

public class SudokuVisualizerApp extends JFrame {
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private JButton startButton, stopButton, resetButton, exitButton, toggleThemeButton;
    private JLabel messageLabel;
    private char[][] initialBoard;
    private boolean isDarkMode = false;
    private Theme currentTheme = ThemeFactory.getTheme(false);
    private int currentDelay = DEFAULT_VISUALIZER_DELAY_MS;

    public SudokuVisualizerApp(char[][] board, ActionListener startAction, ActionListener stopAction) {
        this.initialBoard = deepCopyBoard(board);
        setTitle(APP_TITLE);
        setSize(SIZE * CELL_SIZE, SIZE * CELL_SIZE + 150);
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
                cell.setForeground(currentTheme.getCellForeground());
                cell.setBackground(currentTheme.getCellBackground(row, col));

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
        messageLabel.setForeground(currentTheme.getMessageForeground());
        messageLabel.setBackground(currentTheme.getMessageBackground());
        messageLabel.setOpaque(true);
        return messageLabel;
    }

    private JPanel createButtonPanel(ActionListener startAction, ActionListener stopAction) {
        JPanel buttonPanel = new JPanel();

        startButton = createStyledButton("Start", startAction);
        stopButton = createStyledButton("Stop", stopAction);
        resetButton = createStyledButton("Reset", e -> resetBoard());
        exitButton = createStyledButton("Exit", e -> System.exit(0));
        toggleThemeButton = createStyledButton("Dark Mode", e -> toggleTheme());

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(toggleThemeButton);
        buttonPanel.add(createSpeedSliderPanel());

        return buttonPanel;
    }

    private JButton createStyledButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.setBackground(currentTheme.getButtonBackground());
        button.setForeground(currentTheme.getButtonForeground());
        button.addActionListener(action);
        return button;
    }

    private JPanel createSpeedSliderPanel() {
        JPanel sliderPanel = new JPanel();

        JLabel speedLabel = new JLabel("Speed (ms):");
        speedLabel.setFont(BUTTON_FONT);
        speedLabel.setForeground(currentTheme.getSliderForeground());
        speedLabel.setBackground(currentTheme.getSliderBackground());

        JSlider speedSlider = new JSlider(MIN_VISUALIZER_DELAY_MS, MAX_VISUALIZER_DELAY_MS, DEFAULT_VISUALIZER_DELAY_MS);
        speedSlider.setMajorTickSpacing(250);
        speedSlider.setMinorTickSpacing(50);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setPreferredSize(new Dimension(200, 50));
        speedSlider.setToolTipText("Adjust animation speed");
        speedSlider.addChangeListener(e -> currentDelay = speedSlider.getValue());

        sliderPanel.add(speedLabel);
        sliderPanel.add(speedSlider);

        return sliderPanel;
    }


    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        currentTheme = ThemeFactory.getTheme(isDarkMode);
        toggleThemeButton.setText(isDarkMode ? "Light Mode" : "Dark Mode");

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = cells[row][col];
                cell.setBackground(currentTheme.getCellBackground(row, col));
                cell.setForeground(currentTheme.getCellForeground());
            }
        }

        messageLabel.setBackground(currentTheme.getMessageBackground());
        messageLabel.setForeground(currentTheme.getMessageForeground());

        for (JButton btn : new JButton[]{startButton, stopButton, resetButton, exitButton, toggleThemeButton}) {
            btn.setBackground(currentTheme.getButtonBackground());
            btn.setForeground(currentTheme.getButtonForeground());
        }

        repaint();
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
            Thread.sleep(currentDelay);
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