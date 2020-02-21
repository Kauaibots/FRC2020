package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Drive;


public class DriveDistance extends ProfiledPIDCommand  {


    private final static Drive drive = Robot.drive;

    private final double tolerance = .75;
    private final double dTolerance = .5;

    
    public DriveDistance(double inches) {

        super(
            new ProfiledPIDController(RobotPreferences.getDriveDistP(), RobotPreferences.getDriveDistI(),
                                      RobotPreferences.getDriveDistD(), new TrapezoidProfile.Constraints(
                12, //Max Feet per second
                3)), //Max Feet per second per second
            // Close loop on heading
            drive::getAverageEncoder,
            // Set reference to target
            inches,
            // Pipe output to turn robot
            (output, setpoint) -> drive.setStraightDrive(output),
            // Require the drive
            drive);

        getController().disableContinuousInput();

        getController().setTolerance(tolerance, dTolerance);



    }

        @Override
        public void initialize() {


        }

        @Override
        public void execute() {
            


        }

        @Override
        public boolean isFinished() {
            return getController().atSetpoint();
        }

        @Override
        public void end(boolean interrupted) {
            drive.setDrive(0, 0);
        }


    }