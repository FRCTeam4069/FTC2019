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

@Autonomous
public class MasterAutoOne extends OpMode {

    Drivetrain drivetrain;
    Scheduler scheduler;
    Passthrough passthrough;
    ParallelCommand parallelCommandOne;
    DropOff dropOff;

    private WebcamName webcam;
    private NormalStoneDetector detector;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        detector = new NormalStoneDetector(telemetry);
        passthrough = new Passthrough(hardwareMap, telemetry);
        dropOff = new DropOff(hardwareMap, telemetry);
        scheduler = new Scheduler(drivetrain, detector, null, telemetry, passthrough, dropOff);

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        detector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
        detector.enable();

        GoSideways goSidewaysOne = new GoSideways (0.75, 0, detector, null, null);
        GoForwards goForwardsOne = new GoForwards (-0.8, 1000);
        PassthroughOn passthroughOn = new PassthroughOn();
        parallelCommandOne = new ParallelCommand(goForwardsOne, passthroughOn, true);
        GoForwards goBackwardsOne = new GoForwards(0.8, 1000);
        GoSideways goSidewaysTwo = new GoSideways(0.75, 4000, null, null, null);
        passthrough.update(false, false, 0);
        scheduler.add(goSidewaysOne);
        scheduler.add(parallelCommandOne);
        scheduler.add(goBackwardsOne);
        scheduler.add(goSidewaysTwo);
    }
    @Override
    public void loop() {
        scheduler.loop();
        passthrough.update(false, false, 100);
    }
}
