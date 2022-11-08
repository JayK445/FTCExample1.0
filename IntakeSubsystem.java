package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {

    private Telemetry tele;
    private Motor motor;
    private CRServo servo;

    public IntakeSubsystem(Telemetry tele, HardwareMap hMap) {
        this.tele = tele;

        motor = new Motor(hMap, "intake_motor");
        servo = new CRServo(hMap, "intake_servo");
    }

    public void intakeArmMove(double leftX) {
        motor.set(leftX/3);
    }

    public void intakeServoMove(double leftY) {
        servo.set(leftY/3);
    }

}
