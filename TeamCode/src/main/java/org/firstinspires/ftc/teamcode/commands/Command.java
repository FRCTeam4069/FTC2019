package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public abstract class Command {

    protected Drivetrain drivetrain;
    protected SkyStoneDetector detector;
    protected Telemetry telemetry;

    public void setSubsystems(Drivetrain drivetrain, SkyStoneDetector detector, Telemetry telemetry) {
        this.drivetrain = drivetrain;
        this.detector = detector;
        this.telemetry = telemetry;
    }

    abstract void start();
    abstract void loop();
    abstract boolean isFinished();

}
