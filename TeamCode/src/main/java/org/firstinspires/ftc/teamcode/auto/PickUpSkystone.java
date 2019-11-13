package org.firstinspires.ftc.teamcode.auto;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

@Autonomous(name = "PickUpSkystone")
public class PickUpSkystone extends OpMode {

    SkyStoneDetector detector;

    private WebcamName webcam;
    private Drivetrain drivetrain;
    private Telemetry telemetry;
    private Scheduler scheduler;


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

        scheduler = new Scheduler(drivetrain, detector, telemetry);
    }

    @Override
    public void loop() {
        // drivetrain.drive (0.5, 0, 0);

    }
}