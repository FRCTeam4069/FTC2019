package org.firstinspires.ftc.teamcode.commands;

public class DriveForTime extends Command {

    private double speed;
    private double startTime;
    private double duration;

    public DriveForTime(double speed, double duration) {
        this.speed = speed;
        this.duration = duration;
    }

    @Override
    void start() {
        startTime = System.currentTimeMillis();
    }

    @Override
    void loop() {
        drivetrain.update(0, -speed, 0, false, false);
    }

    @Override
    boolean isFinished() {
        return System.currentTimeMillis() >= startTime + duration;
    }
}
