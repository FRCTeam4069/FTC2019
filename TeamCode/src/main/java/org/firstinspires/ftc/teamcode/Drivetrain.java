package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Drivetrain {
    private static Drivetrain instance;

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    Telemetry telemetry;

    private double lastTime = -1.0;
    private double BLlastPosition = -1.0;
    private double FRlastPosition = -1.0;
    private double FLlastPosition = -1.0;
    private double BRlastPosition = -1.0;

    private Drivetrain(HardwareMap hardwareMap, Telemetry telemetry) {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        this.telemetry = telemetry;
    }

    /**
     * A function to translate operator inputs into wheel speeds and then update the motors.
     *
     * @param forward The component of motion in the forward direction
     * @param strafe The component of motion in the lateral direction
     * @param turn
     */
    public void drive(double strafe, double forward, double turn) {

        double direction = Math.atan2(strafe, forward);
        double leftBackCurPos = ((double)leftBack.getCurrentPosition() / 480.0) * 2.0 * Math.PI;
        double rightFrontCurPos = ((double)rightFront.getCurrentPosition() / 480.0 * 2.0 * Math.PI);
        double leftFrontCurPos = ((double)leftFront.getCurrentPosition() / 480.0 * 2.0 * Math.PI);
        double rightBackCurPos = ((double)rightBack.getCurrentPosition() / 480.0 * 2.0 * Math.PI);
        double leftBackChange = leftBackCurPos - BLlastPosition;
        double rightFrontChange = rightFrontCurPos - FRlastPosition;
        double leftFrontChange = leftFrontCurPos - FLlastPosition;
        double rightBackChange = rightBackCurPos - BRlastPosition;

        double currentTime = System.currentTimeMillis();
        double timeElapsed;
        if (lastTime > 0){
            timeElapsed = currentTime - lastTime;
        }
        else  {
            timeElapsed = 0;
        }

        double setVelocity = 1.0;

        double leftBackVel = leftBackChange / timeElapsed;
        double rightFrontVel = rightFrontChange / timeElapsed;
        double leftFrontVel = leftFrontChange / timeElapsed;
        double rightBackVel = rightBackChange / timeElapsed;
        double desiredVel = 0;
        double leftBackError = desiredVel - leftBackVel;
        double rightFrontError = desiredVel - rightFrontVel;
        double leftFrontError = desiredVel - leftFrontVel;
        double rightBackError = desiredVel - rightBackVel;
        double kP = 0.1;

        double speed = Math.hypot(forward, strafe);
        double leftBackSpeed = Math.sin(direction - Math.PI / 4) * speed;
        double rightFrontSpeed = Math.sin(direction - Math.PI / 4) * speed;
        double leftFrontSpeed = Math.sin(direction + Math.PI / 4) * speed;
        double rightBackSpeed = Math.sin(direction + Math.PI / 4) * speed;

        leftBack.setPower(leftBackSpeed);
        rightBack.setPower(rightBackSpeed);
        leftFront.setPower(leftFrontSpeed);
        rightFront.setPower(rightFrontSpeed);

        double fac1 = max(abs(leftBackSpeed), abs(rightBackSpeed));
        double fac2 = max(abs(leftFrontSpeed), abs(rightFrontSpeed));
        double speedScalingFactor = max(fac1, fac2);

        if (turn < 0) {
            leftBack.setPower(leftBackSpeed + turn);
            leftFront.setPower(leftFrontSpeed + turn);
            rightBack.setPower(leftBackSpeed - turn);
            rightFront.setPower(rightFrontSpeed - turn);
            if (leftBackSpeed + turn < -1 || leftFrontSpeed + turn < -1) {
                leftBack.setPower((leftBackSpeed + turn) / speedScalingFactor);
                leftFront.setPower((leftFrontSpeed + turn) / speedScalingFactor);
                rightBack.setPower((rightBackSpeed + turn) / speedScalingFactor);
                rightFront.setPower((rightFrontSpeed + turn) / speedScalingFactor);
            }
            if (rightBackSpeed + turn > 1 || rightFrontSpeed + turn > 1) {
                leftBack.setPower((leftBackSpeed + turn) / speedScalingFactor);
                leftFront.setPower((leftFrontSpeed + turn) / speedScalingFactor);
                rightBack.setPower((rightBackSpeed + turn) / speedScalingFactor);
                rightFront.setPower((rightFrontSpeed + turn) / speedScalingFactor);
            }
        } else if (turn > 0) {
            rightBack.setPower(rightBackSpeed - turn);
            rightFront.setPower(rightFrontSpeed - turn);
            leftBack.setPower(leftBackSpeed + turn);
            leftFront.setPower(leftFrontSpeed + turn);
            if (leftBackSpeed + turn < -1 || leftFrontSpeed + turn < -1) {
                leftBack.setPower((leftBackSpeed + turn) / speedScalingFactor);
                leftFront.setPower((leftFrontSpeed + turn) / speedScalingFactor);
                rightBack.setPower((rightBackSpeed + turn) / speedScalingFactor);
                rightFront.setPower((rightFrontSpeed + turn) / speedScalingFactor);
            }
            if (rightBackSpeed + turn > 1 || rightFrontSpeed + turn > 1) {
                leftBack.setPower((leftBackSpeed + turn) / speedScalingFactor);
                leftFront.setPower((leftFrontSpeed + turn) / speedScalingFactor);
                rightBack.setPower((rightBackSpeed + turn) / speedScalingFactor);
                rightFront.setPower((rightFrontSpeed + turn) / speedScalingFactor);
            }
        } else {
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftBack.setPower(0);
            rightBack.setPower(0);
        }
        telemetry.addData ("Left Front Power: ", leftFrontSpeed);
        telemetry.addData ("Left Back Power: ", leftBackSpeed);
        telemetry.addData ("Right Front Power: ", rightFrontSpeed);
        telemetry.addData ("Right Back Power: ", rightBackSpeed);
        lastTime = currentTime;
        BLlastPosition = leftBack.getCurrentPosition();
        FRlastPosition = rightFront.getCurrentPosition();
        FLlastPosition = leftFront.getCurrentPosition();
        BRlastPosition = rightBack.getCurrentPosition();
    }
    public static Drivetrain getInstance(HardwareMap hardwareMap, Telemetry telemetry) {
        if (instance == null) {
            instance = new Drivetrain(hardwareMap, telemetry);
        }

        return instance;
    }

}
