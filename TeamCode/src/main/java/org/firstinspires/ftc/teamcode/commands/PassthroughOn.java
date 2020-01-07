package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOn extends Command {

    public double time;
    public double time2;

    public PassthroughOn() {

    }
        void start(){
        time = System.currentTimeMillis();
        }
        void loop() {
            passthrough.update(true, false, 0);
            telemetry.addData("running passthrough", true);
            time2 = System.currentTimeMillis();
        }
        boolean isFinished() {
            return time + 1000 < System.currentTimeMillis();
        }
    }