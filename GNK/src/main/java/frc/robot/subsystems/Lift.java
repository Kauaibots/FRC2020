/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


/**
 * Add your docs here.
 */
public class Lift extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final CANSparkMax LiftSpark1 = Constants.Lift1;
  private final DigitalInput Lift1Up = Constants.Lift1Up;
  private final DigitalInput Lift1Down = Constants.Lift1Down; 
  private final CANSparkMax LiftSpark2 = Constants.Lift2;
  private final DigitalInput Lift2Up = Constants.Lift2Up;
  private final DigitalInput Lift2Down = Constants.Lift2Down; 
  private final Servo Lift1Servo = Constants.Lift1Servo;
  private final Servo Lift2Servo = Constants.Lift2Servo;
  public enum LiftMotion {UP, DOWN, STOP, UP1, DOWN1, UP2, DOWN2};

  public Lift(){
    
  }
  public void setLift1(double power)
  {
    //double Power;
    if(power > 0)
    {
      getLiftatTop();
      if(!getLiftatTop() && getServosUnlocked())
      {
        LiftSpark1.set(power);
      }
      if(getLiftatTop())
      {
        System.out.print("Lift Fully Extended");
        LiftSpark1.set(0);
      }

    }
    else if(power < 0)
    {
      getLiftatBottom();
      if(!getLiftatBottom())
      {
        LiftSpark1.set(power);
      }
      if(getLiftatBottom())
      {
        LiftSpark1.set(0);
      }

    }
    else
    {
      LiftSpark1.set(0);
    }
    
  }
  public boolean getLiftatTop()
  {
    return !Lift1Up.get();
  }
  public boolean getLiftatBottom()
  {
    return !Lift1Down.get();
  }


  
  public void setLift2(double power)
  {
    //double Power;
    if(power > 0)
    {
      getLiftatTop2();
      if(!getLiftatTop2() && getServosUnlocked() == true /* redundant - kaden 2020 */)
      {
        LiftSpark2.set(power);
      }
      if(getLiftatTop2())
      {
        LiftSpark2.set(0);
      }

    }
    else if(power < 0)
    {
      getLiftatBottom2();
      if(!getLiftatBottom2())
      {
        LiftSpark2.set(power);
      }
      if(getLiftatBottom2())
      {
        LiftSpark2.set(0);
      }

    }
    else
    {
      LiftSpark2.set(0);
    }
    
  }

  public boolean getLiftatTop2()
  {
    return !Lift2Up.get();
  }
  public boolean getLiftatBottom2()
  {
    return !Lift2Down.get();
  }

  public void setServoLocked()
  {
    Lift1Servo.set(0);
    Lift2Servo.set(.4);
  }
  public void setServoUnlocked()
  {
    Lift1Servo.set(.4);
    Lift2Servo.set(.2);
  }
  public boolean getServosUnlocked()
  {
    if(Lift1Servo.get() > .3 && Lift2Servo.get() < .3)
    {
      return true;
    }
    else 
    {
      return false;
    }

    
  }


  public double getLift1Pos() {
    return LiftSpark1.getEncoder().getPosition();
  }

  public double getLift2Pos() {
    return LiftSpark2.getEncoder().getPosition();
  }

  public void zeroLift1Pos() {
    LiftSpark1.getEncoder().setPosition(0);
  }
 
  public void zeroLift2Pos() {
    LiftSpark2.getEncoder().setPosition(0);
  }

}
