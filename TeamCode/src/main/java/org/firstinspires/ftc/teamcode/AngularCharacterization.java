package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous
public class AngularCharacterization extends OpMode {
    Drivetrain drivetrain;

    double turn = 0.75;

    double cumulativeAngle;
    double numSamples;

    @Override
    public void init() {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        drivetrain.drive(0, 0, turn);

        cumulativeAngle += drivetrain.imu.getAngularVelocity().xRotationRate;
        numSamples += 1;

        telemetry.addData("Average Rotation Rate", cumulativeAngle / numSamples);

    }

    @Override
    public void stop() {
        telemetry.addData("Average Rotation Rate", cumulativeAngle / numSamples);
    }


}
