package org.firstinspires.ftc.teamcode.teleop;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;

@TeleOp(name = "MecanumVision")
public class MecanumVision extends OpMode {

    NormalStoneDetector detector;

    private WebcamName webcam;

    private Drivetrain drivetrain;

    @Override
    public void init() {
        detector = new NormalStoneDetector(telemetry);
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
       // drivetrain.update (0.5, 0, 0);
        if (detector.position > 300 && detector.position < 400000) {
            drivetrain.update(0, 0 , 0, gamepad1.x, gamepad1.b);
        }
        else {
            drivetrain.update(0.5, 0, 0, gamepad1.x, gamepad1.b);
        }
        telemetry.addData("Position", detector.position);

//
    }
}