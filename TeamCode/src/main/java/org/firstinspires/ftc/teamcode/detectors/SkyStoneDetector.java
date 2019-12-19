package org.firstinspires.ftc.teamcode.detectors;

import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pipelines.SkyStonePipeline;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class SkyStoneDetector extends DogeCVDetector {

    Telemetry telemetry;
    SkyStonePipeline skyStonePipeline = new SkyStonePipeline();
    public double position;

    public SkyStoneDetector(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat process(Mat input) {
        skyStonePipeline.process(input.clone());
        telemetry.addData("Number of contours", skyStonePipeline.findContoursOutput().size());
        telemetry.addData("Number of filtered contours", skyStonePipeline.filterContoursOutput());

        ArrayList<MatOfPoint> contours = skyStonePipeline.filterContoursOutput();
        Mat processed = skyStonePipeline.blurOutput().clone();

        double leftMin = 1000000;
        double rightMax = 0;

        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint contour = contours.get(i);
            Rect boundingRect = Imgproc.boundingRect(contour);
            double left = boundingRect.tl().y;
            double right = boundingRect.br().y;
            if (left < leftMin) {
                leftMin = left;
            }
            if (right > rightMax) {
                rightMax = right;

            }

        }
        position = (leftMin + rightMax) / 2;

        telemetry.addData ("position", position);
        telemetry.addData ("left min", leftMin);
        telemetry.addData ("right max", rightMax);
        return (processed);
    }

    @Override
    public void useDefaults() {

    }
}
