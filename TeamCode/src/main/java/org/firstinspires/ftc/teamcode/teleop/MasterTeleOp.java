package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;

@TeleOp
public class MasterTeleOp extends OpMode {

    Drivetrain drivetrain;
    Passthrough passthrough;
    DropOff dropOff;
    private Servo clamp;
    private CRServo tilt;
    double speed;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        passthrough = new Passthrough(hardwareMap, telemetry);
        dropOff = new DropOff(hardwareMap, telemetry);
        clamp = hardwareMap.get(Servo.class, "clamp");
        tilt = hardwareMap.get(CRServo.class, "tilt");
//        clamp.setPosition(0.5);
        speed = 1;
    }

    @Override public void loop() {
        double strafe = -gamepad1.left_stick_x;
        if(gamepad1.dpad_right) {
            strafe = 1.0;
        } else if(gamepad1.dpad_left) {
            strafe = -1.0;
        }
        drivetrain.update(strafe, -gamepad1.left_stick_y, -gamepad1.right_stick_x, gamepad1.left_bumper, gamepad1.right_bumper);
        if (gamepad2.left_bumper) {
            speed = 1.0;
        }
        else if (gamepad2.right_bumper) {
            speed = 0.4;
        }
        if (speed > 1) {
            speed = 1;
        }
        if (speed < 0) {
            speed = 0;
        }
        clamp.setPosition(speed);
        if (gamepad2.dpad_right) {
            tilt.setPower(0.5);
        }
        if (gamepad2.dpad_left) {
            tilt.setPower(-0.5);
        }
        passthrough.update(gamepad2.a, gamepad2.y, speed);
        dropOff.update(-gamepad2.right_stick_y / 2);
    }
}
