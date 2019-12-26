package org.firstinspires.ftc.teamcode.detectors;


import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pipelines.SkyStonePipeline;
import org.firstinspires.ftc.teamcode.pipelines.StonePipeline;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;

public class SkyStoneDetector extends DogeCVDetector {
    SkyStonePipeline pipeline = new SkyStonePipeline();
    Telemetry telemetry;
    public double position = 0;

    public SkyStoneDetector(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat process(Mat input) {
        pipeline.process(input.clone());
        telemetry.addData("Number of contours", pipeline.filterContoursOutput().size());

        ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();
        Mat processed = pipeline.hsvThresholdOutput().clone();

        int[] areas = new int[pipeline.filterContoursOutput().size()];
        double largestContour = 0;

        //assumes biggest contour detected will be produced by block
        for (int i = 0; i <= areas.length; i++) {
            areas[i] = (contours.get(i).width() * contours.get(i).height());
            if (areas[i] > largestContour) {
                largestContour = areas[i];
                position = (Imgproc.boundingRect(contours.get(i)).tl().y + Imgproc.boundingRect(contours.get(i)).br().y) / 2;
            }
        }
        return processed;
    }
    @Override
    public void useDefaults () {
    }
    public List<MatOfPoint> getContours = pipeline.filterContoursOutput();
}