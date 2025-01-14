package project.Item.Robot;

import project.Item.ItemType;
import project.Item.Shape.Point;

public class B extends Robot {

    public B(Point location) {
        super(location, "b");
        this.type = ItemType.BotB;
        this.imageUrl = getImageUrl();
    }

}
