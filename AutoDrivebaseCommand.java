package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.arcrobotics.ftclib.purepursuit.Path;
import com.arcrobotics.ftclib.purepursuit.Waypoint;

public class AutoDrivebaseCommand extends CommandBase {

    //Declare the MecanumDrive, Odometry, and Path from top to bottom.
    //The path is how to use the Pure Pursuit implementation in FTCLib (read docs)
    private MecanumDrive m_drive;
    private MecanumDriveOdometry m_odometry;
    private Path m_path;

    //Constructor
    public AutoDrivebaseCommand(MecanumDrive drive, MecanumDriveOdometry odometry, Waypoint[] waypoints) {
        //Create path with the waypoints provided in the constructor
        m_path = new Path(waypoints);
        m_path.init();

        m_drive = drive;
        m_odometry = odometry;
    }

    //Run each time the command is scheduled (read docs)
    @Override
    public void initialize() {

    }

    //The following three methods are stuff if the path needs to be edited. (add waypoint, add waypoints, remove waypoint)
    public void addWaypoint(Waypoint waypoint) {
        m_path.add(waypoint);
    }

    public void addWaypoints(Waypoint... waypoints) {
        for(Waypoint waypoint : waypoints) {
            this.addWaypoint(waypoint);
        }
    }

    public void removeWaypointAtIndex(int index) {
        m_path.remove(index);
    }

    //Execute method is run after initialize() - runs constantly basically
    @Override
    public void execute() {
        Pose2d robotPose = m_odometry.getPoseMeters();
        double[] motorSpeeds = m_path.loop(robotPose.getTranslation().getX(), robotPose.getTranslation().getY(), robotPose.getHeading());
        m_drive.driveRobotCentric(motorSpeeds[0], motorSpeeds[1], motorSpeeds[2]);
    }

    //stuff so the command knows when to end after running execute()
    @Override
    public void end(boolean isInterrupted) {
        m_drive.stop();
    }

    @Override
    public boolean isFinished() {
        return m_path.isFinished();
    }

}
