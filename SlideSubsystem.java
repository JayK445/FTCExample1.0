package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SlideSubsystem extends SubsystemBase {

    private Telemetry tele;
    private Motor motor1, motor2;
    private final double multiplier = 0.3;

    public SlideSubsystem(HardwareMap hMap, Telemetry tele) {

        this.tele = tele;

        motor1 = new Motor(hMap, "motor_slide");
        motor2 = new Motor(hMap, "motor_slide2");
    }

    public void slideMove(Double rightY) {
        motor1.set(rightY*multiplier);
        motor2.set(rightY*multiplier);
    }
}
