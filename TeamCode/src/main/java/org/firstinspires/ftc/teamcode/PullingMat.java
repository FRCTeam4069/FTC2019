package org.firstinspires.ftc.teamcode;

import android.drm.DrmStore;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;

@Autonomous
public class PullingMat extends LinearOpMode{

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        telemetry.addData("Left Front", leftFront.getCurrentPosition());
        telemetry.addData("Right Front", rightFront.getCurrentPosition());
        telemetry.addData("Left Back", leftBack.getCurrentPosition());
        telemetry.addData("Right Back ", rightBack.getCurrentPosition());

       leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        double startTime = System.currentTimeMillis();

        while (opModeIsActive()) {

            telemetry.addData("Left Front", leftFront.getCurrentPosition());
            telemetry.addData("Right Front", rightFront.getCurrentPosition());
            telemetry.addData("Left Back", leftBack.getCurrentPosition());
            telemetry.addData("Right Back ", rightBack.getCurrentPosition());

            int leftFrontPos = leftFront.getCurrentPosition();
            int rightFrontPos = rightFront.getCurrentPosition();
            int leftBackPos = leftBack.getCurrentPosition();
            int rightBackPos = rightBack.getCurrentPosition();

            double averageMotorPosition = (leftFrontPos + rightFrontPos + leftBackPos + rightBackPos) / 4.0;
            double setpoint = 2000;
            double originalSetpoint = -2000;
            double error = setpoint - averageMotorPosition;
            double reversedError = originalSetpoint - averageMotorPosition;
            double kP = 0.01;
            double currentTime = System.currentTimeMillis();
            double timeElapsed = currentTime - startTime;

            leftFront.setTargetPosition(600);
            leftBack.setTargetPosition(600);
            rightFront.setTargetPosition(600);
            rightBack.setTargetPosition(600);

            leftFront.setPower(kP * reversedError);
            rightFront.setPower(kP * reversedError);
            leftBack.setPower(kP * reversedError);
            rightBack.setPower(kP * reversedError);


            /*
            if (timeElapsed <500) {
                leftFront.setPower(kP * error);
                rightFront.setPower(kP * error);
                leftBack.setPower(kP * error);
                rightBack.setPower(kP * error);
            }

            if (timeElapsed >= 5000) {
                leftFront.setPower(kP * reversedError);
                rightFront.setPower(kP * reversedError);
                leftBack.setPower(kP * reversedError);
                rightBack.setPower(kP * reversedError);
            }

            if (timeElapsed >= 15000) {
                leftFront.setPower(kP * error);
                rightFront.setPower(kP * reversedError);
                leftBack.setPower(kP * reversedError);
                rightBack.setPower(kP * error);
            }




            */
            telemetry.update();
        }
    }
}
