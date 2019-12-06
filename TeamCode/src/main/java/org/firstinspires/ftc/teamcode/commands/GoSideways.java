package org.firstinspires.ftc.teamcode.commands;

public class GoSideways extends Command {

    private double speed;

    public GoSideways(double speed) {
        this.speed = speed;
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        drivetrain.update(speed, 0, 0);

        telemetry.addData("Position", detector.position);


    }

    @Override
    public boolean isFinished() {
        return detector.position > 300 && detector.position < 400000;
    }
}
