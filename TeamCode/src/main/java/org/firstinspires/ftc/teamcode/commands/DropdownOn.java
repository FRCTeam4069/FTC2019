package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.DropOff;

public class DropdownOn extends Command {

    DropOff dropOff;
    private double power;
    private double runningTimeMillis;
    private double time;
    private double time2;

    public DropdownOn(double power, double runningTimeMillis) {
        this.power = power;
        this.runningTimeMillis = runningTimeMillis;
    }

    @Override
    public void start() {
        dropOff = new DropOff(hardwareMap, telemetry);
        time = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        dropOff.update(-power);
        time2 = System.currentTimeMillis();
    }

    @Override
    boolean isFinished() {
        return time2 > time + runningTimeMillis;
    }
}
