package org.firstinspires.ftc.teamcode;
public class PIDController{

    public double kP;
    public double kI;
    public double kD;

    public PIDController(double kP, double kI, double kD) {

        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }
}
