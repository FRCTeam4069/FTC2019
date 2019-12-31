package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.GoSideways;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;

@Autonomous
public class BasicAuto extends OpMode {

    private Drivetrain drivetrain;
    private NormalStoneDetector normalStoneDetector;
    private WebcamName webcam;
    private Scheduler scheduler;
    private GoSideways goSideways = new GoSideways(0.5, 0, normalStoneDetector, null);
    private GoForwards goForwards = new GoForwards(0.75, 5000);
    @Override
    public void init () {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
        Passthrough passthrough = new Passthrough(hardwareMap, telemetry);

        normalStoneDetector = new NormalStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        normalStoneDetector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        normalStoneDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
        normalStoneDetector.enable();
        scheduler = new Scheduler(drivetrain, normalStoneDetector, null, telemetry, passthrough);
        scheduler.add(goSideways);
        scheduler.add(goForwards);
    }
    @Override
    public void loop() {
        scheduler.loop();
    }
}
