/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Lift1.Motor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Lift1;
import frc.robot.subsystems.Lift1.LiftMotion;

public class LiftMotion1 extends CommandBase {
  /**
   * Creates a new LiftMotion1.
   */
    LiftMotion motion;
    LiftMotion Currentmotion1;
    //private final Lift1 lift = Robot.Lift;

  public LiftMotion1(LiftMotion motion){
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
    Robot.Lift.setLiftMotion(motion);

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
