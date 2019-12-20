package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Passthrough;

@TeleOp
public class MasterTeleOp extends OpMode {

    Drivetrain drivetrain;
    Passthrough passthrough;
    Elevator elevator;
    private CRServo clamp;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        passthrough = new Passthrough(hardwareMap, telemetry);
        elevator = new Elevator(hardwareMap, telemetry);
        clamp = hardwareMap.get(CRServo.class, "clamp");
    }

    @Override public void loop() {
        drivetrain.update(gamepad1.left_stick_x, gamepad1.left_stick_y, -gamepad1.right_stick_x, gamepad1.x, gamepad1.b);
        passthrough.update(gamepad1.a, gamepad1.y);
        elevator.setMode(Elevator.ElevatorMode.MANUAL_CONTROL, gamepad1.right_stick_y);
        elevator.update();
        double speed = 0;
        if (gamepad1.left_bumper) {
            speed += 0.5;
        }
        else if (gamepad1.right_bumper) {
            speed -= 0.5;
        }
        clamp.setPower(speed);
    }
}
