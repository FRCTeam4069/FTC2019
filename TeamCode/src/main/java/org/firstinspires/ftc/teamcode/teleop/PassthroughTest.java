package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Passthrough;

@TeleOp(name = "PassthroughTest")
public class PassthroughTest extends OpMode {

    private Passthrough passthrough;

    @Override
    public void init() {
       passthrough = new Passthrough(hardwareMap, telemetry);
    }

    public void loop() {


        passthrough.update(gamepad1.y, gamepad1.a, 0);
    }
}
