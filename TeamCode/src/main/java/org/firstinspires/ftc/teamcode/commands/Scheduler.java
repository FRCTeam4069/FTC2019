package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Command> commandQueue;

    private Drivetrain drivetrain;
    private NormalStoneDetector normalStoneDetector;
    private Telemetry telemetry;
    private Passthrough passthrough;
    private SkyStoneDetector skyStoneDetector;
    private DropOff dropOff;
    private boolean programBegun = false;



    public Scheduler(Drivetrain drivetrain, NormalStoneDetector normalStoneDetector, SkyStoneDetector skyStoneDetector, Telemetry telemetry, Passthrough passthrough, DropOff dropOff) {
        this.drivetrain = drivetrain;
        this.normalStoneDetector = normalStoneDetector;
        this.telemetry = telemetry;
        this.commandQueue = new ArrayList<>();
        this.passthrough = passthrough;
        this.skyStoneDetector = skyStoneDetector;
        this.dropOff = dropOff;
    }

    public void add(Command command) {
        command.setSubsystems(drivetrain, normalStoneDetector, skyStoneDetector, telemetry, passthrough, dropOff);
        commandQueue.add(command);
    }

    public void loop() {
        if (commandQueue.isEmpty()) {
            return;
        }

        Command firstCommand = commandQueue.get(0);

        if (!programBegun) {
            firstCommand.start();
            programBegun = true;
        }

        firstCommand.loop();
        if (firstCommand.isFinished()) {
            telemetry.clearAll();
            commandQueue.remove(0);
            if (!commandQueue.isEmpty()) {
                commandQueue.get(0).start();
            }
        }
    }

}
