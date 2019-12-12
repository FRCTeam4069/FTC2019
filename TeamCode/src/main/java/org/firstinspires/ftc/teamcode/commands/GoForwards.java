package org.firstinspires.ftc.teamcode.commands;

public class GoForwards extends Command {

    private double speed;

    public GoForwards (double speed, double desiredPosition) {
        this.speed = speed;
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        drivetrain.update(0, speed, 0);
    }

    public boolean isFinished() {
        return false;
    }
}