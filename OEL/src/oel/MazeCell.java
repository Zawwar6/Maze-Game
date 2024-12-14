package oel;

public class MazeCell {
    private int x, y; // Coordinates
    private boolean isWall; // Whether the cell is a wall
    private boolean visited; // For maze generation and solving

    public MazeCell(int x, int y, boolean isWall) {
        this.x = x;
        this.y = y;
        this.isWall = isWall;
        this.visited = false;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isWall() { return isWall; }
    public void setWall(boolean isWall) { this.isWall = isWall; }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
}
