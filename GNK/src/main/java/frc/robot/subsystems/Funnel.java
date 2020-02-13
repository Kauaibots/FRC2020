package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

    public class Funnel extends SubsystemBase {

        private final WPI_TalonSRX roller1 = Constants.roller1;
        private final WPI_TalonSRX roller2 = Constants.roller2;
        private final WPI_TalonSRX roller3 = Constants.roller3;
        private final WPI_TalonSRX roller4 = Constants.roller4;
        private final WPI_TalonSRX roller5 = Constants.roller5;

        private final AnalogInput ir1 = Constants.ir1;
        private final AnalogInput ir2 = Constants.ir2;
        private final AnalogInput ir3 = Constants.ir3;
        private final AnalogInput ir4 = Constants.ir4;
        private final AnalogInput ir5 = Constants.ir5;


        public Funnel () {

        }

        @Override
        public void periodic() {
            
        }
            
        public void setRoller1(double power) {
            roller1.set(power);
        }

        public void setRoller2(double power) {
            roller2.set(power);
        }

        public void setRoller3(double power) {
            roller3.set(power);
        }

        public void setRoller4(double power) {
            roller4.set(power);
        }

        public void setRoller5(double power) {
            roller5.set(power);
        }

        public boolean getIR1() {
            ir1.getAverageVoltage();

            return false;
        }


    }