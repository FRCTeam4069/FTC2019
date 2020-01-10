package org.firstinspires.ftc.teamcode.commands;


public class DropdownOn extends Command {

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
        time = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        dropOff.update(-power);
        time2 = System.currentTimeMillis();
    }

    @Override
    boolean isFinished() {
        if(time2 > time + runningTimeMillis){
            dropOff.update(0);
            return true;
        }else{
            return false;
        }
    }
}
