package oel;

import java.util.Scanner;

public class GameController {
    private final MazeGenerator mazeGenerator;
    private MazeCell[][] maze;
    private int playerX, playerY;

    public GameController(int rows, int cols) {
        this.mazeGenerator = new MazeGenerator(rows, cols);
    }

    public void startGame() {
        maze = mazeGenerator.generateMaze();
        playerX = 1; // Start from (1, 1) for consistency with maze generation
        playerY = 1;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            renderMaze();
            System.out.println("Move (W/A/S/D): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.isEmpty()) {
                System.out.println("Please enter a valid move.");
                continue;
            }
            char move = input.charAt(0);

            if (makeMove(move)) {
                if (playerX == maze.length - 2 && playerY == maze[0].length - 2) {
                    System.out.println("Congratulations! You've reached the exit!");
                    break;
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }

    private boolean makeMove(char direction) {
        int newX = playerX, newY = playerY;
        switch (direction) {
            case 'W': newX--; break; // Up
            case 'A': newY--; break; // Left
            case 'S': newX++; break; // Down
            case 'D': newY++; break; // Right
            default: return false;
        }

        if (isValidMove(newX, newY)) {
            playerX = newX;
            playerY = newY;
            return true;
        }
        return false;
    }

    private boolean isValidMove(int x, int y) {
        return x > 0 && x < maze.length - 1 && y > 0 && y < maze[0].length - 1 && !maze[x][y].isWall();
    }

    private void renderMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print("P "); // Player
                } else if (maze[i][j].isWall()) {
                    System.out.print("* "); // Wall
                } else {
                    System.out.print("  "); // Path
                }
            }
            System.out.println();
        }
    }
}
