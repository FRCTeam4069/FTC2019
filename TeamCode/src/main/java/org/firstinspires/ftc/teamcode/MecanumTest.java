package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "MecanumTest")
public class MecanumTest extends OpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
        double leftBackSpeed = 0;
        double direction = Math.PI * 0;
        double speed = 1;

        double rightFrontSpeed = 0;

        leftBackSpeed = Math.sin(direction - Math.PI/4) * speed;
        rightFrontSpeed = Math.sin(direction - Math.PI/4) * speed;

        double leftFrontSpeed = 0;

        double rightBackSpeed = 0;

        leftFrontSpeed = Math.sin(direction + Math.PI/4) * speed;
        rightBackSpeed = Math.sin(direction + Math.PI/4) * speed;

        leftBack.setPower(leftBackSpeed);
        rightBack.setPower(rightBackSpeed);
        leftFront.setPower(leftFrontSpeed);
        rightFront.setPower(rightFrontSpeed);
    }
}
