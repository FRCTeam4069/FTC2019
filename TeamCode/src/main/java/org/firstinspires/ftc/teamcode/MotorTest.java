package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MotorTest extends OpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;

    public void init() {
       leftFront = hardwareMap.get(DcMotor.class, "leftFront");
       rightFront = hardwareMap.get(DcMotor.class, "rightFront");
       leftBack = hardwareMap.get(DcMotor.class, "leftBack");
       rightBack = hardwareMap.get(DcMotor.class, "rightBack");
    }

    public void loop() {
        leftBack.setPower (0.5);
    }
}
