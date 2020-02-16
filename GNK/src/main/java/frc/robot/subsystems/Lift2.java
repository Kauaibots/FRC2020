/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
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
  private DigitalInput SensorUp2 = new DigitalInput(2);
  private DigitalInput SensorDown2 = new DigitalInput(3);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void UP2()
  {
    double Speed3 = 0;
    SensorUp2.get();
    if(SensorUp2.get() == true)
    {
      Speed3 = -.5;
    }
    else if(SensorUp2.get() == false)
    {
      Speed3 = 0;
      System.out.println("Warning: Lift 2 is fully exetended");
    }
    Lift2.set(Speed3);

  }
  public void DOWN2()
  {
    double Speed4 = 0;
    SensorDown2.get();
    if(SensorDown2.get() == true)
    {
      Speed4 = .5;
    }
    else if(SensorDown2.get() == false)
    {
      Speed4 = 0;
      System.out.println("Warning: Lift 2 is fully retracted");
    }
    Lift2.set(Speed4);
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
