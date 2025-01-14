package project.utils.Item;

import project.utils.Shape.Point;

public class Plug extends Item {

    public Plug(Point location) {
        super(location, ItemType.Plug, "images/plug-24.png");
    }

    public Plug(Point location, int cellSize) {
        super(location, ItemType.Plug, "images/plug-24.png", cellSize);
    }
}
