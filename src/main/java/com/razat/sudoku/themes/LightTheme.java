package com.razat.sudoku.themes;

import static com.razat.sudoku.configs.UIConfig.*;

import java.awt.*;

public class LightTheme implements Theme {
    public Color getCellBackground(int row, int col) {
        return ((row / 3 + col / 3) % 2 == 0) ? SUBGRID_BG_COLOR : CELL_BG_COLOR;
    }
    public Color getCellForeground() { return CELL_TEXT_COLOR; }
    public Color getMessageBackground() { return MESSAGE_BG_COLOR; }
    public Color getMessageForeground() { return MESSAGE_TEXT_COLOR; }
    public Color getButtonBackground() { return BUTTON_RESET_BG; }
    public Color getButtonForeground() { return BUTTON_TEXT_COLOR; }
}
