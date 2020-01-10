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
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

@Autonomous(name = "PickUpStone")
public class SkyStonePickUp extends OpMode {

    SkyStoneDetector skyStoneDetector;
    DropOff dropOff;

    private WebcamName webcam;
    private Scheduler scheduler;
    ParallelCommand parallelCommand;


    @Override
    public void init() {
        skyStoneDetector = new SkyStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");
        GoSideways goSideways = new GoSideways(0.5, 0, null, skyStoneDetector);
        GoForwards goForward = new GoForwards(-0.5, 2000);
        PassthroughOn passthroughOn = new PassthroughOn();
        PassthroughOff passthroughOff = new PassthroughOff(false);
        parallelCommand = new ParallelCommand(passthroughOn, goForward, parallelCommand.command1IsFinished);

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        skyStoneDetector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        skyStoneDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
        skyStoneDetector.enable();

        Drivetrain drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
        Passthrough passthrough = new Passthrough(hardwareMap, telemetry);
        dropOff = new DropOff(hardwareMap, telemetry);

        scheduler = new Scheduler(drivetrain, null, skyStoneDetector, telemetry, passthrough, dropOff);
        scheduler.add(goSideways);
        scheduler.add(parallelCommand);
    }

    @Override
    public void loop() {
        scheduler.loop();
    }
}
