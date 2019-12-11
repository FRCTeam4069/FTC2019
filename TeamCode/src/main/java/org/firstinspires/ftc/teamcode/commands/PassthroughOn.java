package org.firstinspires.ftc.teamcode.commands;

public class PassthroughOn extends Command {

    public PassthroughOn() {

    }

        void start(){
            passthrough.update(false, false);
        }
        void loop(){
            passthrough.update(true, false);
        }
        boolean isFinished() {
            return true;
        }
    }