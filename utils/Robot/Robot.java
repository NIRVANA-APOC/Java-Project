package project.utils.Robot;

import project.utils.Item.ItemType;
import project.utils.Item.MovingItem;
import project.utils.Shape.Point;

import javax.swing.*;
import java.awt.*;

enum State {
    Free, Charging, Fetching, Delivering
}

public class Robot extends MovingItem {

    public String id;
    public int payloadWeight;
    public int batteryLevel;
    private State state;

    public Robot(Point location, String id) {
        super(location, ItemType.Robot);
        this.id = id;
        this.batteryLevel = 100;
    }

    public Robot(Point location, String id, int cellSize) {
        super(location, ItemType.Robot, cellSize);
        this.id = id;
        this.batteryLevel = 100;
    }

    public boolean fetch() {
        this.state = State.Fetching;

        return true;
    }

    public boolean deliver() {
        state = State.Delivering;

        return true;
    }

    @Override
    public String getImageUrl() {
        String imageUrl = "images/" + this.id;
        if (this.batteryLevel >= 50) {
            imageUrl += "-green";
        } else if (this.batteryLevel >= 10) {
            imageUrl += "-orange";
        }
        else {
            imageUrl += "-red";
        }

        if (this.state == State.Delivering) {
            imageUrl += "-black";
        }
        imageUrl += ".png";
        return imageUrl;
    }

    @Override
    public Image getImage() {
        return new ImageIcon(getImageUrl()).getImage();
    }
}

