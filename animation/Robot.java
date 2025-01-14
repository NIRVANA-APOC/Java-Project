package project.animation;


import javax.swing.*;
import java.awt.*;

public class Robot extends MovingItem{

    private final project.Role.Robot robot;
    private final int timeTick;
    private int runTime = 0;

    public Robot(project.Role.Robot robot, int timeTick) {
        super(robot.velocity, robot.location, "images/%s-green.png".formatted(robot.id));
        this.robot = robot;
        this.timeTick = timeTick;
    }

    @Override
    public Image getImage() {
        String imageUrl = "images/" + this.robot.id;
        if (this.robot.batteryLevel >= 50) {
            imageUrl += "-green";
        } else if (this.robot.batteryLevel >= 10) {
            imageUrl += "-orange";
        }
        else {
            imageUrl += "-red";
        }

        if (this.robot.state == project.Role.Robot.State.Delivering) {
            imageUrl += "-black";
        }
        imageUrl += ".png";
        return new ImageIcon(imageUrl).getImage();
    }

    @Override
    public void move(){
        if (this.robot.batteryLevel >= 0) {
            super.move();
        }
        runTime++;
        if (runTime != 0 && runTime % timeTick == 0) {
            this.robot.batteryLevel -= 1;
        }
    }

}
