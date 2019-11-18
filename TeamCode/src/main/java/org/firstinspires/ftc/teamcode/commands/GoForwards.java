package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Drivetrain;

public class GoForwards extends Command {

    private double speed;
    private double desiredPosition;
    double error;

    public GoForwards (double speed, double desiredPosition) {
        this.speed = speed;
        this.desiredPosition = desiredPosition;
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
    double leftFrontError = drivetrain.leftFrontWheelPosition - desiredPosition;
    double rightFrontError = drivetrain.rightFrontWheelPosition - desiredPosition;
    double leftBackError = drivetrain.leftBackWheelPosition - desiredPosition;
    double rightBackError = drivetrain.rightBackWheelPosition - desiredPosition;
    double kP = 0.01;
    error = (leftFrontError + leftBackError + rightFrontError + rightBackError) / 4;
    if (error > 2000) {
        drivetrain.drive (0, 1, 0);
    }
    else {
        drivetrain.drive (0, error * kP, 0);
    }
    }

    public boolean isFinished() {
        return error < 10 || error > -100;
    }
}