/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmGoToPosition;
import frc.robot.commands.CPColor;
import frc.robot.commands.CPDrivePosition;
import frc.robot.commands.CPRollerRotate;
import frc.robot.subsystems.ControlPanel.ControlPosition;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class CPToColor extends SequentialCommandGroup {
  /**
   * Creates a new CPRotate.
   */
  public CPToColor() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(

      new ArmGoToPosition(ControlPosition.UP).withTimeout(1),
      new CPDrivePosition().withTimeout(4),
      //new ArmGoToPosition(ControlPosition.COMPRESS).withTimeout(1),
      new CPColor().withTimeout(4),
      new ArmGoToPosition(ControlPosition.DOWN).withTimeout(1)

    );
  }
}
