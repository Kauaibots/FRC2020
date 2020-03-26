/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drive;

public class CPDrivePosition extends CommandBase {

  Drive drive = Robot.drive;
  ControlPanel cp = Robot.cPanel;

  double drivePower = 0.25;

  boolean finished;
 


  /**
   * Creates a new CPPosition.
   */
  public CPDrivePosition() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    finished = false;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (cp.getProximity() < 130) {
      drive.setStraightDrive(drivePower);
    }
    else {
      finished = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.setStraightDrive(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
