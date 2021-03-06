package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public abstract class Command {

    protected Drivetrain drivetrain;
    protected NormalStoneDetector normalStoneDetector;
    protected HardwareMap hardwareMap;
    protected Telemetry telemetry;
    protected Passthrough passthrough;
    protected SkyStoneDetector skyStoneDetector;
    protected DropOff dropOff;



    public void setSubsystems(Drivetrain drivetrain, NormalStoneDetector normalStoneDetector, SkyStoneDetector skyStoneDetector, Telemetry telemetry, Passthrough passthrough, DropOff dropOff) {
        this.drivetrain = drivetrain;
        this.normalStoneDetector = normalStoneDetector;
        this.telemetry = telemetry;
        this.passthrough = passthrough;
        this.skyStoneDetector = skyStoneDetector;
        this.dropOff = dropOff;
    }

    abstract void start();
    abstract void loop();
    abstract boolean isFinished();

}
