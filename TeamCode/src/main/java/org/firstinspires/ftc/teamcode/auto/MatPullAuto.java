package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.DropdownOff;
import org.firstinspires.ftc.teamcode.commands.DropdownOn;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.GoSideways;
import org.firstinspires.ftc.teamcode.commands.ParallelCommand;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.detectors.NormalStoneDetector;
import org.firstinspires.ftc.teamcode.detectors.SkyStoneDetector;

@Autonomous
public class MatPullAuto extends OpMode {

    Drivetrain drivetrain;
    NormalStoneDetector normalStoneDetector;
    SkyStoneDetector skyStoneDetector;
    Passthrough passthrough;
    Scheduler scheduler;
    DropOff dropOff;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap, telemetry);
        normalStoneDetector = new NormalStoneDetector(telemetry);
        skyStoneDetector = new SkyStoneDetector(telemetry);
        passthrough = new Passthrough (hardwareMap, telemetry);
        dropOff = new DropOff(hardwareMap, telemetry);
        scheduler = new Scheduler(drivetrain, normalStoneDetector, skyStoneDetector, telemetry, passthrough, dropOff);
        GoSideways goSideways = new GoSideways(0.75, 500, null, null);
        DropdownOn dropdownOn1 = new DropdownOn(0.1, 4000);
        GoForwards goForwards = new GoForwards(0.75, 1500);
        ParallelCommand parallelCommand1 = new ParallelCommand(dropdownOn1, goForwards, false);
        DropdownOn dropdownOn = new DropdownOn(0.1, 2000);
        GoForwards goBackwards = new GoForwards(-0.75, -1550);
        ParallelCommand parallelCommand = new ParallelCommand(dropdownOn, goBackwards, false);
        GoSideways goSideways2 = new GoSideways(0.75, -2200, null, null);

        scheduler.add(goSideways);
        scheduler.add(parallelCommand1);
        //scheduler.add(dropdown);
        //scheduler.add(goForwards);
        //scheduler.add(goBackwards);
        scheduler.add(parallelCommand);
        scheduler.add(goSideways2);
    }

    @Override
    public void loop() {
        scheduler.loop();
    }
}
