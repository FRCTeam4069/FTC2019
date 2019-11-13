package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Command> commandQueue;

    private Drivetrain drivetrain;
    private SkyStoneDetector detector;
    private Telemetry telemetry;

    public Scheduler(Drivetrain drivetrain, SkyStoneDetector detector, Telemetry telemetry) {
        this.drivetrain = drivetrain;
        this.detector = detector;
        this.telemetry = telemetry;
    }

    public void add(Command command) {
        command.setSubsystems(drivetrain, detector);
        commandQueue.add(command);
    }

    public void loop() {
        Command firstCommand = commandQueue.get(0);
        firstCommand.loop();
        if (firstCommand.isFinished()) {
            commandQueue.remove(0);
            commandQueue.get(0).loop();
        }
    }

}
