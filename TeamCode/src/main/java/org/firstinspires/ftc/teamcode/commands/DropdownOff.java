package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.DropOff;

public class DropdownOff extends Command {

    private double time;

    public DropdownOff(){}

    @Override
    public void start() {
        time = System.currentTimeMillis();
    }
    @Override
    public void loop() {
        dropOff.update(1);
    }
    @Override
    boolean isFinished() {
        return System.currentTimeMillis() < time + 1000;
    }
}
