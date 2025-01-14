package project.utils;

import project.utils.Item.*;
import project.utils.Robot.A;
import project.utils.Robot.B;
import project.utils.Robot.C;
import project.utils.Robot.Robot;
import project.utils.Shape.Point;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Map {
    private static final String mapFilePath = "map.txt";
    public Item[][] map;
    public int ROWS;
    public int COLS;
    public int CELL_SIZE;
    public ArrayList<InArrow> inArrows = new ArrayList<>();
    public ArrayList<OutArrow> outArrows = new ArrayList<>();
    public ArrayList<Plug> chargePlug = new ArrayList<>();
    public ArrayList<Robot> robots = new ArrayList<>();

    public Map() {
        readMap();
    }


    public boolean isNone(Point p) {
        return isNone(p.x, p.y);
    }

    public boolean isNone(int x, int y) {
        return map[x][y].type == ItemType.None;
    }

    private void setItem(int x, int y, ItemType type) {
        this.map[x][y].type = type;
    }

    private void setItem(Point p, ItemType type) {
        setItem(p.x, p.y, type);
    }

    private void setItem(Item item) {
        setItem(item.x, item.y, item.type);
    }

    public ArrayList<Point> findPath(Point from, Point to) {
        // 定义方向数组，表示上下左右四个方向
        int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 检查起点和终点是否合法
//        if (!isValid(from)) {
//            System.out.println("Not Valid!");
//            return new ArrayList<>();
//        }

        if (!isValid(to)) {
            for (int[] dir: DIRECTIONS) {
                int newX = to.x + dir[0];
                int newY = to.y + dir[1];
                Point p = new Point(newX, newY);
                if (isValid(p)) {
                    to = p;
                }

            }
        }

        // 初始化队列、visited 数组和 parent 数组
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[ROWS][COLS];
        Point[][] parent = new Point[ROWS][COLS]; // 记录每个点的父节点

        // 将起点加入队列，并标记为已访问
        queue.offer(from);
        visited[from.x][from.y] = true;

        // BFS 搜索
        while (!queue.isEmpty()) {

            Point current = queue.poll();
//            System.out.println(current);
            int x = current.x;
            int y = current.y;

            // 如果到达终点，回溯路径
            if (current.equal(to)) {
                return buildPath(parent, to);
            }

            // 遍历四个方向
            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // 检查新点是否合法且未被访问过
                if (isValid(newX, newY) && !visited[newX][newY]) {
                    queue.offer(new Point(newX, newY));
                    visited[newX][newY] = true;
                    parent[newX][newY] = current; // 记录父节点
                }
            }
        }

        // 如果没有找到路径，返回空列表
        return new ArrayList<>();
    }

    // 检查点 (x, y) 是否合法
    private boolean isAccess(int x, int y) {
        Item item = this.map[x][y];
        return switch (item.type) {
            case None, Arrow, Plug, InArrow, OutArrow -> true;
            default -> false;
        };
    }

    private boolean isValid(Point p) {
        return isValid(p.x, p.y);
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLS && isAccess(x, y);
    }

    // 根据 parent 数组回溯路径
    private static ArrayList<Point> buildPath(Point[][] parent, Point end) {
        ArrayList<Point> path = new ArrayList<>();
        Point current = end;

        // 从终点回溯到起点
        while (current != null) {
            path.add(current);
            current = parent[current.x][current.y] == null ? null : parent[current.x][current.y];
        }

        // 反转路径，使其从起点到终点
        Collections.reverse(path);
        return path;
    }

    public void readMap() {
        try (Scanner scanner = new Scanner(new File(mapFilePath))) {
            if (scanner.hasNext()) {
                this.ROWS = scanner.nextInt();
                this.COLS = scanner.nextInt();
                this.CELL_SIZE = scanner.nextInt();
            }
            this.map = new Item[ROWS][COLS];
            int cnt = 0;
            while (scanner.hasNext()) {
                int typeID = scanner.nextInt();
                ItemType type = ItemType.None;
                for (var t: ItemType.values()) {
                    if (t.ordinal() == typeID) {
                        type = t;
                        break;
                    }
                }
                Point p = new Point(cnt / COLS, cnt % COLS);
                Item item = switch (type) {
                    case Arrow -> new Arrow(p);
                    case InArrow -> new InArrow(p);
                    case OutArrow -> new OutArrow(p);
                    case Plug -> new Plug(p);
                    case Robot -> new Robot(p, "a");
                    case Box -> new Item(p, ItemType.Box);
                    case None -> new Item(p, ItemType.None);
                    case BotA -> new A(p);
                    case BotB -> new B(p);
                    case BotC -> new C(p);
                };

                if (InArrow.class.isAssignableFrom(item.getClass())) {
                    this.inArrows.add((InArrow) item);
                }

                if (OutArrow.class.isAssignableFrom(item.getClass())) {
                    this.outArrows.add((OutArrow) item);
                }

                if (Plug.class.isAssignableFrom(item.getClass())) {
                    this.chargePlug.add((Plug) item);
                }

                if (Robot.class.isAssignableFrom(item.getClass())) {
                    this.robots.add((Robot) item);
                }

                item.setCELL_SIZE(CELL_SIZE);
                this.map[p.x][p.y] = item;
                cnt++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportMap() {
        try {
            FileWriter writer = new FileWriter(mapFilePath);
            writer.write("%d %d %d \n".formatted(ROWS, COLS, CELL_SIZE));
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    writer.write(map[i][j].type.ordinal() + " ");
                }
                writer.write("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                this.map[i][j].draw(g);
            }
        }
    }

    public void move() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                this.map[i][j].move();
            }
        }
    }

    public static void main(String[] args) {

        Map map = new Map();
//        map.exportMap();
        var path = map.findPath(new Point(0, 0), new Point(0, 10));
        System.out.println(path);
    }
}
