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
    private NormalStoneDetector detector;
    private WebcamName webcam;
    private Scheduler scheduler;
    private GoSideways goSideways = new GoSideways(0.5);
    private GoForwards goForwards = new GoForwards(0.75, 5000);
    @Override
    public void init () {
        drivetrain = Drivetrain.getInstance(hardwareMap, telemetry);
        Passthrough passthrough = new Passthrough(hardwareMap, telemetry);

        detector = new NormalStoneDetector(telemetry);
        telemetry.addData("DogeCV Camera Test", "Init");

        webcam = hardwareMap.get(WebcamName.class, "webcam");
        detector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
                DogeCV.CameraMode.WEBCAM, false, webcam);
        detector.enable();
        scheduler = new Scheduler(drivetrain, detector, telemetry, passthrough);
        scheduler.add(goSideways);
        scheduler.add(goForwards);
    }
    @Override
    public void loop() {
        scheduler.loop();
    }
}
