package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MecanumTest")
public class MecanumTest extends OpMode {


    @Override
    public void init() {
    }

    @Override
    public void loop() {
        double LMotorSpeed = 0;
        int Direction = 0;
        int speed = 0;

        LMotorSpeed = Math.sin(Math.PI / 2 + Math.PI / 4);


    }
}
