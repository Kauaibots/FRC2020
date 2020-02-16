/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lift1.Servo;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ARMED extends CommandBase {
  /**
   * Creates a new ARMED.
   */
  public ARMED() {
    addRequirements(Robot.Lift);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.Lift.ARMED();
    System.out.println("Lift 1 ARMED! DO NOT GO UP");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
