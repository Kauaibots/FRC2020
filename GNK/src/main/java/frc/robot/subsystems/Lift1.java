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
import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class Lift1 extends SubsystemBase {
  private CANSparkMax LiftSpark = new CANSparkMax(7, MotorType.kBrushless);
  private PWMVictorSPX Servo1 = new PWMVictorSPX(1);
  public enum LiftMotion {Up, Down, Stop};
  private DigitalInput SensorUp = new DigitalInput(0);
  private DigitalInput SensorDown = new DigitalInput(1);
  //public enum ServoPosition{Armed, Disarmed};
  public LiftMotion motion = LiftMotion.Stop;
  //public ServoPosition position = ServoPosition.Armed;
  // Put methods for controlling this subsystem
  // here. Call these from Commands
  
  public void setLiftMotion(LiftMotion motion)
  {
    switch(motion)
    {
      case Up:
      LiftSpark.set(.5);
      System.out.println("LIFT 1 ENUM UP");
      break;
      case Down:
      LiftSpark.set(-.5);
      System.out.println("LIFT 1 ENUM DOWN");
      break;
      case Stop:
      LiftSpark.set(0);
      System.out.println("LIFT 1 ENUM STOP");
      break;
    }
  }
  
  public void UP()
  {
    double Speed = 0;
    SensorUp.get();
    if(SensorUp.get() == true)
    {
      Speed = .5;
    }
    else if(SensorUp.get() == false)
    {
      Speed = 0;
      System.out.println("Warning: Lift 1 is fully extended");
    }
    LiftSpark.set(Speed);
  }
  public void DOWN()
  {
    double Speed2 = 0;
    SensorDown.get();
    if(SensorDown.get() == true)
    {
      Speed2 = -.5;
    }
    else if(SensorDown.get() == false)
    {
      Speed2 = 0;
      System.out.println("Warning: Lift 1 is fully retracted");
    }
    LiftSpark.set(Speed2);
  }
  public void STOP()
  {
    LiftSpark.set(0);
  }
  public void DISARMED()
  {
    Servo1.setPosition(.4);
  }
  public void ARMED()
  {
    Servo1.setPosition(0);
  }


 
}
