package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MecanumTest")
public class MecanumTest extends OpMode {

    private Drivetrain drivetrain;

    @Override
    public void init() {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        drivetrain.drive (gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}