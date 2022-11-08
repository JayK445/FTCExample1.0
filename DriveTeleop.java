package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "DriveTeleop")
public class DriveTeleop extends CommandOpMode {

    private DrivebaseSubsystem subsystem;
    private SlideSubsystem slide_subsystem;
    private IntakeSubsystem intake_subsystem;
    private GamepadEx gamepad_1;
    private GamepadEx gamepad_2;

    @Override
    public void initialize() {
        subsystem = new DrivebaseSubsystem(telemetry, hardwareMap);
        slide_subsystem = new SlideSubsystem(hardwareMap, telemetry);
        intake_subsystem = new IntakeSubsystem(telemetry, hardwareMap);

        gamepad_1 = new GamepadEx(gamepad1);
        gamepad_2 = new GamepadEx(gamepad2);

        DrivebaseCommand command = new DrivebaseCommand(subsystem, gamepad_1::getLeftX, gamepad_1::getLeftY, gamepad_1::getRightX);
        SlideCommand slide_command = new SlideCommand(slide_subsystem, gamepad_2::getRightY);
        IntakeCommand intake_command = new IntakeCommand(intake_subsystem, gamepad_2::getLeftX, gamepad_2::getLeftY);

        register(subsystem);
        CommandScheduler.getInstance().setDefaultCommand(subsystem, command);
        register(slide_subsystem);
        CommandScheduler.getInstance().setDefaultCommand(slide_subsystem, slide_command);
        register(intake_subsystem);
        CommandScheduler.getInstance().setDefaultCommand(intake_subsystem, intake_command);
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
    }
}
