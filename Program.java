import java.util.Scanner;

public class Program {
    public static int[][] desk;
    public static int xw, yw;
    public static int m = 0;
    public static int n = 0;
    public static int x = 0;
    public static int y = 0;

    public static void main(String[] args) {
        Printmenu();
        int choice = InputInt("\nВведите номер выбранной операции...   ");
        switch (choice) {
            case (1):
                DeskSize();
                main(args);
            case (2):
                ShowMaze();
                main(args);
            case (3):
                StartPoint();
                main(args);
            case (4):
                Start();
                main(args);
            case (5):
                System.exit(0);
        }
    }

    private static void StartPoint() {
        x = InputInt("Введите координаты по вертикали");
        y = InputInt("Введите координаты по горизонтали");
        desk[x][y] = -2;
        ShowMaze();
    }

    public static void Start() {
        if (m == 0 | n == 0) {
            System.out.println("Сначала укажите размер сетки.");
            DeskSize();
            Start();
        } else {
            GetPsth GetPsth = new GetPsth(desk);
            desk = GetPsth.MarkPath();
            ShowMaze();
        }
    }

    public static void ShowMaze() {
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                if (desk[i][j] == -1) {
                    System.out.print("▓▓"); // стена
                } else if (desk[i][j] == -4) {
                    System.out.print("░░"); // проход
                } else if (desk[i][j] == -2) {
                    System.out.print("SP"); // вход
                } else if (desk[i][j] == -3) {
                    System.out.print("EP"); // выход
                } else {
                    System.out.print("  "); // Маршрут
                }
            }
            System.out.println();
        }
        PressEnterToContinue();
    }

    public static void PressEnterToContinue() {
        System.out.println("Чтобы продолжнить ENTER");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public static void Printmenu() {
        System.out.print("\033[H\033[J");
        System.out.println("Выберите действие:\n");
        String[] menu = {
                "1. Указать размер лабиринта",
                "2. Показать лабиринт",
                "3. Задать координаты стартовой точки",
                "4. Найти кратчайший маршрут",
                "5. Выход"
        };
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
    }

    public static void DeskSize() {
        m = InputInt("Укажите размерность m  -> ");
        n = InputInt("Укажите размерность n  -> ");
        desk = new int[m][n];
        MazeGenerator mazeGenerator = new MazeGenerator(m, n);
        desk = mazeGenerator.generateMaze();
        System.out.println("Лабиринт сгенерирован.");
        ShowMaze();
    }

    public static int InputInt(String Text) {
        Scanner in = new Scanner(System.in);
        System.out.print(Text);
        int res = 0;
        try {
            res = in.nextInt();
        } catch (Exception e) {
            System.out.println("ошибка ввода!\n\nВы ввели не число!\nПОВТОРИТЕ ПОПЫТКУ...");
            main(null);
        }
        return res;
    }

}