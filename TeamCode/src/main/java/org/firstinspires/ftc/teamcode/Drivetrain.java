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

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private Drivetrain(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     *
     *
     * @param strafe
     * @param forward
     * @param turn
     */
    public void drive(double strafe, double forward, double turn) {
        double direction = Math.atan2(strafe, forward);
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
        }
        else if (turn > 0) {
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
        }
    else {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}
    public static Drivetrain getInstance(HardwareMap hardwareMap, Telemetry telemetry) {
        if(instance == null) {
            instance = new Drivetrain(hardwareMap, telemetry);
        }

        return instance;
    }

}
