package com.razat.sudoku.themes;

import java.awt.*;

public interface Theme {
    Color getCellBackground(int row, int col);
    Color getCellForeground();
    Color getMessageBackground();
    Color getMessageForeground();
    Color getButtonBackground();
    Color getButtonForeground();
}
