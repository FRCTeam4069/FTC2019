package org.firstinspires.ftc.teamcode;

import android.media.audiofx.DynamicsProcessing;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.ValueEncoder;
import org.firstinspires.ftc.teamcode.Passthrough;

public class DropOff {

    private CRServo dropOffMotor;
    private Telemetry telemetry;

    public DropOff(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        dropOffMotor = hardwareMap.get(CRServo.class, "Elevator");
    }

    public void update(double power) {
        dropOffMotor.setPower(power);
        }
    }
