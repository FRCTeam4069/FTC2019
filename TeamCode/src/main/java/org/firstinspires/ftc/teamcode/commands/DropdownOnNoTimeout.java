package org.firstinspires.ftc.teamcode.commands;

public class DropdownOnNoTimeout extends Command {
    private double power;

    public DropdownOnNoTimeout(double power) {
        this.power = power;
    }

    @Override
    void start() {
    }

    @Override
    public void loop() {
        dropOff.update(-power);
    }

    @Override
    boolean isFinished() {
        return true;
    }
}
