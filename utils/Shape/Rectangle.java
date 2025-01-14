package project.utils.Shape;

import java.awt.*;

public class Rectangle extends Point {
    private final int width;
    private final int height;

    public Rectangle() {
        super();
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(Point p, int width, int height) {
        super(p.x, p.y);
        this.width = width;
        this.height = height;
    }

    public Rectangle(int cellSize) {
        super(cellSize);
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(Point p, int width, int height, int cellSize) {
        super(p.x, p.y, cellSize);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        int x_min = x;
        int x_max = x + height;
        int y_min = y;
        int y_max = y + width;

        for (int y = y_min; y <= y_max; y += CELL_SIZE) {
            g.drawLine(x_min, y, x_max, y);
        }

        for (int x = x_min; x <= x_max; x += CELL_SIZE) {
            g.drawLine(x, y_min, x, y_max);
        }
    }

}
