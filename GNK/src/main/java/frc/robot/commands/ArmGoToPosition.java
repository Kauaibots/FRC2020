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

    final int downPos = 0;
    final int upPos = 1150;
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
            power = .5;
        }
        else if (absError > 200) {
            power = .3;
        }
        else if (absError > 50) {
            power = .1;
        }
        else if (absError > 10) {
            power = .025;
        }
        else {
            power = 0;
        }


        if (error < 0) {
            power *= .5;
        }


        if (absError > 10) {
            count = 0;
        }
        else {
            count++;
        }


        power *= (absError/error);

        cp.setArm(power);


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
