package project.Item.Robot;

import project.Item.ItemType;
import project.Item.Shape.Point;

public class C extends Robot {

    public C(Point location) {
        super(location, "c");
        this.type = ItemType.BotC;
        this.imageUrl = getImageUrl();
    }

}
