package project.utils.Robot;

import project.utils.Item.ItemType;
import project.utils.Shape.Point;

public class C extends Robot {

    public C(Point location) {
        super(location, "c");
        this.type = ItemType.BotC;
        this.imageUrl = getImageUrl();
    }

}
