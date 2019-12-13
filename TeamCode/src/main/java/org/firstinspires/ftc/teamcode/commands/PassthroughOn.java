package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOn extends Command {

    public PassthroughOn() {

    }

        void start(){
        }
        void loop() {
            passthrough.update(true, false);
            telemetry.addData("running passthrough", true);
        }
        boolean isFinished() {
            return false;
        }
    }