package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOn extends Command {

    public double time;
    public double time2;
    private boolean forward;
    private boolean reverse;

    public PassthroughOn(boolean forward, boolean reverse) {
        this.forward = forward;
        this.reverse = reverse;
    }
        void start(){
        time = System.currentTimeMillis();
        }
        void loop() {
            passthrough.update(forward, reverse, 1);
            telemetry.addData("running passthrough", true);
            time2 = System.currentTimeMillis();
        }
        boolean isFinished() {
            return time + 1000 < System.currentTimeMillis();
        }
    }