package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

@Autonomous
public class BasicAuto extends OpMode {

    private Drivetrain drivetrain;
    private SkyStoneDetector detector;
    private WebcamName webcam;
    @Override
    public void init () {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);

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
        if (detector.position > 300 && detector.position < 400000) {
            drivetrain.drive(0, 0 , 0);

        }
        else {
            drivetrain.drive(0.5, -0.05, 0);
        }
    }
}
