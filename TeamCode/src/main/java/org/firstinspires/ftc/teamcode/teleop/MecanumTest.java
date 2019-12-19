package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drivetrain;

@TeleOp(name = "MecanumTest")
public class MecanumTest extends OpMode {

    private Drivetrain drivetrain;
    private Servo servo;
    private double position = 0.5;

    @Override
    public void init() {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
        servo = hardwareMap.get(Servo.class, "finalSolution");
    }

    @Override
    public void loop() {
        drivetrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.x, gamepad1.b);
        if (gamepad1.left_bumper) {
            position += 0.001;
        }
        else if (gamepad1.right_bumper) {
            position -= 0.001;
        }
    }
}