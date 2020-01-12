package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GoForwards extends Command {

    private double speed;
    private Servo clamp;
    public double desiredPosition;
    private double startPosition;
    private boolean reversed;

    public GoForwards (double speed, double desiredPosition) {
        this.speed = speed;
        this.reversed = speed < 0.0;
        this.desiredPosition = desiredPosition;

    }

    @Override
    public void start() {
        this.startPosition = drivetrain.averageWheelPosition;
    }

    @Override
    public void loop() {
        drivetrain.update(0, -speed, 0, false, false);
        telemetry.addData("Desired Position", desiredPosition);
        telemetry.addData("Actual Position", drivetrain.averageWheelPosition);
    }

    public boolean isFinished() {
        if(!reversed) {
            return Math.abs(desiredPosition - startPosition) < Math.abs(drivetrain.averageWheelPosition);
        } else {
            return Math.abs(desiredPosition - Math.abs(startPosition)) > Math.abs(drivetrain.averageWheelPosition);
        }
    }
}