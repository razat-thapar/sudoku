package com.razat.sudoku.themes;

import static com.razat.sudoku.configs.UIConfig.*;

import java.awt.*;

public class DarkTheme implements Theme {
    public Color getCellBackground(int row, int col) {
        return ((row / 3 + col / 3) % 2 == 0) ? DARK_SUBGRID_BG : DARK_CELL_BG;
    }
    public Color getCellForeground() { return DARK_CELL_TEXT; }
    public Color getMessageBackground() { return DARK_MESSAGE_BG; }
    public Color getMessageForeground() { return DARK_MESSAGE_TEXT; }
    public Color getButtonBackground() { return DARK_BUTTON_BG; }
    public Color getButtonForeground() { return DARK_BUTTON_TEXT; }
    public Color getSliderBackground() {
        return DARK_SLIDER_BG;
    }
    public Color getSliderForeground() {
        return DARK_SLIDER_TEXT;
    }
}
