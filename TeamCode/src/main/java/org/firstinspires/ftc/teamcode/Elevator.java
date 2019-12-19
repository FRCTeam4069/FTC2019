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
        MANUAL_CONTROL,
        STOP
    }

    private ElevatorMode mode = ElevatorMode.STOP;
    private double manualDemand = 0;

    public void setMode(ElevatorMode mode) {
        this.setMode(mode, 0);
    }

    public void setMode(ElevatorMode mode, double demand) {
        this.mode = mode;
        this.manualDemand = demand;
    }

    public void update() {


        double power = 0.0;

        telemetry.addData("Green Sensor",colorSensor.green());
        telemetry.addData("Limit Switch State", limitSwitch.getState());
        switch (mode) {
            case LIMIT_SWITCH:
                power = -0.5;
                if (!limitSwitch.getState()) {
                    mode = ElevatorMode.STOP;
                }
                break;
            case GREEN_SENSOR:
                power = 0.5;
                if (colorSensor.green() > 50) {
                    mode = ElevatorMode.LIMIT_SWITCH;
                }
                break;
            case MANUAL_CONTROL:
                power = manualDemand;
                break;
            case STOP:
                power = 0;
                break;
        }

        servo.setPower(power);
    }
}







