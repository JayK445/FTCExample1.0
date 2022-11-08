package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

public class DrivebaseCommand extends CommandBase
{
    private DoubleSupplier m_leftX;
    private DoubleSupplier m_leftY;
    private DoubleSupplier m_rightY;
    private DrivebaseSubsystem m_subsystem;

    public DrivebaseCommand(DrivebaseSubsystem subsystem, DoubleSupplier leftX, DoubleSupplier leftY, DoubleSupplier rightY)
    {
        m_subsystem = subsystem;
        m_leftX = leftX;
        m_leftY = leftY;
        m_rightY = rightY;

        // This is needed for defining default commands
        addRequirements(m_subsystem);
    }

    @Override
    public void initialize()
    {

    }

    // All this looping function does is continuously plug controller input into moving the drive-base
    @Override
    public void execute()
    {
        m_subsystem.controlMecanum(m_leftX, m_leftY, m_rightY);
    }
}
