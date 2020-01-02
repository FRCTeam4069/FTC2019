package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public class ParallelCommand extends Command {

    private Command command0;
    private Command command1;
    public boolean command0IsFinished = false;
    public boolean command1IsFinished = false;
    public boolean singleCommandFinish;

    public ParallelCommand(Command command0, Command command1, Boolean singleCommandFinish) {
        this.command0 = command0;
        this.command1 = command1;
        this.singleCommandFinish = singleCommandFinish;
    }

    @Override
    void start() {
        command0.start();
        command1.start();
    }

    @Override
    void loop() {
        if (!command0IsFinished) {
            command0.loop();
        }

        if (!command1IsFinished) {
            command1.loop();
        }
    }

    @Override
    boolean isFinished() {
        if (command0.isFinished()) {
            command0IsFinished = true;
        }

        if (command1.isFinished()) {
            command1IsFinished = true;
        }

        if (singleCommandFinish) {
            return command0IsFinished;
        }

        return command0IsFinished && command1IsFinished;
    }

    @Override
    public void setSubsystems(Drivetrain drivetrain, NormalStoneDetector normalStoneDetector, SkyStoneDetector skyStoneDetector, Telemetry telemetry, Passthrough passthrough) {
        super.setSubsystems(drivetrain, normalStoneDetector, skyStoneDetector, telemetry, passthrough);
        command0.setSubsystems(drivetrain, normalStoneDetector, skyStoneDetector, telemetry, passthrough);
        command1.setSubsystems(drivetrain, normalStoneDetector, skyStoneDetector, telemetry, passthrough);
    }
}
