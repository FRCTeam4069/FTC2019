package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Passthrough;

public class ClampControl extends Command {

    private Passthrough passthrough;
    private double clampPosition;
    private double startingTime;

    public ClampControl(double clampPosition) {
        this.clampPosition = clampPosition;
    }

    @Override
    public void start() {
        passthrough = new Passthrough(hardwareMap, telemetry);
        startingTime = System.currentTimeMillis();
    }
    @Override
    public void loop() {
        passthrough.update(false, false, clampPosition);
    }
    @Override
    boolean isFinished() {
        return startingTime < startingTime + 1000;
    }
}
