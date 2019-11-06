package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

@TeleOp(name = "MecanumVision")
public class MecanumVision extends OpMode {

    SkyStoneDetector detector;

    private WebcamName webcam;

    private Drivetrain drivetrain;

    @Override
    public void init() {
        detector = new SkyStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        detector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
                detector.enable();

        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
       // drivetrain.drive (0.5, 0, 0);
        if (detector.position > 300 && detector.position < 400000) {
            drivetrain.drive(0, 0 , 0);
        }
        else {
            drivetrain.drive(0.5, 0, 0);
        }
        telemetry.addData("Position", detector.position);

        telemetry.addData ("left front motor output: ", drivetrain.leftFrontOutput);
        telemetry.addData ("left back motor output: ", drivetrain.leftBackOutput);
        telemetry.addData ("right front motor output: ", drivetrain.rightFrontOutput);
        telemetry.addData ("right back motor output: ", drivetrain.rightBackOutput);
    }
}