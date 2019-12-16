package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

public class ParallelCommand extends Command {

    private Command command0;
    private Command command1;
    private boolean command0IsFinished = false;
    private boolean command1IsFinished = false;

    public ParallelCommand(Command command0, Command command1) {
        this.command0 = command0;
        this.command1 = command1;
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

        return command0IsFinished && command1IsFinished;
    }

    @Override
    public void setSubsystems(Drivetrain drivetrain, SkyStoneDetector detector, Telemetry telemetry, Passthrough passthrough) {
        super.setSubsystems(drivetrain, detector, telemetry, passthrough);
        command0.setSubsystems(drivetrain, detector, telemetry, passthrough);
        command1.setSubsystems(drivetrain, detector, telemetry, passthrough);
    }
}
