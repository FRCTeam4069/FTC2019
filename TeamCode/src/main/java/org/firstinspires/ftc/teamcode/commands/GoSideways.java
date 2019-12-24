package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public class GoSideways extends Command {

    private double speed;
    private NormalStoneDetector normalStoneDetector;
    private  SkyStoneDetector skyStoneDetector;

    //input null if not being used in desired OpMode
    public GoSideways(double speed, NormalStoneDetector normalStoneDetector, SkyStoneDetector skyStoneDetector) {
        this.speed = speed;
        this.normalStoneDetector = normalStoneDetector;
        this.skyStoneDetector = skyStoneDetector;
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        drivetrain.update(speed, 0, 0, false, false);

        if(normalStoneDetector != null) {
            telemetry.addData("Position", normalStoneDetector.position);
        }
        if(skyStoneDetector != null) {
            telemetry.addData("Sky Stone Position", skyStoneDetector.position);
        }
    }

    @Override
    public boolean isFinished() {
        if(normalStoneDetector != null) {
            return normalStoneDetector.position < 320 && normalStoneDetector.position != 500000;
        }
        else {
            return skyStoneDetector.position < 320 && skyStoneDetector.position != 500000;
        }
    }
}