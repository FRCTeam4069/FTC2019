package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Passthrough;

@TeleOp
public class MasterTeleOp extends OpMode {

    Drivetrain drivetrain;
    Passthrough passthrough;
    Elevator elevator;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        passthrough = new Passthrough(hardwareMap, telemetry);
        elevator = new Elevator(hardwareMap, telemetry);
    }

    @Override public void loop() {
        drivetrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x * 0.7);
        passthrough.update(gamepad1.a, gamepad1.y);
        elevator.setMode(Elevator.ElevatorMode.MANUAL_CONTROL, gamepad2.right_stick_y);
        elevator.update();
    }
}
