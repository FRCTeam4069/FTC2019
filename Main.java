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

        LeftMotor.setDirection(DcMotor.Direction.FORWARD);
        RightMotor.setDirection(DcMotor.Direction.REVERSE);

        LeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        RightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        LeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();

        while (opModeIsActive()) {


            int LMotorPosition = LeftMotor.getCurrentPosition();
            int RMotorPosition = RightMotor.getCurrentPosition();

            double averageMotorPosition = (LMotorPosition + RMotorPosition) /2;
            double setPoint = 2000;
            double eP = setPoint - averageMotorPosition;
            double k = 0.01;



            RightMotor.setPower (k * eP);
            LeftMotor.setPower (k * eP);


            telemetry.addData("Status", "Running");
            telemetry.addData("Left Motor Position:", LMotorPosition);
            telemetry.addData("Right Motor Position", RMotorPosition);
            telemetry.update();

        }
    }
}

