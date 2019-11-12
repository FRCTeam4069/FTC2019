package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous
public class BasicAuto extends OpMode {

    private Drivetrain drivetrain;
    @Override
    public void init () {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
    }
    @Override
    public void loop() {

    }
}
