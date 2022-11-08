package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

public class SlideCommand extends CommandBase {

    private DoubleSupplier rightY;
    private SlideSubsystem subsystem;

    public SlideCommand(SlideSubsystem subsystem, DoubleSupplier rightY) {

        this.subsystem = subsystem;
        this.rightY = rightY;

        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.slideMove(rightY.getAsDouble());
    }

}
