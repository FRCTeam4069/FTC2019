package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
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
        double rightFrontCurPos = ((double)rightFront.getCurrentPosition() / 480.0) * 2.0 * Math.PI;
        double leftFrontCurPos = ((double)leftFront.getCurrentPosition() / 480.0) * 2.0 * Math.PI;
        double rightBackCurPos = ((double)rightBack.getCurrentPosition() / 480.0) * 2.0 * Math.PI;
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

        double actualLeftBackVel = leftBackChange / timeElapsed;
        double actualRightFrontVel = rightFrontChange / timeElapsed;
        double actualLeftFrontVel = leftFrontChange / timeElapsed;
        double actualRightBackVel = rightBackChange / timeElapsed;

        double speed = Math.hypot(forward, strafe);
        double desiredLeftBackSpeed = Math.sin(direction - Math.PI / 4) * speed;
        double desiredRightFrontSpeed = Math.sin(direction - Math.PI / 4) * speed;
        double desiredLeftFrontSpeed = Math.sin(direction + Math.PI / 4) * speed;
        double desiredRightBackSpeed = Math.sin(direction + Math.PI / 4) * speed;

        desiredLeftBackSpeed = desiredLeftBackSpeed + turn;
        desiredLeftFrontSpeed = desiredLeftFrontSpeed + turn;
        desiredRightBackSpeed = desiredRightBackSpeed - turn;
        desiredRightFrontSpeed = desiredRightFrontSpeed - turn;



        double leftBackError = desiredLeftBackSpeed - actualLeftBackVel;
        double rightFrontError = desiredRightFrontSpeed - actualRightFrontVel;
        double leftFrontError = desiredLeftFrontSpeed - actualLeftFrontVel;
        double rightBackError = desiredRightBackSpeed - actualRightBackVel;

        double kP = -0.1;

        double leftBackOutput = desiredLeftBackSpeed + (leftBackError * kP);
        double leftFrontOutput = desiredLeftFrontSpeed + (leftFrontError * kP);
        double rightBackOutput = desiredRightBackSpeed + (rightBackError * kP);
        double rightFrontOutput = desiredRightFrontSpeed + (rightFrontError * kP);

        double fac1 = max(abs(leftBackOutput), abs(rightBackOutput));
        double fac2 = max(abs(leftFrontOutput), abs(rightFrontOutput));
        double speedScalingFactor = max(fac1, fac2);

        if (speedScalingFactor > 1) {
            leftBackOutput /= speedScalingFactor;
            leftFrontOutput /= speedScalingFactor;
            rightBackOutput /= speedScalingFactor;
            rightFrontOutput /= speedScalingFactor;
        }

        leftBack.setPower(leftBackOutput);
        rightBack.setPower(rightBackOutput);
        leftFront.setPower(leftFrontOutput);
        rightFront.setPower(rightFrontOutput);

        lastTime = currentTime;
        BLlastPosition = leftBackCurPos;
        FRlastPosition = rightFrontCurPos;
        FLlastPosition = leftFrontCurPos;
        BRlastPosition = rightBackCurPos;
    }
    public static Drivetrain getInstance(HardwareMap hardwareMap, Telemetry telemetry) {
        if (instance == null) {
            instance = new Drivetrain(hardwareMap, telemetry);
        }

        return instance;
    }

}
