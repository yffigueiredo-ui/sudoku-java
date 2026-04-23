package sudoku;

public class Board {

    private Space[][] grid = new Space[9][9];

    public Board() {
        initBoard();
    }

    private void initBoard() {

        Integer[][] start = {
                {5,null,null, null,7,null, null,null,null},
                {6,null,null, 1,9,5, null,null,null},
                {null,9,8, null,null,null, null,6,null},

                {8,null,null, null,6,null, null,null,3},
                {4,null,null, 8,null,3, null,null,1},
                {7,null,null, null,2,null, null,null,6},

                {null,6,null, null,null,null, 2,8,null},
                {null,null,null, 4,1,9, null,null,5},
                {null,null,null, null,8,null, null,7,9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean fixed = start[i][j] != null;
                grid[i][j] = new Space(start[i][j], fixed);
            }
        }
    }

    public boolean insert(int row, int col, int value) {

        if (grid[row][col].isFixed()) return false;

        if (!isValid(row, col, value)) return false;

        grid[row][col].setValue(value);
        return true;
    }

    public void remove(int row, int col) {
        grid[row][col].clear();
    }

    private boolean isValid(int row, int col, int value) {

        // linha
        for (int i = 0; i < 9; i++) {
            Integer v = grid[row][i].getValue();
            if (v != null && v.equals(value)) return false;
        }

        // coluna
        for (int i = 0; i < 9; i++) {
            Integer v = grid[i][col].getValue();
            if (v != null && v.equals(value)) return false;
        }

        // 3x3
        int r = (row / 3) * 3;
        int c = (col / 3) * 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                Integer v = grid[i][j].getValue();
                if (v != null && v.equals(value)) return false;
            }
        }

        return true;
    }

    public void printBoard() {

        System.out.println(BoardTemplate.LINE);

        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {

                Integer v = grid[i][j].getValue();
                System.out.print((v == null ? "." : v) + " ");
            }

            System.out.println();

            if ((i + 1) % 3 == 0) {
                System.out.println(BoardTemplate.LINE);
            }
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j].getValue() == null) return false;
            }
        }
        return true;
    }

    public boolean hasErrors() {
        // tentativa de validar tudo
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                Integer val = grid[i][j].getValue();

                if (val != null) {
                    grid[i][j].clear();

                    if (!isValid(i, j, val)) {
                        grid[i][j].setValue(val);
                        return true;
                    }

                    grid[i][j].setValue(val);
                }
            }
        }
        return false;
    }

    public boolean isFinished() {
        return isComplete() && !hasErrors();
    }

    public void reset() {
        initBoard();
    }
}