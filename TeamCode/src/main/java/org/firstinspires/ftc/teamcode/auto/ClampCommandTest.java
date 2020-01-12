package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.ClampControl;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;

@Autonomous
public class ClampCommandTest extends OpMode {

    private Passthrough passthrough;
    private Drivetrain drivetrain;
    private NormalStoneDetector normalStoneDetector;
    private DropOff dropOff;
    private Scheduler scheduler;
    @Override
    public void init() {
        passthrough = new Passthrough(hardwareMap, telemetry);
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        normalStoneDetector = new NormalStoneDetector(telemetry);
        dropOff = new DropOff(hardwareMap,telemetry);
        scheduler = new Scheduler(drivetrain, normalStoneDetector, null, telemetry, passthrough, dropOff);

        ClampControl clampControl = new ClampControl(0.5);
        ClampControl clampControl1 = new ClampControl(1);
        ClampControl clampControl2 = new ClampControl(0);
        scheduler.add(clampControl);
        scheduler.add(clampControl1);
        scheduler.add(clampControl2);
    }
    @Override
    public void loop() {
        scheduler.loop();
    }
}
