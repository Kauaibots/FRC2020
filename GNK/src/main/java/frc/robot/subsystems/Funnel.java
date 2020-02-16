package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

    public class Funnel extends SubsystemBase {

        public enum FunnelState { COLLECT, DUMP, STOP, REVERSE1, REVERSEALL };
        public enum EmptyStage { ZERO, ONE, TWO, THREE, FOUR, FIVE }

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

        //The voltage value for each ir sensor to detect a ball's presence
        private final double ballVolt1 = 1;
        private final double ballVolt2 = 2;
        private final double ballVolt3 = 1.4;
        private final double ballVolt4 = 1;
        private final double ballVolt5 = 1.5;


        public Funnel () {

        }

        @Override
        public void periodic() {
            SmartDashboard.putNumber("Ball Sensor 1", getIR1());
            SmartDashboard.putNumber("Ball Sensor 2", getIR2());
            SmartDashboard.putNumber("Ball Sensor 3", getIR3());
            SmartDashboard.putNumber("Ball Sensor 4", getIR4());
            SmartDashboard.putNumber("Ball Sensor 5", getIR5());
        }

        public void setRoller(int roller, double power) {
            if (roller == 1) {
                setRoller1(power);
            }
            else if (roller == 2) {
                setRoller2(power);
            }
            else if (roller == 3) {
                setRoller3(power);
            }
            else if (roller == 4) {
                setRoller4(power);
            }
            else if (roller == 5) {
                setRoller5(power);
            }

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

        //Turns on all rollers up until the given stage
        public void setRollers(int stages, double power) {
            for (int i = 1; i <= stages; i++) {
                setRoller(i, power);
            }
        }

        public void collectLemons(double power) {

            //If there is no ball anywhere, 4 moves. If there is one in pos 5, 3 moves
            int stageToMove = (getBallFill() != 0) ? getBallFill()-2:4;

            setRollers(stageToMove, power);

        }

        //Returns an int representing the lowest filled stage
        public int getBallFill() {
            for (int i = 5; i > 0; i--) {
                if (getBallPresent(i)){
                    return i;
                }
            }
            return 0;
        }

        public void stopAllRollers() {
            setRoller1(0);
            setRoller2(0);
            setRoller3(0);
            setRoller4(0);
            setRoller5(0);
        }

        public double getIR1() {
            return ir1.getAverageVoltage();
        }

        public double getIR2() {
            return ir2.getAverageVoltage();
        }

        public double getIR3() {
            return ir3.getAverageVoltage();
        }

        public double getIR4() {
            return ir4.getAverageVoltage();
        }

        public double getIR5() {
            return ir5.getAverageVoltage();
        }


        public boolean ballPresent1() {
            return (getIR1() > ballVolt1);
        }

        public boolean ballPresent2() {
            return (getIR2() > ballVolt2);
        }

        public boolean ballPresent3() {
            return (getIR3() > ballVolt3);
        }

        public boolean ballPresent4() {
            return (getIR4() > ballVolt4);
        }

        public boolean ballPresent5() {
            return (getIR5() > ballVolt5);
        }

        public boolean getBallPresent(int stage) {
            if (stage == 1) {
                return ballPresent1();
            }
            else if (stage == 2) {
                return ballPresent2();
            }
            else if (stage == 3) {
                return ballPresent3();
            }
            else if (stage == 4) {
                return ballPresent4();
            }
            else if (stage == 5) {
                return ballPresent5();
            }
            else {
                return false;
            }
        }

    }