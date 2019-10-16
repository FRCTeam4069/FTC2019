package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous
public class PIDTest extends OpMode {
    private DcMotor leftFront;

    @Override
    public void init() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setPower(1);
    }

    @Override
    public void start() {
        leftFront.setTargetPosition(10000);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        telemetry.addData("left front motor", leftFront.getCurrentPosition());
    }
}
