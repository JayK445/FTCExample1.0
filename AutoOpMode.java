package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.arcrobotics.ftclib.purepursuit.Waypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.EndWaypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.GeneralWaypoint;
import com.arcrobotics.ftclib.purepursuit.waypoints.StartWaypoint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutoTest", preselectTeleOp = "DriveTeleop")
public class AutoOpMode extends CommandOpMode {

    //Declaring a bunch of different stuff - uses explained later
    private MecanumDriveOdometry m_robotOdometry;
    private AutoDrivebaseCommand ppCommand;
    private MecanumDrive m_robotDrive;
    private Motor fL,fR,bR,bL;
    private MecanumDriveKinematics m_robotKinematics;
    private double[] imuAngles;
    private IMUImplement imu;

    //Translation2ds for the kinematics
    private Translation2d frontLeft, frontRight, backLeft, backRight;

    //Array of waypoints to use for the command constructor (just one way of doing it)
    private Waypoint[] waypoints = {};

    //Define the hardware, schedule commands, etc in initialize()
    @Override
    public void initialize() {

        //define the imu (gyroscope to measure the robot heading)
        imu = new IMUImplement(hardwareMap, "imu");
        imu.init();
        imuAngles = imu.getAngles();

        //defining motors
        fL = new Motor(hardwareMap, "motorfl", Motor.GoBILDA.RPM_312);
        fR = new Motor(hardwareMap, "motorfr", Motor.GoBILDA.RPM_312);
        bL = new Motor(hardwareMap, "motorbl", Motor.GoBILDA.RPM_312);
        bR = new Motor(hardwareMap, "motorbr", Motor.GoBILDA.RPM_312);

        //making MecanumDrive object
        m_robotDrive = new MecanumDrive(true, fL, fR, bL, bR);

        //Setting the distances of each wheel from the conter of the robot
        frontLeft = new Translation2d(-0.250, -0.250);
        frontRight = new Translation2d(0.250, -0.250);
        backLeft = new Translation2d(-0.250, 0.250);
        backRight = new Translation2d(0.250, 0.250);

        //Define kinematic object (kinematics is thing to help find the speed of the wheels, robot, etc)
        m_robotKinematics = new MecanumDriveKinematics(frontLeft, frontRight, backLeft, backRight);

        //Define odometry object (Odometry calculate the position of the robot)
        m_robotOdometry = new MecanumDriveOdometry(m_robotKinematics, Rotation2d.fromDegrees(imuAngles[0]));

        //Define the object of the Command class (puts in the MecanumDrive, Odometry, and waypoints array
        //respectively which match up w/ constructor in the class)
        ppCommand = new AutoDrivebaseCommand(m_robotDrive, m_robotOdometry, waypoints);

        //Schedule the command for the command scheduler to run
        schedule(ppCommand);
    }
}