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
import frc.robot.subsystems.Funnel;

public class CPColor extends CommandBase {

ControlPanel cp = Robot.cPanel;
Funnel funnel = Robot.funnel;

int targetColor;
int counter;
boolean onTarget;


  /**
   * Creates a new CPColor.
   */
  public CPColor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cp, funnel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    targetColor = cp.getTargetColorInt();
    onTarget = false;
    counter = 0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    int currentColor = cp.getCurrColor();

    cp.setArm(.1);

    if (currentColor != targetColor) {
      funnel.setIntake(.3);
      onTarget = false;
    }
    else {
      funnel.setIntake(0); 
      onTarget = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cp.setArm(0);
    funnel.setIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (onTarget) {
      counter++;
    }
    else {
      counter = 0;
    }

    return counter >= 5;

  }
}
