package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;

@Autonomous
public class PID extends LinearOpMode {

    private DcMotor LeftMotor;
    private DcMotor RightMotor;

    @Override
    public void runOpMode() {
        LeftMotor = hardwareMap.get(DcMotor.class, "motorTest");
        RightMotor = hardwareMap.get(DcMotor.class, "motorTest2");

        LeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        RightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        RightMotor.setMode(DcMotor.RunMode.RESET_ENCODERS);
        LeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while(opModeIsActive()) {
            int LMotorPosition = LeftMotor.getCurrentPosition();
            int RMotorPosition = RightMotor.getCurrentPosition();

            double averageMotorPosition = (LMotorPosition + RMotorPosition) / 2.0;
            double setpoint = 2000;
            double error = setpoint - averageMotorPosition;
            double kP = 0.01;

            RightMotor.setPower(kP * error);
            LeftMotor.setPower(kP * error);

            telemetry.addData("Status", "Running");
            telemetry.addData("Left Motor Position", LMotorPosition);
            telemetry.addData("Right Motor Position", RMotorPosition);
            telemetry.update();
        }
    }
}
