package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MotorTest extends OpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    public void init() {
       leftFront = hardwareMap.get(DcMotor.class, "leftFront");
       rightFront = hardwareMap.get(DcMotor.class, "rightFront");
       leftBack = hardwareMap.get(DcMotor.class, "leftBack");
       rightBack = hardwareMap.get(DcMotor.class, "rightBack");
       rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
       rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double magnitude = Math.hypot(forward, strafe);
        double angle = Math.atan2(forward, strafe);
        double x = Math.PI/2.0;
        double leftBackRightFront = Math.sin(angle - Math.PI / 4.0) * magnitude + turn;
        double leftFrontRightBack = Math.sin(angle + Math.PI / 4.0) * magnitude + turn;
        double maxValue = Math.max(Math.abs(leftBackRightFront), Math.abs(leftFrontRightBack));
        leftBack.setPower(leftBackRightFront / maxValue);
        rightBack.setPower(leftFrontRightBack / maxValue);
        leftFront.setPower(leftFrontRightBack / maxValue);
        rightFront.setPower(leftBackRightFront / maxValue);
        String speeds = "leftBackRightFront = " + leftBackRightFront + " leftFrontRightBack = " + leftFrontRightBack;
        String output = "Angle = " + angle + " Strafe = " + strafe;
        telemetry.addData("Angle and Strafe", output);
        telemetry.addData("Speeds", speeds);

    }
}
