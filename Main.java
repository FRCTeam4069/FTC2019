package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class Main extends LinearOpMode {

    private DcMotor LeftMotor;
    private DcMotor RightMotor;


    @Override
    public void runOpMode() {



        LeftMotor = hardwareMap.get (DcMotor.class, "motorTest");
        RightMotor = hardwareMap.get (DcMotor.class, "motorTest2");

        double pwrL = 0;
        double pwrR = 0;
        double pwrLT = 0;
        double pwrRT = 0;

        LeftMotor.setDirection(DcMotor.Direction.FORWARD);
        RightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            pwrLT = this.gamepad1.left_stick_x;
            pwrRT = this.gamepad1.left_stick_x;
            pwrL = this.gamepad1.left_stick_y;
            pwrR = this.gamepad1.left_stick_y;

            double pwrl = pwrL + pwrLT;
            double pwrr = pwrR + pwrRT;

            if (pwrl > 1) {
                pwrL = pwrL/2;
            }

            if (pwrr > 1) {
                pwrR = pwrR/2;
            }

            if (pwrl < -1) {
                pwrL = pwrL/2;
            }

            if (pwrr < -1) {
                pwrR = pwrR/2;
            }

            pwrL = pwrL - pwrLT;
            pwrR = pwrR + pwrRT;

            LeftMotor.setPower (pwrL);
            RightMotor.setPower (pwrR);
            
            double LTrigger = this.gamepad1.left_trigger;
            if (LTrigger != 0) {
                LeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
                RightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
            }
            
            LeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            RightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
            int LMotorPosition = LeftMotor.getCurrentPosition();
            int RMotorPosition = RightMotor.getCurrentPosition();
            

            telemetry.addData("Status", "Running");
            telemetry.addData("Left Motor Position:", LMotorPosition);
            telemetry.addData("Right Motor Position", RMotorPosition);
            telemetry.update();
            
        }
    }
}

