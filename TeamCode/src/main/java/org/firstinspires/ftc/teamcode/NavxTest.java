package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp
public class NavxTest extends OpMode {

    private NavxMicroNavigationSensor navx;

    @Override
    public void init() {
        navx = hardwareMap.get(NavxMicroNavigationSensor.class, "navx");
    }

    @Override
    public void loop() {
        Orientation orientation = navx.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        telemetry.addData("navx angle", orientation.firstAngle);
        telemetry.update();
    }
}
