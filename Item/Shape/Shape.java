package project.Item.Shape;

import java.awt.*;

abstract class Shape {
    protected int CELL_SIZE;

    public Shape() {
        this.CELL_SIZE = 1;
    }

    public Shape(int cellSize) {
        this.CELL_SIZE = cellSize;
    }

    public void setCELL_SIZE(int cellSize) {
        this.CELL_SIZE = cellSize;
    }

    public abstract void draw(Graphics g);
}
