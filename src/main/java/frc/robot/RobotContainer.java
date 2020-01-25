// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveIntake;
import frc.robot.commands.StickDrive;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Funnel;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class RobotContainer {


    private final Drive drive = new Drive();

    private final StickDrive stickDrive = new StickDrive(drive);

    public Joystick driveStick = new Joystick(0);

    public Funnel funnel = new Funnel();

    public DriveIntake driveIntake = new DriveIntake(funnel, 0);

    /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        drive.setDefaultCommand(stickDrive);
    }

    /**
    * Use this method to define your button->command mappings.  Buttons can be created by
    * instantiating a {@link GenericHID} or one of its subclasses ({@link
    * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
    * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
    */
    private void configureButtonBindings() {

        

    }


    /**
    * Use this to pass the autonomous command to the main {@link Robot} class.
    *
    * @return the command to run in autonomous
    */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return stickDrive;
    }

}

