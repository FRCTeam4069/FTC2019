package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "simple teleop drive")
public class ArcadeDrive extends OpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;



    @Override
    public void init() {
        telemetry.addData("Working?", true);
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER                                                                                                                                                                                                                                                                                                                                                                      );
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        telemetry.addData("left Stick X", gamepad1.left_stick_x );
        float speed = gamepad1.right_trigger - gamepad1.left_trigger;
        float turn = gamepad1.left_stick_x;
        //leftDrive.setPower(speed - turn);
        //rightDrive.setPower(speed + turn);
        leftDrive.setPower(0.5);
        rightDrive.setPower(0.5);


        telemetry.addData("Left encoder", leftDrive.getCurrentPosition());
        telemetry.addData("Right encoder", rightDrive.getCurrentPosition());

        if (leftDrive.getCurrentPosition() == 2000) {

            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }

        telemetry.update();
    }
}
