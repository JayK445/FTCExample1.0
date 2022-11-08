package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

public class DrivebaseSubsystem extends SubsystemBase
{
    private Motor m_motor_fl;
    private Motor m_motor_fr;
    private Motor m_motor_bl;
    private Motor m_motor_br;
    private MecanumDrive mecanum;
    IMUImplement imu;
    private Telemetry tele;


    public DrivebaseSubsystem(Telemetry tele, HardwareMap hMap)
    {

        // Defining telemetry as a part of this subsystem in-case we want to print some values
        this.tele = tele;

        // Define our motor hardware for the driving
        m_motor_fl = new Motor(hMap, "motorfl", Motor.GoBILDA.RPM_312);
        m_motor_fr = new Motor(hMap, "motorfr", Motor.GoBILDA.RPM_312);
        m_motor_bl = new Motor(hMap, "motorbl", Motor.GoBILDA.RPM_312);
        m_motor_br = new Motor(hMap, "motorbr", Motor.GoBILDA.RPM_312);

        // Defining a Mecanum Drive-base object; FTCLib saves us from calculating every motor movement
        mecanum = new MecanumDrive(m_motor_fl, m_motor_fr,
                m_motor_bl, m_motor_br);

        imu = new IMUImplement(hMap, "imu");
        imu.init();
    }

    public void controlMecanum(DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightX)
    {
        // Give the defined drive-base object lambda values to dictate how we move
        mecanum.driveRobotCentric(-rightX.getAsDouble()/2, -leftY.getAsDouble()/2, -leftX.getAsDouble()/2);
    }



}
