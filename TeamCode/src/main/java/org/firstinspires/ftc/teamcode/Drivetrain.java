package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Drivetrain {
    public static final double ANGULAR_VELOCITY_M = 9.068;
    public static final double ANGULAR_VELOCITY_B = -1.405;
    //x = turn value
    
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    Telemetry telemetry;
    public NavxMicroNavigationSensor navx;

    private double lastTime = -1.0;
    private double BLlastPosition = -1.0;
    private double FRlastPosition = -1.0;
    private double FLlastPosition = -1.0;
    private double BRlastPosition = -1.0;
    double lastAngle = Double.NaN;
    private double lastError = Double.NaN;
    public double leftBackWheelPosition;
    public double rightBackWheelPosition;
    public double leftFrontWheelPosition;
    public double rightFrontWheelPosition;
    public double averageWheelPosition;
    private static Drivetrain instance;

    public Drivetrain(HardwareMap hardwareMap, Telemetry telemetry) {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        this.telemetry = telemetry;
        navx = hardwareMap.get (NavxMicroNavigationSensor.class, "navx");
        leftBackWheelPosition = leftBack.getCurrentPosition();
        rightBackWheelPosition = rightBack.getCurrentPosition();
        leftFrontWheelPosition = leftFront.getCurrentPosition();
        rightFrontWheelPosition = rightFront.getCurrentPosition();
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Gets the angle of the robot.
     *
     * @return The yaw of the robot
     */
    public double getAngle() {
        Orientation orientation = navx.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        return orientation.firstAngle;
    }

    /**
     * A function to translate operator inputs into wheel speeds and then update the motors.
     *
     * @param forward The component of motion in the forward direction
     * @param strafe The component of motion in the lateral direction
     * @param turn
     */
    public void update(double strafe, double forward, double turn, boolean x, boolean b) {

        double direction = Math.atan2(forward, strafe);
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

        double actualLeftBackVel = leftBackChange / timeElapsed;
        double actualRightFrontVel = rightFrontChange / timeElapsed;
        double actualLeftFrontVel = leftFrontChange / timeElapsed;
        double actualRightBackVel = rightBackChange / timeElapsed;

        double speed = Math.hypot(forward, strafe);
        double desiredLeftBackSpeed = Math.sin(direction - Math.PI / 4) * speed;
        double desiredRightFrontSpeed = Math.sin(direction - Math.PI / 4) * speed;
        double desiredLeftFrontSpeed = Math.sin(direction + Math.PI / 4) * speed;
        double desiredRightBackSpeed = Math.sin(direction + Math.PI / 4) * speed;

        double expectedTurnSpeed = turn * 10;
        expectedTurnSpeed += strafe * 1.2;

        if (x) {
            expectedTurnSpeed += 3;
        }
        else if (b) {
            expectedTurnSpeed -= 3;
        }

        double turnSpeed = Math.toRadians(navx.getAngularVelocity(AngleUnit.DEGREES).zRotationRate);

        double error = expectedTurnSpeed - turnSpeed;
        double derivative;
        if (Double.isNaN(lastError)) {
            derivative = 0;
        } else {
            derivative = (error - lastError) / timeElapsed;
        }


        telemetry.addData("error", error);
        telemetry.addData("derivative", derivative);

        double turnP = 0.1;
        double turnD = 0;
        double output = error * turnP + turnD * derivative;

        telemetry.addData("expected turn speed", expectedTurnSpeed);
        telemetry.addData("turn speed", turnSpeed);


        desiredLeftBackSpeed = desiredLeftBackSpeed + output;
        desiredLeftFrontSpeed = desiredLeftFrontSpeed + output;
        desiredRightBackSpeed = desiredRightBackSpeed - output;
        desiredRightFrontSpeed = desiredRightFrontSpeed - output;



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
        lastError = error;
        BLlastPosition = leftBackCurPos;
        FRlastPosition = rightFrontCurPos;
        FLlastPosition = leftFrontCurPos;
        BRlastPosition = rightBackCurPos;
        averageWheelPosition = ((((double)leftBack.getCurrentPosition()) + ((double)rightBack.getCurrentPosition()) + ((double)leftFront.getCurrentPosition()) + ((double)rightFront.getCurrentPosition())) / 4);
        rightFrontWheelPosition = rightFront.getCurrentPosition();
        leftFrontWheelPosition = leftFront.getCurrentPosition();
        rightBackWheelPosition = rightBack.getCurrentPosition();
        leftBackWheelPosition = leftBack.getCurrentPosition();
        telemetry.addData("right Front position: ", rightFrontWheelPosition);
        telemetry.addData("right Back position: ", rightBackWheelPosition);
        telemetry.addData("left Front position: ", leftFrontWheelPosition);
        telemetry.addData("left Back position: ", leftBackWheelPosition);
        telemetry.addData("Average Position: ", averageWheelPosition);
    }

    public static Drivetrain getInstance(HardwareMap hardwareMap, Telemetry telemetry) {
        if (instance == null) {
            instance = new Drivetrain(hardwareMap, telemetry);
        }
        return instance;
    }

}
