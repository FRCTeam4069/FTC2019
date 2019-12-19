package org.firstinspires.ftc.teamcode.auto;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.GoSideways;
import org.firstinspires.ftc.teamcode.commands.ParallelCommand;
import org.firstinspires.ftc.teamcode.commands.PassthroughOff;
import org.firstinspires.ftc.teamcode.commands.PassthroughOn;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;

@Autonomous(name = "PickUpSkystone")
public class PickUpSkystone extends OpMode {

    NormalStoneDetector detector;

    private WebcamName webcam;
    private Scheduler scheduler;


    @Override
    public void init() {
        detector = new NormalStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");
        GoSideways goSideways = new GoSideways(0.5);
        GoForwards goForward = new GoForwards(-0.5, 2000);
        PassthroughOn passthroughOn = new PassthroughOn();
        PassthroughOff passthroughOff = new PassthroughOff();
        ParallelCommand parallelCommand = new ParallelCommand(passthroughOn, goForward);

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        detector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
        detector.enable();

        Drivetrain drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
        Passthrough passthrough = new Passthrough(hardwareMap, telemetry);

        scheduler = new Scheduler(drivetrain, detector, telemetry, passthrough);
        scheduler.add(goSideways);
        scheduler.add(parallelCommand);
    }

    @Override
    public void loop() {
        scheduler.loop();

    }
}