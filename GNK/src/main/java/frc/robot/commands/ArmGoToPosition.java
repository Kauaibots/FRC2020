package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.subsystems.ControlPanel.ControlPosition;

public class ArmGoToPosition extends CommandBase {

    ControlPanel cp = Robot.cPanel;

    int target = 0;

    int count = 0;

    final int downPos = 50;
    final int upPos = 1100;
    final int compressedPos = 1200;

    public ArmGoToPosition(ControlPosition position) {

        target = getTarget(position);

        count = 0;

    }

    @Override
    public void execute() {
        
        int error = getError(cp.getEncoder());
        int absError = Math.abs(error);
        double power = 0;

        if (absError > 500) {
            power = .35;
        }
        else if (absError > 200) {
            power = .2;
        }
        else if (absError > 70) {
            power = .125;
        }
        else if (absError > 20) {
            power = .1;
        }
        else {
            power = 0;
        }


        if (error < 0) {
            power *= .6;
            if (target > 1000) {
                power *= 1.2;
            }
        }


        if (absError > 20) {
            count = 0;
        }
        else {
            count++;
        }


        power *= (error != 0)? (absError/error): 1; //Inverts the output power if negative and protects against dividing by 0

        cp.setArm(power);


        SmartDashboard.putNumber("Arm error", error);
        SmartDashboard.putNumber("Arm Power", power);


    }

    int getError(int currPos) {
        return target-currPos; 
    }

    int getTarget(ControlPosition position) {
        switch (position) {
            case UP:
                return upPos;
            case DOWN:
                return downPos;
            case COMPRESS:
                return compressedPos;
        }
        return 0;
    }


    @Override
    public boolean isFinished() {

        return count > 5;
    }

    @Override
    public void end(boolean interrupted) {
        cp.setArm(0);
    }

}
