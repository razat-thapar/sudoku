package com.razat.sudoku.factories;

import com.razat.sudoku.themes.DarkTheme;
import com.razat.sudoku.themes.LightTheme;
import com.razat.sudoku.themes.Theme;

public class ThemeFactory {
    public static Theme getTheme(boolean isDarkMode) {
        return isDarkMode ? new DarkTheme() : new LightTheme();
    }
}