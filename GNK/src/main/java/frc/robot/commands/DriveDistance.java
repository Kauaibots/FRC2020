package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Drive.EncoderEnum;


public class DriveDistance extends CommandBase  {


    private final static Drive drive = Robot.drive;

    private double target = 0;
    private final double tolerance = .75;

    private int counter;
    private int timer;

    
    public DriveDistance(double inches) {

        target = inches;

        addRequirements(drive);


    }

        @Override
        public void initialize() {

            

            drive.zeroEncoder(EncoderEnum.MIDDLE);
            counter = 0;
            timer = 0;

            SmartDashboard.putBoolean("Drive Distance Active", true);



        }

        @Override
        public void execute() {

            timer++;

            double error = target-drive.getAverageEncoder();
            double absError = Math.abs(error);


            double power = 0;

            if (absError > 25) {
                power = .7;
                if (timer < 10) {
                    //power *= timer/10;
                }
            }
            else if (absError > 20) {
                power = .5;
            }
            else if (absError > 10) {
                power = .35;
            }
            else if (absError > 4) {
                power = .3;
            }
            else if (absError > tolerance) {
                power = .15;
            }
            else {
                power = 0;
            }

            power *= (error != 0)? (absError/error): 1; //Inverts the output power if negative and protects against dividing by 0
    

            SmartDashboard.putNumber("Drive Dist Power", power);
    
            if (absError > tolerance) {
                counter = 0;
            }
            else {
                counter++;
            }


            drive.setStraightDrive(power);



            SmartDashboard.putNumber("Drive Distance Error", error);
            SmartDashboard.putNumber("Drive Distance Setpoint", target);
            SmartDashboard.putNumber("Drive Distance Current", drive.getAverageEncoder());

        }        
        
        

        
        
        

        @Override
        public boolean isFinished() {
            return counter > 10;
        }

        @Override
        public void end(boolean interrupted) {
            super.end(interrupted);
            drive.setDrive(0, 0);
            SmartDashboard.putBoolean("Drive Distance Active", false);

        }


    }