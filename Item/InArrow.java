package project.Item;

import project.Item.Shape.Point;

public class InArrow extends Arrow {
    public InArrow(Point location) {
        super(location);
        this.type = ItemType.InArrow;
    }

    public InArrow(Point location, int cellSize) {
        super(location, cellSize);
        this.type = ItemType.InArrow;
    }
}
