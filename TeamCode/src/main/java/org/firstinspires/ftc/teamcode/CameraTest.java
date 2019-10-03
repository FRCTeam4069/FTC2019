package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

@TeleOp(name = "Camera Test")
public class CameraTest extends OpMode {

    SkyStoneDetector detector;
    private WebcamName webcam;

    @Override
    public void init() {
        detector = new SkyStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        detector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);

        detector.enable();

    }

    @Override
    public void loop() {

    }
}
