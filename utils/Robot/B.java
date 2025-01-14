package project.utils.Robot;

import project.utils.Item.ItemType;
import project.utils.Shape.Point;

public class B extends Robot {

    public B(Point location) {
        super(location, "b");
        this.type = ItemType.BotB;
        this.imageUrl = getImageUrl();
    }

}
