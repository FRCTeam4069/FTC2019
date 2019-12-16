package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Elevator;

@TeleOp(name = "ElevatorTest")
public class ElevatorTest extends OpMode {

    private Elevator elevator;

    @Override
    public void init() {
        elevator = new Elevator(hardwareMap, telemetry);
        elevator.setMode(Elevator.ElevatorMode.GREEN_SENSOR);
    }

    public void loop() {
        elevator.update();
    }
}
