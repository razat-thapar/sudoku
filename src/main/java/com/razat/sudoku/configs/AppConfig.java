package com.razat.sudoku.configs;

public class AppConfig {
    public static final boolean ENABLE_VISUALIZER = true;
    public static final String APP_TITLE = "Sudoku Visualizer";
    public static final int DEFAULT_VISUALIZER_DELAY_MS = 50; // Default Delay in milliseconds for visualization steps
    public static final int MIN_VISUALIZER_DELAY_MS = 1; // Min Delay in milliseconds for visualization steps
    public static final int MAX_VISUALIZER_DELAY_MS = 1000; // Maximum delay for visualization
    public static final int SIZE = 9;
    public static final int CELL_SIZE = 60;
    public static final char EMPTY_CELL = '.';
    public static final char[] DIGITS = {'0','1','2','3','4','5','6','7','8','9'};
    // Messages
    public static final String MESSAGE_SOLVING = "Solving...";
    public static final String MESSAGE_SOLVED = "Sudoku Solved!";
    public static final String MESSAGE_NO_SOLUTION = "No solution exists.";
    public static final String MESSAGE_WELCOME = "Welcome to Sudoku Solver";
}
