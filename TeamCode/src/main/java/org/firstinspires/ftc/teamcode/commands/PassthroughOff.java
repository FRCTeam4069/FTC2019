package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOff extends Command {


    public PassthroughOff() {

    }

    void start(){
        passthrough.update(false, false);
    }
    void loop(){
    }
    boolean isFinished() {
        return false;
    }
}