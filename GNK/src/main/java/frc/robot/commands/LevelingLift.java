/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Lift.LiftMotion;

public class LevelingLift extends CommandBase {
  /**
   * Creates a new Lift.
   */
  LiftMotion state;
  int counter;
  private final Lift lift = Robot.Lift;
  private final Drive drive = Robot.drive;
  double power = .5;

  public LevelingLift(LiftMotion State) {
    this.state = State;

    addRequirements(lift);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
    if (state == LiftMotion.UP || state == LiftMotion.DOWN) {
      lift.zeroLift1Pos();
      lift.zeroLift2Pos();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter++;
    switch (state) {
    case UP:
      lift.setServoUnlocked();
      if (counter > 25) {
        leveledExtension();
      }
      break;
    case DOWN:
      leveledRectraction();
      lift.setServoLocked();
      break;
    case STOP:
      lift.setLift1(0);
      lift.setLift2(0);
      break;
    case UP1:
      lift.setServoUnlocked();
      if (counter > 25) {
        lift.setLift1(power);
      }
      break;
    case DOWN1:
      lift.setLift1(power * -1);
      lift.setServoLocked();
      break;
    case UP2:
      lift.setServoUnlocked();
      if (counter > 25) {
        lift.setLift2(power);
      }
      break;
    case DOWN2:
      lift.setLift2(power * -1);
      lift.setServoLocked();
      break;

    }

  }

  public void leveledExtension() {

    double power1 = power;
    double power2 = power;

    double lift1Pos = lift.getLift1Pos();
    double lift2Pos = lift.getLift2Pos();

    if (lift1Pos > lift2Pos) {
      power1 *= .95;
      power2 *= 1.05;
    }
    else if (lift1Pos < lift2Pos) {
      power1 *= 1.05;
      power2 *= .95;

      lift.setLift1(power1);
      lift.setLift2(power2);
    }

  }

  public void leveledRectraction() {
    double roll = drive.getRoll();

    double power1 = -power;
    double power2 = -power;

    if (roll < -2) {
      power1 *= 1.05;
      power2 *= .75;
    }
    if (roll > 2) {
      power1 *= .75;
      power2 *= 1.05;
    }

    lift.setLift1(power1);
    lift.setLift2(power2);
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
