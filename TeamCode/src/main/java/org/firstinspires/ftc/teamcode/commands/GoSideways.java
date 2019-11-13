package org.firstinspires.ftc.teamcode.commands;

public class GoSideways extends Command {

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if (detector.position > 300 && detector.position < 400000) {
            drivetrain.drive(0, 0 , 0);
        }
        else {
            drivetrain.drive(0.5, 0, 0);
        }
        telemetry.addData("Position", detector.position);

        telemetry.addData ("left front motor output: ", drivetrain.leftFrontOutput);
        telemetry.addData ("left back motor output: ", drivetrain.leftBackOutput);
        telemetry.addData ("right front motor output: ", drivetrain.rightFrontOutput);
        telemetry.addData ("right back motor output: ", drivetrain.rightBackOutput);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
