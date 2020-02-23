package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

    public class ControlPanel extends SubsystemBase {


        private final WPI_TalonSRX cpArm = Constants.cpArm;

        public enum ControlPosition { DOWN, UP, COMPRESS };
       
        public ControlPanel() {


        }

        @Override
        public void periodic() {
            
            SmartDashboard.putNumber("Arm Encoder", getEncoder());
            

            if (Robot.robotContainer.driveStick.getRawButton(6)) {
                //zeroEncoder();
            }

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

    }