package org.firstinspires.ftc.teamcode.auto;


import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.GoSideways;
import org.firstinspires.ftc.teamcode.commands.ParallelCommand;
import org.firstinspires.ftc.teamcode.commands.PassthroughOff;
import org.firstinspires.ftc.teamcode.commands.PassthroughOn;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;

@Autonomous(name = "PickUpStone")
public class PickUpStone extends OpMode {

    NormalStoneDetector normalStoneDetector;
    Drivetrain drivetrain;
    DropOff dropOff;

    private WebcamName webcam;
    private Scheduler scheduler;


    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        normalStoneDetector = new NormalStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");
        GoSideways goSideways = new GoSideways(0.75, 0, normalStoneDetector, null);
        GoForwards goForward = new GoForwards(-0.5, 1000);
        PassthroughOn passthroughOn = new PassthroughOn();
        PassthroughOff passthroughOff = new PassthroughOff(false);
        ParallelCommand parallelCommand = new ParallelCommand(passthroughOn, goForward, false);

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        normalStoneDetector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        normalStoneDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
        normalStoneDetector.enable();

        Drivetrain drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
        Passthrough passthrough = new Passthrough(hardwareMap, telemetry);
        dropOff = new DropOff(hardwareMap, telemetry);

        scheduler = new Scheduler(drivetrain, normalStoneDetector, null, telemetry, passthrough, dropOff);
        scheduler.add(goSideways);
        scheduler.add(parallelCommand);
    }

    @Override
    public void loop() {
        scheduler.loop();
    }
}