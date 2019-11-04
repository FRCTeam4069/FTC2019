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
        if (detector.position > 300) {
            drivetrain.drive(0, 0 , 0);
        }
        else {
            drivetrain.drive(0.75, 0, 0);
        }
        telemetry.addData("Position", detector.position);
        //drivetrain.drive (gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
    }
}