package com.razat.sudoku;

public class AppConfig {
    static final boolean ENABLE_VISUALIZER = true;
    static final String APP_TITLE = "Sudoku Visualizer";
    static final int VISUALIZER_DELAY_MS = 1; // Delay in milliseconds for visualization steps
    static final int SIZE = 9;
    static final int CELL_SIZE = 60;
    static final char EMPTY_CELL = '.';
    static final char[] DIGITS = {'0','1','2','3','4','5','6','7','8','9'};
    // Messages
    public static final String MESSAGE_SOLVING = "Solving...";
    public static final String MESSAGE_SOLVED = "Sudoku Solved!";
    public static final String MESSAGE_NO_SOLUTION = "No solution exists.";
    public static final String MESSAGE_WELCOME = "Welcome to Sudoku Solver";
}
