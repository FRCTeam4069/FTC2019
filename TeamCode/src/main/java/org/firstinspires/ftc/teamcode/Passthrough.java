package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Passthrough {

    private CRServo PTLeft;
    private CRServo PTRight;
    private Servo clamp;
    Telemetry telemetry;


    public Passthrough(HardwareMap hardwareMap, Telemetry telemetry) {
        PTLeft = hardwareMap.get(CRServo.class, "PTLeft");
        PTRight = hardwareMap.get(CRServo.class, "PTRight");
        clamp = hardwareMap.get(Servo.class, "clamp");
        this.telemetry = telemetry;
    }


    public void update(boolean forward, boolean reverse, double clampPosition) {
        if (forward) {
            PTLeft.setPower(-1);
            PTRight.setPower(1);
        }
        else if (reverse) {
            PTLeft.setPower(1);
            PTRight.setPower(-1);
        }
        else {
            PTLeft.setPower(0);
            PTRight.setPower(0);
        }
        clamp.setPosition(clampPosition);
    }
}
