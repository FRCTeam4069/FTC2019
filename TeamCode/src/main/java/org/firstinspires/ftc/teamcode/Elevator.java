package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileBuilder;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.ValueEncoder;
import org.firstinspires.ftc.teamcode.Passthrough;

public class Elevator {


//    private CRServo Clamp;
//    private CRServo Wrench;
    private CRServo Elevator;
    Telemetry telemetry;
    MotionProfile currentProfile = null;

    public Elevator(HardwareMap hardwareMap, Telemetry telemetry) {
//        Clamp = hardwareMap.get(CRServo.class, "Clamp");
//        Wrench = hardwareMap.get(CRServo.class, "Wrench");
        Elevator = hardwareMap.get(CRServo.class, "Elevator");

        this.telemetry = telemetry;
    }

    public void setPosition(double position) {


    }

    public void update(boolean forward, boolean reverse, double power) {
        if (forward) {
//            Wrench.setPower(1);
        } else if (reverse) {
//            Wrench.setPower(-1);
        } else {
//
//            Wrench.setPower(0);
        }

        Elevator.setPower(power);

    }
}






