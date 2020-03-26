package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

    public class ControlPanel extends SubsystemBase {


        private final WPI_TalonSRX cpArm = Constants.cpArm;

        public enum ControlPosition { DOWN, UP, COMPRESS };

        ColorSensorV3 colorSensor = Constants.colorSensor;
        ColorMatch colorMatcher = Constants.colorMatcher;
        ColorMatchResult colorResult;
       
        public ControlPanel() {


        }

        @Override
        public void periodic() {
            
            SmartDashboard.putNumber("Arm Encoder", getEncoder());
            

            if (Robot.robotContainer.driveStick.getRawButton(5)) {
                zeroEncoder();
            }

            Color detectedColor = colorSensor.getColor();

            colorResult = colorMatcher.matchClosestColor(detectedColor);

            String colorString;

            if (colorResult.color == Constants.kBlueTarget) {
                colorString = "Blue";
              } else if (colorResult.color == Constants.kRedTarget) {
                colorString = "Red";
              } else if (colorResult.color == Constants.kGreenTarget) {
                colorString = "Green";
              } else if (colorResult.color == Constants.kYellowTarget) {
                colorString = "Yellow";
              } else {
                colorString = "Unknown";
              }
          
              /**
               * Open Smart Dashboard or Shuffleboard to see the color detected by the 
               * sensor.
               */
              //SmartDashboard.putNumber("Red", detectedColor.red);
              //SmartDashboard.putNumber("Green", detectedColor.green);
              //SmartDashboard.putNumber("Blue", detectedColor.blue);
              //SmartDashboard.putNumber("Confidence", colorResult.confidence);
              SmartDashboard.putString("Detected Color", colorString);


              SmartDashboard.putNumber("Proximity", getProximity());
            

        }

        
        public void setArm(double power) {
            cpArm.set(power);
        }

        public int getEncoder() {
            return cpArm.getSelectedSensorPosition();
        }

        public void zeroEncoder() {
            cpArm.setSelectedSensorPosition(0);
        }

        public double getProximity() {
            return colorSensor.getProximity();
        }

        public ColorMatchResult getRawColor() {
            return colorResult;
        }

        //Returns an int 0-3 R,Y,B,G of the current color
        public int getCurrColor() {

            ColorMatchResult color = getRawColor();
            int colorInt;

            if (color.color == Constants.kBlueTarget) {
                colorInt = 2;
              } else if (color.color == Constants.kRedTarget) {
                colorInt = 0;
              } else if (color.color == Constants.kGreenTarget) {
                colorInt = 3;
              } else if (color.color == Constants.kYellowTarget) {
                colorInt = 1;
              } else {
                colorInt = 5;
              }

            colorInt = (colorInt != 5)? (colorInt+2):5;

            return colorInt;

        }

        //Returns a string containing the capitalized first letter of the color (R,Y,B,G)
        public String getFMSColor() {
            return DriverStation.getInstance().getGameSpecificMessage();
        }

        //Returns an int 0-3 R,Y,B,G of the target color
        public int getTargetColorInt() {

            String gameData = getFMSColor();
            int colorInt;

            if(gameData.length() > 0) {
                switch (gameData.charAt(0))
                {
                    case 'R' :
                    colorInt = 0;
                    break;
                    case 'Y' :
                    colorInt = 1;
                    break;
                    case 'B' :
                    colorInt = 2;
                    break;
                    case 'G' :
                    colorInt = 3;
                    break;
                    default :
                    colorInt = 5;
                    break;
                }
            } else {
                colorInt = 0;
            }

            return colorInt;

        }

    }