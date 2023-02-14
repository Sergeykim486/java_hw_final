import java.util.Random;

public class MazeGenerator {

    private int width;
    private int height;
    private int[][] maze;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
    }

    public int[][] generateMaze() {
        // Initialize maze with all walls
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = -1;
            }
        }

        Random random = new Random();

        // Set entrance and exit
        int entranceX = random.nextInt(width);
        maze[0][entranceX] = -2;
        int exitX = random.nextInt(width);
        maze[height - 1][exitX] = -3;

        // Start generating maze from the entrance point
        generateFromPoint(0, entranceX);

        return maze;
    }

    private void generateFromPoint(int y, int x) {
        // Mark the current cell as visited
        maze[y][x] = 0;

        // Get a random order for the directions
        int[] directions = { 0, 1, 2, 3 };
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(4);
            int temp = directions[randomIndex];
            directions[randomIndex] = directions[i];
            directions[i] = temp;
        }

        // Try to go in each direction
        for (int i = 0; i < 4; i++) {
            int direction = directions[i];
            int nextY = y;
            int nextX = x;

            if (direction == 0 && y > 1) {
                nextY -= 2;
            } else if (direction == 1 && x < width - 2) {
                nextX += 2;
            } else if (direction == 2 && y < height - 2) {
                nextY += 2;
            } else if (direction == 3 && x > 1) {
                nextX -= 2;
            }

            if (maze[nextY][nextX] == -1) {
                // Break the wall and continue generating from the next cell
                maze[(nextY + y) / 2][(nextX + x) / 2] = 0;
                generateFromPoint(nextY, nextX);
            }
        }
    }
}
