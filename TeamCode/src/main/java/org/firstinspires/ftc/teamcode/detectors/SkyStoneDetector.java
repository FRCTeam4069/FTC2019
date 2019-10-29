package org.firstinspires.ftc.teamcode.detectors;


import android.graphics.Color;

import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pipelines.SkyStonePipeline;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
    import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArayList;
import java.util.ArrayList;
import java.util.List;

public class SkyStoneDetector extends DogeCVDetector {
    SkyStonePipeline pipeline = new SkyStonePipeline();
    Telemetry telemtry;

    public SkyStoneDetector(Telemetry telemetry) {
        this.telemtry = telemetry;
    }

    @Override
    public Mat process(Mat input) {
        pipeline.process(input.clone());
        telemtry.addData("Number of contours", pipeline.findContoursOutput().size());

        ArrayList<MatOfPoint> contours = pipeline.findContoursOutput();
        Mat processed = input.clone();

        int rectangle = 0;
        int counter = 0;

        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint contour = contours.get(i);
            Rect boundingRect = Imgproc.boundingRect(contour);
            double left = boundingRect.tl().x;
            double right  = boundingRect.br().x;


            Imgproc.rectangle(processed, boundingRect.tl(), boundingRect.br(), new Scalar(255, 0, 0));
        }
        rectangle /= 2;
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