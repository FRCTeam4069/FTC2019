package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOff extends Command {

    private boolean finish;

    public PassthroughOff(boolean finish) {
        this.finish = finish;
    }

    void start(){
        passthrough.update(false, false, 1);
    }
    void loop() {}
    boolean isFinished() {
        return finish;
    }
}