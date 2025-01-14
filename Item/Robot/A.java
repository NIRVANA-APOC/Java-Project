package project.Item.Robot;

import project.Item.ItemType;
import project.Item.Shape.Point;

public class A extends Robot {

    public A(Point location) {
        super(location, "a");
        this.type = ItemType.BotA;
        this.imageUrl = getImageUrl();
    }

}
