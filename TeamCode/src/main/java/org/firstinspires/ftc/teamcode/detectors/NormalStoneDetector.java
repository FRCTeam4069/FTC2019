package org.firstinspires.ftc.teamcode.detectors;


import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pipelines.StonePipeline;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;

public class NormalStoneDetector extends DogeCVDetector {
    StonePipeline pipeline = new StonePipeline();
    Telemetry telemetry;
    public double position = 0;

    public NormalStoneDetector(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat process(Mat input) {
        pipeline.process(input.clone());
        telemetry.addData("Number of contours", pipeline.findContoursOutput().size());

        ArrayList<MatOfPoint> contours = pipeline.findContoursOutput();
        Mat processed = pipeline.blurOutput().clone();

        double leftMin = 1000000;
        double rightMax = 0;

        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint contour = contours.get(i);
            Rect boundingRect = Imgproc.boundingRect(contour);
            double left = boundingRect.tl().y;
            double right  = boundingRect.br().y;
            if (left < leftMin) {
             leftMin = left;
            }
            if (right > rightMax) {
                rightMax = right;
            }



            Imgproc.rectangle(processed, boundingRect.tl(), boundingRect.br(), new Scalar(255, 0, 0));
        }
        position = (leftMin + rightMax) / 2;

        telemetry.addData ("position", position);
        telemetry.addData ("left min", leftMin);
        telemetry.addData ("right max", rightMax);
        telemetry.addData ("num contours", contours.size());

        //return processed;
        return pipeline.hsvThresholdOutput();
    }

    @Override
    public void useDefaults() {
    }

    public List<MatOfPoint> getCountours() {
        return pipeline.findContoursOutput();
    }
}