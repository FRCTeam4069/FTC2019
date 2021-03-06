package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GroundDetector {

    private ColorSensor colourSensor;
    public boolean isRed;
    public boolean isBlue;

    public GroundDetector(HardwareMap hardwareMap, Telemetry telemetry, ColorSensor colourSensor) {
        this.colourSensor = colourSensor;
        colourSensor = hardwareMap.get(ColorSensor.class, "groundColour");
    }
    public void locateLine () {
        if (colourSensor.red() > 128) {
            isRed = true;
        }
        if (colourSensor.blue() > 128) {
            isBlue = true;
        }
    }
}
