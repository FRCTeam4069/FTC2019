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

        telemetry.addData("Left front mode", leftFront.getMode());
    }

    @Override
    public void loop() {
        if (gamepad1.left_stick_x != 0 || gamepad1.left_stick_y != 0) {

            double y = gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double theta = Math.atan2(y, x);
            double leftBackSpeed = 0;
            double direction = theta;
            double speed = Math.hypot(x, y);

            double rightFrontSpeed = 0;

            leftBackSpeed = Math.sin(direction - Math.PI / 4) * speed;
            rightFrontSpeed = Math.sin(direction - Math.PI / 4) * speed;

            double leftFrontSpeed = 0;

            double rightBackSpeed = 0;

            leftFrontSpeed = Math.sin(direction + Math.PI / 4) * speed;
            rightBackSpeed = Math.sin(direction + Math.PI / 4) * speed;

            leftBack.setPower(leftBackSpeed);
            rightBack.setPower(rightBackSpeed);
            leftFront.setPower(leftFrontSpeed);
            rightFront.setPower(rightFrontSpeed);

            telemetry.addData("angle", theta);
            telemetry.addData("FL=", leftFrontSpeed);
            telemetry.addData("FR=", rightFrontSpeed);
            telemetry.addData("BL=", leftBackSpeed);
            telemetry.addData("BR=", rightBackSpeed);
        } else {
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
        }
    }
}
