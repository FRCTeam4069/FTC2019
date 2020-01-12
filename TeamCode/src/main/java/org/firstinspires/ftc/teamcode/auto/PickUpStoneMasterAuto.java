package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.GroundDetector;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.ClampControl;
import org.firstinspires.ftc.teamcode.commands.DriveForTime;
import org.firstinspires.ftc.teamcode.commands.DropdownOn;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.GoSideways;
import org.firstinspires.ftc.teamcode.commands.ParallelCommand;
import org.firstinspires.ftc.teamcode.commands.PassthroughOff;
import org.firstinspires.ftc.teamcode.commands.PassthroughOn;
import org.firstinspires.ftc.teamcode.commands.Scheduler;

@Autonomous
public class PickUpStoneMasterAuto extends OpMode {

    Drivetrain drivetrain;
    Passthrough passthrough;
    DropOff dropOff;
    Scheduler scheduler;
    GroundDetector groundDetector;



    public void init() {
        drivetrain = new Drivetrain (hardwareMap, telemetry);
        passthrough = new Passthrough (hardwareMap, telemetry);
        scheduler = new Scheduler(drivetrain, null, null, telemetry, passthrough, dropOff);
        groundDetector = new GroundDetector(hardwareMap, telemetry);

        ClampControl clampControl = new ClampControl(0.5);
        ClampControl clampControl1 = new ClampControl(0);
        ParallelCommand parallelCommand1 = new ParallelCommand(new DriveForTime(-0.75, 2500), new PassthroughOn(), false);
        PassthroughOff passthroughOff = new PassthroughOff(true);
        GoForwards goBackwards = new GoForwards(0.5, 2000);
        GoSideways goSideways1 = new GoSideways(-0.75, -2000, null, null, null);
        ClampControl clampUp = new ClampControl(0);
        ClampControl clampDown = new ClampControl(180);
        GoSideways goSideways2 = new GoSideways(0.5, 0, null, null, groundDetector);

        scheduler.add(clampControl);
        scheduler.add(clampControl1);
        scheduler.add(parallelCommand1);
        scheduler.add(passthroughOff);
        scheduler.add(goBackwards);
        scheduler.add(goSideways1);
        scheduler.add(clampUp);
        scheduler.add(clampDown);
        scheduler.add(goSideways2);
        drivetrain.ResetEncoders();
    }
    public void loop() {
        scheduler.loop();
    }
}
