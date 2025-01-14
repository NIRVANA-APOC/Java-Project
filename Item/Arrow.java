package project.Item;

import project.Item.Shape.Point;

public class Arrow extends Item {

    public Arrow(Point location) {
        super(location, ItemType.Arrow, "images/arrow-24.png");
    }

    public Arrow(Point location, int cellSize) {
        super(location, ItemType.Arrow, "images/arrow-24.png", cellSize);
    }
}
