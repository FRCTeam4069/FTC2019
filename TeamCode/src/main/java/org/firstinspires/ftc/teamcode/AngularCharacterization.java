package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous
public class AngularCharacterization extends OpMode {
    Drivetrain drivetrain;

    double turn = 1;

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

        double w = drivetrain.navx.getAngularVelocity(AngleUnit.RADIANS).zRotationRate;
        if(w < 0) {
            w -= 2 * Math.PI;
        }
        cumulativeAngle += w;
        numSamples += 1;

        telemetry.addData("Average Rotation Rate", cumulativeAngle / numSamples);

    }

    @Override
    public void stop() {
        telemetry.addData("Average Rotation Rate", cumulativeAngle / numSamples);
    }


}
