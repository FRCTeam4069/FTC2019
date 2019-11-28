package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Passthrough {

    private CRServo PTLeft;
    private CRServo PTRight;
    Telemetry telemetry;

    private Passthrough(HardwareMap hardwareMap, Telemetry telemetry) {
        PTLeft = hardwareMap.get(CRServo.class, "PTLeft");
        PTRight = hardwareMap.get(CRServo.class, "PTRight");
        this.telemetry = telemetry;
    }


    public void Passthrough(boolean forward, boolean reverse) {
        if (forward == true) {
            PTLeft.setPower(1);
            PTRight.setPower(1);
        }
        else if (reverse == true) {
            PTLeft.setPower(-1);
            PTRight.setPower(-1);
        }
    }
}
