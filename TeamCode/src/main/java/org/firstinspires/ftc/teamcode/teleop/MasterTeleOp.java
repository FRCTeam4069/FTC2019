package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Passthrough;

@TeleOp
public class MasterTeleOp extends OpMode {

 //   HardwareMap hardwareMap;
    Drivetrain drivetrain;
    Passthrough passthrough;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        passthrough = new Passthrough(hardwareMap, telemetry);
//        hardwareMap = new HardwareMap();
    }

    @Override public void loop() {
        drivetrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        passthrough.update(gamepad1.a, gamepad1.y);
    }
}
