package project.Item;

import project.Item.Shape.Point;

public class OutArrow extends Arrow{
    public OutArrow(Point location) {
        super(location);
        this.type = ItemType.OutArrow;
    }

    public OutArrow(Point location, int cellSize) {
        super(location, cellSize);
        this.type = ItemType.OutArrow;
    }
}
