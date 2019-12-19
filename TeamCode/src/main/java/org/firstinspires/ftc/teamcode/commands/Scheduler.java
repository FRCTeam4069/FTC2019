package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Command> commandQueue;

    private Drivetrain drivetrain;
    private NormalStoneDetector detector;
    private Telemetry telemetry;
    private Passthrough passthrough;

    public Scheduler(Drivetrain drivetrain, NormalStoneDetector detector, Telemetry telemetry, Passthrough passthrough) {
        this.drivetrain = drivetrain;
        this.detector = detector;
        this.telemetry = telemetry;
        this.commandQueue = new ArrayList<>();
        this.passthrough = passthrough;
    }

    public void add(Command command) {
        command.setSubsystems(drivetrain, detector, telemetry, passthrough);
        commandQueue.add(command);
    }

    public void loop() {
        Command firstCommand = commandQueue.get(0);
        firstCommand.loop();
        if (firstCommand.isFinished()) {
            commandQueue.remove(0);
            if (!commandQueue.isEmpty()) {
                commandQueue.get(0).loop();
            }
        }
    }

}
