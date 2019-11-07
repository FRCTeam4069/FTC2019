package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Drivetrain;

@TeleOp(name = "MecanumTest")
public class MecanumTest extends OpMode {

    private Drivetrain drivetrain;

    @Override
    public void init() {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        //drivetrain.drive (-gamepad1.left_stick_x, gamepad1.left_stick_y, -gamepad1.right_stick_x);
        drivetrain.drive (0.5, 0, 0);
        telemetry.addData ("left front motor output: ", drivetrain.leftFrontOutput);
        telemetry.addData ("left back motor output: ", drivetrain.leftBackOutput);
        telemetry.addData ("right front motor output: ", drivetrain.rightFrontOutput);
        telemetry.addData ("right back motor output: ", drivetrain.rightBackOutput);
    }
}