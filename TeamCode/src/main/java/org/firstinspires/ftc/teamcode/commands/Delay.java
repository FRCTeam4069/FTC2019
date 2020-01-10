package org.firstinspires.ftc.teamcode.commands;


public class Delay extends Command {

    private long delayMillis;
    private long endTime;

    public Delay(int delayMillis) {
        this.delayMillis = delayMillis;
        endTime = 1000000000;
    }

    @Override
    void start() {
        endTime = System.currentTimeMillis() + delayMillis;
    }

    @Override
    void loop() {

    }

    @Override
    boolean isFinished() {
        telemetry.addData("system current time", System.currentTimeMillis());
        telemetry.addData("end time", endTime);
        telemetry.update();
        return System.currentTimeMillis() > endTime;
    }
}
