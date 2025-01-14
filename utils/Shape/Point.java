package project.utils.Shape;

import java.awt.*;

public class Point extends Shape {
    public int x;
    public int y;

    public Point() {
        super();
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Point(int cellSize) {
        super(cellSize);
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y, int cellSize) {
        super(cellSize);
        this.x = x;
        this.y = y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void add(Point other) {
        this.x += other.x;
        this.y += other.y;
    }

    public boolean equal(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    public Point getRelativePosition() {
        return new Point(x * CELL_SIZE, y * CELL_SIZE);
    }

    @Override
    public void draw(Graphics g) {
        Point p = getRelativePosition();
        g.drawLine(p.x, p.y, p.x + CELL_SIZE, p.y);
        g.drawLine(p.x, p.y, p.x, p.y + CELL_SIZE);
        g.drawLine(p.x, p.y + CELL_SIZE, p.x + CELL_SIZE, p.y + CELL_SIZE);
        g.drawLine(p.x + CELL_SIZE, p.y, p.x + CELL_SIZE, p.y + CELL_SIZE);
    }

    public void transDraw(Graphics g) {
        new Point(y, x, CELL_SIZE).draw(g);
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(x, y);
    }
}
