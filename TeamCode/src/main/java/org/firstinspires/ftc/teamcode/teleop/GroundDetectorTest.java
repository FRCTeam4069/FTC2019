package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GroundDetector;

@TeleOp
public class GroundDetectorTest extends OpMode {
   GroundDetector groundDetector;
    @Override
    public void init() {
        groundDetector = new GroundDetector(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        telemetry.addData("isBlue", groundDetector.isBlue);
        telemetry.addData("isRed", groundDetector.isRed);
    }
}
