import javax.naming.SizeLimitExceededException;

public class GetPsth {

    public static int[][] maze;
    public static int[][] temp;
    public static int sx;
    public static int sy;
    public static int ex;
    public static int ey;
    public static int wave = 0;

    public static boolean complete = false;

    public GetPsth(int[][] maze1) {
        maze = maze1;
        temp = maze;
    }

    public static int[][] MarkPath() {
        FindStartEndPoints();
        WaveGen();
        FindPath();
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (temp[i][j] == -4) {
                    maze[i][j] = -4;
                }
            }
        }
        temp[sx][sy] = -2;
        temp[ex][ey] = -3;
        return temp;
    }

    public static void FindStartEndPoints() {
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (temp[i][j] == -2) {
                    sx = i;
                    sy = j;
                } else if (temp[i][j] == -3) {
                    ex = i;
                    ey = j;
                }
            }
        }
    }

    public static void WaveGen() {
        temp[sx][sy] = 0;
        int sizex = temp.length;
        int sizey = temp[0].length;
        while (!complete) {
            complete = true;
            for (int i = 0; i < sizex; i++) {
                for (int j = 0; j < sizey; j++) {
                    if (wave == 0) {
                        if (i == sx && j == sy) {
                            System.out.println("Найдена стартовая точка");
                            if (i > 0 && temp[i - 1][j] == 0) {
                                temp[i - 1][j] = wave + 1;
                                complete = false;
                            }
                            if (i < (sizex - 1) && temp[i + 1][j] == 0) {
                                temp[i + 1][j] = wave + 1;
                                complete = false;
                            }
                            if (j > 0 && temp[i][j - 1] == 0) {
                                temp[i][j - 1] = wave + 1;
                                complete = false;
                            }
                            if (j < (sizey - 1) && temp[i][j + 1] == 0) {
                                temp[i][j + 1] = wave + 1;
                                complete = false;
                            }

                        }
                    } else if (temp[i][j] == wave) {
                        if (i > 0 && temp[i - 1][j] == 0) {
                            temp[i - 1][j] = wave + 1;
                            complete = false;
                        }
                        if (i < (sizex - 1) && temp[i + 1][j] == 0) {
                            temp[i + 1][j] = wave + 1;
                            complete = false;
                        }
                        if (j > 0 && temp[i][j - 1] == 0) {
                            temp[i][j - 1] = wave + 1;
                            complete = false;
                        }
                        if (j < (sizey - 1) && temp[i][j + 1] == 0) {
                            temp[i][j + 1] = wave + 1;
                            complete = false;
                        }
                    }
                }
            }
            wave++;
        }
    }

    public static void FindPath() {
        int step = wave;
        int x = ex;
        int y = ey;
        while (step > 0) {
            temp[x][y] = -4;
            step--;
            if (x > 0 && temp[x - 1][y] == step) {
                x--;
            } else if (x < (temp.length - 1) && temp[x + 1][y] == step) {
                x++;
            } else if (y > 0 && temp[x][y - 1] == step) {
                y--;
            } else if (y < temp[0].length - 1 && temp[x][y + 1] == step) {
                y++;
            }
        }
    }
}
