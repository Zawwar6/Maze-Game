package oel;

import java.util.*;

public class MazeGenerator {
    private final int rows, cols;
    private final MazeCell[][] grid;

    public MazeGenerator(int rows, int cols) {
        // Ensure rows and cols are odd for proper maze generation
        this.rows = (rows % 2 == 0) ? rows + 1 : rows;
        this.cols = (cols % 2 == 0) ? cols + 1 : cols;
        this.grid = new MazeCell[this.rows][this.cols];

        // Initialize the maze grid with walls
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                grid[i][j] = new MazeCell(i, j, true);
            }
        }
    }

    public MazeCell[][] generateMaze() {
        Stack<MazeCell> stack = new Stack<>();
        MazeCell start = grid[1][1]; // Start from (1, 1) for odd-sized grids
        start.setWall(false); // Starting point is not a wall
        stack.push(start);

        Random rand = new Random();
        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} }; // Right, Down, Left, Up

        while (!stack.isEmpty()) {
            MazeCell current = stack.pop();
            current.setVisited(true);

            // Shuffle directions for randomness
            List<int[]> dirList = Arrays.asList(directions);
            Collections.shuffle(dirList, rand);

            for (int[] dir : dirList) {
                int nx = current.getX() + dir[0] * 2;
                int ny = current.getY() + dir[1] * 2;

                if (isValidCell(nx, ny)) {
                    grid[nx][ny].setWall(false); // Mark as path
                    grid[current.getX() + dir[0]][current.getY() + dir[1]].setWall(false); // Remove wall between
                    stack.push(grid[nx][ny]);
                }
            }
        }

        return grid;
    }

    private boolean isValidCell(int x, int y) {
        return x > 0 && x < rows - 1 && y > 0 && y < cols - 1 && grid[x][y].isWall() && !grid[x][y].isVisited();
    }
}