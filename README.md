
```markdown
# 🧩 Sudoku Solver

A Java-based Sudoku solver that uses backtracking to efficiently solve standard 9x9 Sudoku puzzles. This project demonstrates algorithmic problem-solving, object-oriented design, and GUI development using Java Swing.

## 🚀 Features

- Solves any valid 9x9 Sudoku puzzle
- Uses recursive backtracking algorithm
- Uses BitMap to validate digit placement
- Clean and modular code structure
- Interactive GUI built with Java Swing
- Easy to extend for web or mobile integration

## 🖼️ GUI Overview

The application now includes a graphical user interface built with **Java Swing**:
- Clickable 9x9 grid for input
- "Solve" button to trigger the solver
- Input validation and visual feedback
- Responsive layout and intuitive design

### ALGORITHM DEEP DIVE 
https://leetcode.com/problems/sudoku-solver/solutions/7141244/backtracking-bitmap-o9m-by-razat_aggarwa-8d1u/
# Intuition
<!-- Describe your first thoughts on how to solve this problem. -->
We need to try every digit[1,9] and check if sudoko is valid. 

# Approach
<!-- Describe your approach to solving the problem. -->
Backtracking , we will try every digit and move to next cell in order , if we are unable to fill the next cell then backtracking to previous cell and try another digit. 

# Complexity
- Time complexity:
<!-- Add your time complexity here, e.g. $$O(n)$$ -->
O(9^m) , m = no of empty cells. 

- Space complexity:
<!-- Add your space complexity here, e.g. $$O(n)$$ -->
O(m + 9*3) , m = no of empty cells. 

# Code
```java []
class Solution {
    class Sudoku9 {
        final char EMPTY_CELL = '.';
        final int SIZE = 9;
        final char[] DIGITS = {'0','1','2','3','4','5','6','7','8','9'}; // index matches digit

        char[][] board;
        int[] rowBitMap = new int[SIZE];
        int[] colBitMap = new int[SIZE];
        int[][] boxBitMap = new int[3][3];

        Sudoku9(char[][] board) {
            this.board = board;
            initialize();
        }

        void initialize() {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    char cell = board[row][col];
                    if (cell == EMPTY_CELL) continue;
                    int digit = cell - '0';
                    addDigit(row, col, digit);
                }
            }
        }

        void addDigit(int row, int col, int digit) {
            board[row][col] = DIGITS[digit];
            rowBitMap[row] |= (1 << digit);
            colBitMap[col] |= (1 << digit);
            boxBitMap[row / 3][col / 3] |= (1 << digit);
        }

        void removeDigit(int row, int col, int digit) {
            board[row][col] = EMPTY_CELL;
            rowBitMap[row] ^= (1 << digit);
            colBitMap[col] ^= (1 << digit);
            boxBitMap[row / 3][col / 3] ^= (1 << digit);
        }

        boolean isValid(int row, int col, int digit) {
            return ((rowBitMap[row] & (1 << digit)) == 0) &&
                   ((colBitMap[col] & (1 << digit)) == 0) &&
                   ((boxBitMap[row / 3][col / 3] & (1 << digit)) == 0);
        }
    }

    public void solveSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) return;
        Sudoku9 sudoku = new Sudoku9(board);
        solve(sudoku, 0, 0);
    }

    boolean solve(Sudoku9 sudoku, int row, int col) {
        if (row == 9) return true;
        if (col == 9) return solve(sudoku, row + 1, 0);
        if (sudoku.board[row][col] != sudoku.EMPTY_CELL)
            return solve(sudoku, row, col + 1);

        for (int digit = 1; digit <= 9; digit++) {
            if (sudoku.isValid(row, col, digit)) {
                sudoku.addDigit(row, col, digit);
                if (solve(sudoku, row, col + 1)) return true;
                sudoku.removeDigit(row, col, digit);
            }
        }
        return false;
    }
}
```

### 🧪 DEMO
[![Watch the demo](https://raw.githubusercontent.com/razat-thapar/sudoku/master/thumbnail.jpg)](https://raw.githubusercontent.com/razat-thapar/sudoku/master/SudokuDemo.mp4)

> *Screenshot*
<img width="1061" height="691" alt="image" src="https://github.com/user-attachments/assets/c8607545-4ccb-4abb-a40a-254bc2169183" />


## 📁 Project Structure

```
sudoku/
├── src/
│   ├── gui/                # Swing GUI components
│   ├── solver/             # Core solving logic
│   └── Runner.java         # Main entry point
├── pom.xml
```

- `gui/`: Contains Swing-based UI classes
- `solver/`: Contains the Sudoku solving logic
- `Runner.java`: Launches the GUI and solver
- `pom.xml`: Maven configuration file

## 🛠️ Technologies Used

- Java 100%
- Maven (for build and dependency management)
- Swing (for GUI)

## 🧠 Algorithm Overview

The solver uses a classic **backtracking** approach:
1. Find an empty cell
2. Try placing digits 1–9
3. Check if the placement is valid
4. Recursively attempt to solve the rest of the board
5. Backtrack if needed

## 📦 How to Run

### Prerequisites
- Java 8+
- Maven installed

### Steps

```bash
git clone https://github.com/razat-thapar/sudoku.git
cd sudoku
mvn compile
mvn exec:java -Dexec.mainClass="com.java.razat.sudoku.Runner"
```

> The GUI will launch automatically. You can enter digits manually and click "Solve" to see the solution.

## 📌 Future Improvements

- Add puzzle generation with difficulty levels
- Enhance GUI with drag-and-drop or animations
- Integrate with a web frontend or mobile app

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
```

---

If you’d like, I can help you generate a sample screenshot or walk through how to structure the GUI code. Just say the word!
