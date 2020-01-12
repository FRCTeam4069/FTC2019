package org.firstinspires.ftc.teamcode.commands;

public class WaitCommand extends Command {
    private double startTime;
    private double duration;

    public WaitCommand(double durationMs) {
        duration = durationMs;
    }

    @Override
    void start() {
        startTime = System.currentTimeMillis();
    }

    @Override
    void loop() {

    }

    @Override
    boolean isFinished() {
        return System.currentTimeMillis() >= startTime + duration;
    }
}
