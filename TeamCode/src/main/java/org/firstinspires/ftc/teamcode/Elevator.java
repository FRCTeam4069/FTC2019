package org.firstinspires.ftc.teamcode;

import android.media.audiofx.DynamicsProcessing;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.ValueEncoder;
import org.firstinspires.ftc.teamcode.Passthrough;

public class Elevator {


//    private CRServo Clamp;
//    private CRServo Wrench;
    private CRServo servo;
    private ColorSensor colorSensor;
    private DigitalChannel limitSwitch;
    Telemetry telemetry;

    public Elevator(HardwareMap hardwareMap, Telemetry telemetry) {
//        Clamp = hardwareMap.get(CRServo.class, "Clamp");
//        Wrench = hardwareMap.get(CRServo.class, "Wrench");
        servo = hardwareMap.get(CRServo.class, "Elevator");
        limitSwitch = hardwareMap.get(DigitalChannel.class, "elevatorLimit");


        this.telemetry = telemetry;

        colorSensor = hardwareMap.colorSensor.get("greenColour");

    }

    public enum ElevatorMode {
        LIMIT_SWITCH,
        GREEN_SENSOR,
        MANUAL_DOWN,
        MANUAL_UP,
        STOP

    }

    private ElevatorMode mode = ElevatorMode.STOP;

    public void setMode(ElevatorMode mode) {
        this.mode = mode;
    }

    public void update() {

        double power;

        if (mode == ElevatorMode.LIMIT_SWITCH) {
            power = -0.5;
            if (limitSwitch.getState()) {
                mode = ElevatorMode.STOP;
            }
        } else if (mode == ElevatorMode.GREEN_SENSOR) {
            power = 0.5;
            if (colorSensor.green() > 128) {
                mode = ElevatorMode.STOP;
            }
        } else if (mode == ElevatorMode.MANUAL_UP) {
            power = 0.5;
        } else if (mode == ElevatorMode.MANUAL_DOWN) {
            power = -0.5;
        } else {
            power = 0;
        }

        servo.setPower(power);
    }
}







