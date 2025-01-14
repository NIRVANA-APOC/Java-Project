package project.utils.Item;

import project.utils.Shape.Drawable;
import project.utils.Shape.Point;

import java.awt.*;

public class Item extends Drawable {

    public ItemType type;
    public Drawable item;

    public Item() {
        super();
        this.type = ItemType.None;
    }

    public Item(ItemType type) {
        super();
        this.type = type;
    }

    public Item(ItemType type, String imageUrl) {
        super(imageUrl);
        this.type = type;
        this.item = new Drawable(imageUrl);

    }

    public Item(Point p, ItemType type) {
        super(p.x, p.y);
        this.type = type;
    }

    public Item(Point p, ItemType type, String imageUrl) {
        super(p.x, p.y, imageUrl);
        this.type = type;
        this.item = new Drawable(imageUrl);
    }

    public Item(Point p, ItemType type, int cellSize) {
        super(p.x, p.y, cellSize);
        this.type = type;
    }

    public Item(Point p, ItemType type, String imageUrl, int cellSize) {
        super(p.x, p.y, imageUrl, cellSize);
        this.type = type;
        this.item = new Drawable(imageUrl);
    }

    @Override
    public void draw(Graphics g) {
        if (this.type == ItemType.None) {
            return;
        }
        else {
            super.draw(g);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + type;
    }

    public void move() {}
}

