package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "coolerhdgff teleop")
public class OperatorDrive extends OpMode {

    private DcMotor leftF;
    private DcMotor right;

    @Override
    public void init() {
//        left = hardwareMap.get(DcMotor.class, "mecanum");
//        right = hardwareMap.get(DcMotor.class, "motorTest2");


//        right.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

      //  double ABCD = gamepad1.right_trigger;
    //    left.setPower(ABCD);

//        double speed = gamepad1.right_trigger - gamepad1.left_trigger;
//        double turn = gamepad1.left_stick_x;
//
//        left.setPower(speed + turn);
//        right.setPower(speed - turn);
    }
}
