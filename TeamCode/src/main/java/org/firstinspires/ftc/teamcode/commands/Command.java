package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public abstract class Command {

    protected Drivetrain drivetrain;
    protected SkyStoneDetector detector;
    protected Telemetry telemetry;
    protected Passthrough passthrough;

    public void setSubsystems(Drivetrain drivetrain, SkyStoneDetector detector, Telemetry telemetry, Passthrough passthrough) {
        this.drivetrain = drivetrain;
        this.detector = detector;
        this.telemetry = telemetry;
        this.passthrough = passthrough;

    }

    abstract void start();
    abstract void loop();
    abstract boolean isFinished();

}
