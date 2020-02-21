package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Funnel;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();


    public static RobotContainer robotContainer;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static Drive drive;
    public static Funnel funnel;
    public static Lift1 Lift;
    public static Lift2 Lift2;

    public static RobotPreferences preferences;

    

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        Constants.init();

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
      
        drive = new Drive();
        funnel = new Funnel();
        Lift = new Lift1();
        Lift2 = new Lift2();

        preferences = new RobotPreferences();




        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        robotContainer = new RobotContainer();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);

        SmartDashboard.putBoolean("Rotate PID Tune", false);
        SmartDashboard.putNumber("Rotate P: ", RobotPreferences.getDriveDistP());
        SmartDashboard.putNumber("Rotate I: ", RobotPreferences.getDriveDistI());
        SmartDashboard.putNumber("Rotate D: ", RobotPreferences.getDriveDistD());
 
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.schedule();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.Lift.DISARMED();
        Robot.Lift2.ARMED2();

   }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        CommandScheduler.getInstance().run();
    }
}
