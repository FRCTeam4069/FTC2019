package org.firstinspires.ftc.teamcode.commands;


public class DropdownOn extends Command {

    private double power;
    private double runningTimeMillis;
    private double endTime;

    public DropdownOn(double power, double runningTimeMillis) {
        this.power = power;
        this.runningTimeMillis = runningTimeMillis;
    }

    @Override
    public void start() {
        endTime = System.currentTimeMillis() + runningTimeMillis;
    }

    @Override
    public void loop() {
        dropOff.update(-power);
    }

    @Override
    boolean isFinished() {
        if(System.currentTimeMillis() > endTime){
            telemetry.addData("endTime", endTime);
            telemetry.addData("currentTime", System.currentTimeMillis());
            telemetry.update();
            dropOff.update(0);
            return true;
        }else{
            return false;
        }
    }
}
