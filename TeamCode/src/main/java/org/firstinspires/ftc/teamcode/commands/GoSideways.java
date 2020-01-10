package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public class GoSideways extends Command {

    private double speed;
    private double drivingPosition;

    //input null if not being used in desired OpMode (0 for position)
    public GoSideways(double speed, double drivingPosition, NormalStoneDetector normalStoneDetector, SkyStoneDetector skyStoneDetector) {
        this.speed = speed;
        this.drivingPosition = drivingPosition;
        this.normalStoneDetector = normalStoneDetector;
        this.skyStoneDetector = skyStoneDetector;
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        drivetrain.update(speed, 0, 0, false, false);

        if(normalStoneDetector != null && drivingPosition == 0) {
            telemetry.addData("Position", normalStoneDetector.position);
        }
        if(skyStoneDetector != null && drivingPosition == 0) {
            telemetry.addData("Sky Stone Position", skyStoneDetector.position);
        }
        else {
            telemetry.addData("Driving Position", drivingPosition);
        }
    }

    @Override
    public boolean isFinished() {
        boolean done;
        if(normalStoneDetector != null && drivingPosition == 0) {
            done = normalStoneDetector.position > 320 && normalStoneDetector.position != 500000;
        }
        else if(skyStoneDetector != null && drivingPosition == 0) {
            done = skyStoneDetector.position > 320 && skyStoneDetector.position != 500000;
        }
        else {
            done = drivingPosition  < -drivetrain.rightFrontWheelPosition;
        }
        if (done) {
            drivetrain.update(0, 0, 0, false, false);
        }
        return done;
    }
}