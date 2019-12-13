package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Passthrough;

public class Elevator {


    private CRServo Clamp;
    private CRServo Wrench;
    Telemetry telemetry;

    public Elevator(HardwareMap hardwareMap, Telemetry telemetry) {
        Clamp = hardwareMap.get(CRServo.class, "Clamp");
        Wrench = hardwareMap.get(CRServo.class, "Wrench");
        this.telemetry = telemetry;
    }

    public void update(boolean forward, boolean reverse) {

        if (forward) {
            Wrench.setPower(1);
        } else if (reverse) {
            Wrench.setPower(-1);
        } else {
            Wrench.setPower(0);
        }
    }
}






