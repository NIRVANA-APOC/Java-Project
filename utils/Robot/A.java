package project.utils.Robot;

import project.utils.Item.ItemType;
import project.utils.Shape.Point;

public class A extends Robot {

    public A(Point location) {
        super(location, "a");
        this.type = ItemType.BotA;
        this.imageUrl = getImageUrl();
    }

}
