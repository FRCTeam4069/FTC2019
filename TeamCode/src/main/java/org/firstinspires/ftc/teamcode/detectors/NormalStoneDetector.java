package org.firstinspires.ftc.teamcode.detectors;


import com.disnodeteam.dogecv.detectors.DogeCVDetector;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pipelines.NormalStonePipeline;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class NormalStoneDetector extends DogeCVDetector {
    NormalStonePipeline pipeline = new NormalStonePipeline();
    Telemetry telemtry;

    public NormalStoneDetector(Telemetry telemetry) {
        this.telemtry = telemetry;
    }


    @Override
    public Mat process(Mat input) {
        pipeline.process(input.clone());
        telemtry.addData("Number of contours", pipeline.filterContoursOutput().size());


        if (pipeline.filterContoursOutput().size() < 1) {
            telemtry.addData("Number of items in list", 0);
            return input;
        } else {
            Mat processed = input.clone();
            Rect boundingRect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
            Imgproc.rectangle(processed, boundingRect.tl(), boundingRect.br(), new Scalar(255, 0, 0));
            return processed;
        }
    }

    @Override
    public void useDefaults() {

    }
}

