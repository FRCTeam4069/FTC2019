package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MovingByHand extends OpMode {

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

        telemetry.addData("Left Front", leftFront.getCurrentPosition());
        telemetry.addData("Right Front", rightFront.getCurrentPosition());
        telemetry.addData("Left Back", leftBack.getCurrentPosition());
        telemetry.addData("Right Back ", rightBack.getCurrentPosition());
    }



    @Override
    public void loop() {

        telemetry.addData("Left Front", leftFront.getCurrentPosition());
        telemetry.addData("Right Front", rightFront.getCurrentPosition());
        telemetry.addData("Left Back", leftBack.getCurrentPosition());
        telemetry.addData("Right Back ", rightBack.getCurrentPosition());

        telemetry.update();
    }
}
