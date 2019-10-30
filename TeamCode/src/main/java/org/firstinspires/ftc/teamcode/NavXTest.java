package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp
public class NavXTest extends OpMode {
    public NavxMicroNavigationSensor navx;

    @Override
    public void init() {
        NavxMicroNavigationSensor.Parameters params = new NavxMicroNavigationSensor.Parameters();

        navx = hardwareMap.get(NavxMicroNavigationSensor.class, "navx");
        navx.initialize(params);
    }


    @Override
    public void loop() {
        Orientation orientation = navx.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double yaw = orientation.firstAngle;

        telemetry.addData("Yaw", yaw);
    }
}
