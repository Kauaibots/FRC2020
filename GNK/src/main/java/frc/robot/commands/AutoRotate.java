package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Drive;

public class AutoRotate extends PIDCommand {

    private final static Drive drive = Robot.drive;

    double tolerance = 6;
    double dTolerance = 20;

    public AutoRotate(double degrees) {

        super(new PIDController(RobotPreferences.getRotateP(), RobotPreferences.getRotateI(),
                RobotPreferences.getRotateD()),
                // Close loop on heading
                drive::getYaw,
                // Set reference to target
                degrees,
                // Pipe output to turn robot
                output -> drive.setDrive(0, output),
                // Require the drive
                drive);

        getController().enableContinuousInput(-180, 180);

        getController().setTolerance(tolerance, dTolerance);

        // SendableRegistry.setName(getController(), "Auto Rotate PID");

    }

    @Override
    public void initialize() {

        super.initialize();

        SmartDashboard.putBoolean("Auto Rotate Active", true);

        drive.updateRotatePID();

        getController().setPID(RobotPreferences.getRotateP(), RobotPreferences.getRotateI(),
                RobotPreferences.getRotateD());

    }

    @Override
    public void execute() {

        super.execute();

        SmartDashboard.putNumber("Auto Rotate Error", getController().getPositionError());
        SmartDashboard.putNumber("Auto Rotate Setpoint", getController().getSetpoint());

    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        drive.setDrive(0, 0);
        SmartDashboard.putBoolean("Auto Rotate Active", false);
    }

}
