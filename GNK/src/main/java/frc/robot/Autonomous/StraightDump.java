/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.IndexingFunnel;
import frc.robot.subsystems.Funnel.FunnelState;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class StraightDump extends SequentialCommandGroup {
  /**
   * Creates a new StraightDump.
   */
  public StraightDump() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(

    new DriveDistance(120-32).withTimeout(3),
    new IndexingFunnel(FunnelState.DUMP).withTimeout(1.5)


    );
  }
}
