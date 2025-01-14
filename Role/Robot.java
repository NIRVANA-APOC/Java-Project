package project.Role;

import project.animation.MovingItem.*;
import project.utils.Shape.Point;

public class Robot extends Role {

    public String id;
    public Point location;
    public Velocity velocity;
    public int payloadWeight;
    public int batteryLevel;
    public State state;
    project.animation.Robot animation;

    public enum State {
        Free, Charging, Fetching, Delivering
    }

    public Robot(String id, Velocity velocity, Point location, int payloadWeight) {
        this.id = id;
        this.velocity = velocity;
        this.location = location;
        this.payloadWeight = payloadWeight;
        this.batteryLevel = 100;
        this.state = State.Free;
    }

    public boolean fetch() {
        state = State.Fetching;

        return true;
    }

    public boolean deliver() {
        state = State.Delivering;

        return true;
    }

}
