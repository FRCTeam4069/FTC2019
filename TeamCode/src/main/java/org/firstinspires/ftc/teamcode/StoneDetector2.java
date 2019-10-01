package org.firstinspires.ftc.teamcode;


import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class StoneDetector2 extends DogeCVDetector {
    SkyStonePipeline pipeline = new SkyStonePipeline();
    Telemetry telemtry;

    public StoneDetector2(Telemetry telemetry) {
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

        for (int i = 0; i < contours.size(); i++ ) {
            MatOfPoint contour = contours.get (i);
            Rect boundingRect = Imgproc.boundingRect(contour);
            if (boundingRect.height < 200 && counter < 2) {
                rectangle += boundingRect.x;
               counter ++;
            }

            Imgproc.rectangle(processed, boundingRect.tl(), boundingRect.br(), new Scalar(255, 0, 0));
        }
        rectangle/=2;
        return processed;
    }
    @Override
    public void useDefaults() {
    }
}