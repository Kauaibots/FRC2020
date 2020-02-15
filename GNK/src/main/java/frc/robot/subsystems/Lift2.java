/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class Lift2 extends SubsystemBase {
  private CANSparkMax Lift2 = new CANSparkMax(8, MotorType.kBrushless);
  private PWMVictorSPX Servo2 = new PWMVictorSPX(3);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void UP2()
  {
    Lift2.set(.5);
  }
  public void DOWN2()
  {
    Lift2.set(-.5);
  }
  public void STOP2()
  {
    Lift2.set(0);
  }
  public void ARMED2()
  {
    Servo2.setPosition(.1);
  }
  public void DISARMED2()
  {
    Servo2.setPosition(.4);
  }

  
}
