package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.util.List;

@Autonomous(name = "SkyStoneApproachAuto")
public class SkyStoneApproachAuto extends OpMode {

    NormalStoneDetector detector;
    private WebcamName webcam;

    @Override
    public void init () {

        detector = new NormalStoneDetector(telemetry);
        telemetry.addData ("normalStoneDetector", "intialized");
        webcam = hardwareMap.get(WebcamName.class, "webcam");
        detector.VUFORIA_KEY = Constants.VUFOIRA_KEY;
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance(),
        DogeCV.CameraMode.WEBCAM, false, webcam);

        detector.enable();
    }

    @Override
    public void loop () {
        List<MatOfPoint> contours = detector.getCountours();

        MatOfPoint contour = contours.get(0);
        Rect boundingRect = Imgproc.boundingRect(contour);
        Point point1 = boundingRect.tl();
        Point point2 = boundingRect.br();

        double point1x = point1.x;
        double point1y = point1.y;
        double point2x = point2.x;
        double point2y = point2.y;

        double pointx = (point1x + point2x)/2;
        double pointy = (point1y + point2y)/2;


        }
    }

