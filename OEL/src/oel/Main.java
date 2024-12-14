package oel;

public class Main {
    public static void main(String[] args) {
        // Ensure proper size for the maze (odd dimensions for proper generation)
        int rows = 11, cols = 11;
        if (rows % 2 == 0) rows++; // Adjust to odd if necessary
        if (cols % 2 == 0) cols++;

        GameController game = new GameController(rows, cols); // Create the game
        game.startGame(); // Start the game
    }
}

