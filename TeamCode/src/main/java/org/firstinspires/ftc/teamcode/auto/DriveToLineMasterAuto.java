package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.GroundDetector;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.DriveForTime;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.ParallelCommand;
import org.firstinspires.ftc.teamcode.commands.PassthroughOff;
import org.firstinspires.ftc.teamcode.commands.PassthroughOn;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.commands.WaitCommand;

@Autonomous
public class DriveToLineMasterAuto extends OpMode {


    Drivetrain drivetrain;
    Passthrough passthrough;
    DropOff dropOff;
    Scheduler scheduler;
    GroundDetector groundDetector;


    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        passthrough = new Passthrough(hardwareMap, telemetry);
        scheduler = new Scheduler(drivetrain, null, null, telemetry, passthrough, dropOff);
        groundDetector = new GroundDetector(hardwareMap, telemetry);

        GoForwards goForwards = new GoForwards(0.75,  1000);

        scheduler.add(goForwards);
        scheduler.add(new GoForwards(0, 0));

    }

    @Override
    public void loop() {
        scheduler.loop();
    }
}