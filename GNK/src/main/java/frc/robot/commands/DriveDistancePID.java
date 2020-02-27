package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Drive.EncoderEnum;


public class DriveDistancePID extends ProfiledPIDCommand  {


    private final static Drive drive = Robot.drive;

    private final double tolerance = .75;
    private final double dTolerance = 1/12;

    
    public DriveDistancePID(double inches) {

        super(
            new ProfiledPIDController(RobotPreferences.getDriveDistP(), RobotPreferences.getDriveDistI(),
                                      RobotPreferences.getDriveDistD(), new TrapezoidProfile.Constraints(
                                        10/12, //Inches per second
                                        1/12)), //Inches per second per second
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
            super.initialize();

            drive.zeroEncoder(EncoderEnum.MIDDLE);

            SmartDashboard.putBoolean("Drive Distance Active", true);

            drive.updateDriveDistPID();
    
            getController().setPID(RobotPreferences.getDriveDistP(), RobotPreferences.getDriveDistI(),
                    RobotPreferences.getDriveDistD());


        }

        @Override
        public void execute() {
            super.execute();

            SmartDashboard.putNumber("Drive Distance Error", getController().getPositionError());
            SmartDashboard.putNumber("Drive Distance Setpoint", getController().getSetpoint().position);
            SmartDashboard.putNumber("Drive Distance Current", drive.getAverageEncoder());

        }

        @Override
        public boolean isFinished() {
            return getController().atSetpoint();
        }

        @Override
        public void end(boolean interrupted) {
            super.end(interrupted);
            drive.setDrive(0, 0);
            SmartDashboard.putBoolean("Drive Distance Active", false);

        }


    }