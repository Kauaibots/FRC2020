/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Lift.LiftMotion;

public class LiftManual extends CommandBase {
  /**
   * Creates a new Lift.
   */
  LiftMotion State;
  int counter;
  private final Lift lift = Robot.Lift;
  double Power = .5;
  public LiftManual(LiftMotion State) {
    this.State = State;
    

    addRequirements(lift);


    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter ++;
    switch (State){
      case UP:
        lift.setServoUnlocked();
        if(counter > 25)
      {
        lift.setLift1(Power);
        lift.setLift2(Power);
      }
        break;
      case DOWN:
        lift.setLift1(Power*-1);
        lift.setLift2(Power*-1);
        lift.setServoLocked();
        break;
      case STOP:
        lift.setLift1(0);
        lift.setLift2(0);
        break;
      case UP1:
      lift.setServoUnlocked();
      if(counter > 25)
      {
        lift.setLift1(Power);
      }
      break;
      case DOWN1:
      lift.setLift1(Power*-1);
      lift.setServoLocked();
      break;
      case UP2:
      lift.setServoUnlocked();
      if(counter > 25)
      {
        lift.setLift2(Power);
      }
      break;
      case DOWN2:
      lift.setLift2(Power*-1);
      lift.setServoLocked();
      break;

    }

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
