package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOn extends Command {

    public PassthroughOn() {

    }

        void start(){
            passthrough.update(true, false);
        }
        void loop() {}
        boolean isFinished() {
            return true;
        }
    }