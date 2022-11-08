package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

public class IntakeCommand extends CommandBase {

    private IntakeSubsystem subsystem;
    private DoubleSupplier leftX, leftY;

    public IntakeCommand(IntakeSubsystem subsystem, DoubleSupplier leftX, DoubleSupplier leftY) {

        this.subsystem = subsystem;
        this.leftX = leftX;
        this.leftY = leftY;

        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.intakeArmMove(leftX.getAsDouble());
        subsystem.intakeServoMove(leftY.getAsDouble());
    }

}
