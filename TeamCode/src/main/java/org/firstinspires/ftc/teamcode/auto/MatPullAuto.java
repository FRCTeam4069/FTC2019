package org.firstinspires.ftc.teamcode.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Drivetrain;
import org.firstinspires.ftc.teamcode.DropOff;
import org.firstinspires.ftc.teamcode.Passthrough;
import org.firstinspires.ftc.teamcode.commands.DropdownOff;
import org.firstinspires.ftc.teamcode.commands.DropdownOn;
import org.firstinspires.ftc.teamcode.commands.DropdownOnNoTimeout;
import org.firstinspires.ftc.teamcode.commands.GoForwards;
import org.firstinspires.ftc.teamcode.commands.GoSideways;
import org.firstinspires.ftc.teamcode.commands.ParallelCommand;
import org.firstinspires.ftc.teamcode.commands.Scheduler;
import org.firstinspires.ftc.teamcode.commands.WaitCommand;
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
        GoSideways goSideways = new GoSideways(0.9, 1500, null, null, null);
        DropdownOn raiseDropdown = new DropdownOn(-0.7, 1000);
        GoForwards goForwards = new GoForwards(0.75, 1000);
        DropdownOn lowerDropdown = new DropdownOn(0.5, 1000);
        DropdownOnNoTimeout holdDropdown = new DropdownOnNoTimeout(0.5);
        GoForwards goBackwards = new GoForwards(-0.75, 800);
        ParallelCommand holdAndBack = new ParallelCommand(goBackwards, holdDropdown, true);
        GoSideways goSideways2 = new GoSideways(0.75, 6600, null, null, null);

        //scheduler.add(goSideways);
        scheduler.add(new DropdownOnNoTimeout(-0.5));
        //scheduler.add(new GoSideways(0.75, 250, null, null, null));
        scheduler.add(goForwards);
        scheduler.add(new WaitCommand(500));
        scheduler.add(holdDropdown);
        scheduler.add(new WaitCommand(1000));
        scheduler.add(goBackwards);
        scheduler.add(new DropdownOff());
        scheduler.add(goSideways2);
        scheduler.add(new GoForwards(0.75, 600));
        scheduler.add(new GoSideways(-0.75, 8000,null, null, null));
    }

    @Override
    public void loop() {
        scheduler.loop();
    }
}
