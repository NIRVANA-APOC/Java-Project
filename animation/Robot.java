package project.animation;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;

public class Robot extends MovingItem{
    enum State {
        Free, Charging, Picking, Delivering
    }

    private final String robotID;
    private int payloadWeight;
    private int batteryLevel;
    private State state;
    

    public Robot(String robotID, int xVelocity, int yVelocity, int x, int y, int payloadWeight) {
        this (
                robotID,
                new Velocity(xVelocity, yVelocity),
                new Point(x, y),
                payloadWeight
        );
    }

    public Robot(String robotID, Velocity velocity, Point location, int payloadWeight) {
        super(velocity, location, "images/%s-green.png".formatted(robotID));
        this.robotID = robotID;
        this.payloadWeight = payloadWeight;
        this.batteryLevel = 100;
        this.state = State.Free;
    }

    @Override
    public Image getImage() {
        String imageUrl = "images/" + this.robotID;
        if (this.batteryLevel >= 50) {
            imageUrl += "-green";
        } else if (this.batteryLevel >= 10) {
            imageUrl += "-orange";
        }
        else {
            imageUrl += "-red";
        }

        if (this.state == State.Delivering || this.state == State.Picking) {
            imageUrl += "-black";
        }
        imageUrl += ".png";
        return new ImageIcon(imageUrl).getImage();
    }

    @Override
    public void move(){
        if (this.batteryLevel >= 0) {
            super.move();
        }

        this.batteryLevel -= 1;
    }

}
