package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

    public class Funnel extends SubsystemBase {

        private final WPI_TalonSRX intakeMotor = Constants.intakeMotor;

        public Funnel () {

        }

        @Override
        public void periodic() {
            
        }
            
        public void setIntake(double power) {
            intakeMotor.set(power);
        }



    }