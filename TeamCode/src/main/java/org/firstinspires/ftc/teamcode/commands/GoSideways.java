package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GroundDetector;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public class GoSideways extends Command {

    private double speed;
    private double drivingPosition;
    private GroundDetector groundDetector;

    //input null if not being used in desired OpMode (0 for position)
    public GoSideways(double speed, double drivingPosition, NormalStoneDetector normalStoneDetector, SkyStoneDetector skyStoneDetector, GroundDetector groundDetector) {
        this.speed = speed;
        this.drivingPosition = drivingPosition;
        this.normalStoneDetector = normalStoneDetector;
        this.skyStoneDetector = skyStoneDetector;
        this.groundDetector = groundDetector;
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        drivetrain.update(speed, 0, -0.015, false, false);

        if(normalStoneDetector != null && drivingPosition == 0) {
            telemetry.addData("Position", normalStoneDetector.position);
        }
        else if(skyStoneDetector != null && drivingPosition == 0) {
            telemetry.addData("Sky Stone Position", skyStoneDetector.position);
        }
        else {
            telemetry.addData("Driving Position", drivingPosition);
        }
    }

    @Override
    public boolean isFinished() {
        boolean done = false;
        if(normalStoneDetector != null && drivingPosition == 0) {
            if (normalStoneDetector.position > 320 && normalStoneDetector.position != 500000) {
                done = true;
            }
        }
        else if(skyStoneDetector != null && drivingPosition == 0) {
            if (skyStoneDetector.position > 320 && skyStoneDetector.position != 500000) {
                done = true;
            }
        }
        else if(skyStoneDetector == null && normalStoneDetector == null && drivingPosition != 0) {
            if (drivingPosition  <  - drivetrain.rightFrontWheelPosition) {
                done = true;
            }
        }
        else if (skyStoneDetector == null && normalStoneDetector == null && drivingPosition == 0 && groundDetector != null) {
            if (groundDetector.isBlue) {
                done = true;
            }
            else if (groundDetector.isRed) {
                done = true;
            }
        }
        else{
            done = true;
        }
        if (done) {
            drivetrain.update(0, 0, 0, false, false);
        }
        return done;
    }
}