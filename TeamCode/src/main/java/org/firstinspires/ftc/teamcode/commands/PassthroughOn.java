package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOn extends Command {

    public PassthroughOn() {

    }

        void start(){
        }
        void loop() {
            passthrough.update(true, false, 0);
            telemetry.addData("running passthrough", true);
        }
        boolean isFinished() {
            return false;
        }
    }