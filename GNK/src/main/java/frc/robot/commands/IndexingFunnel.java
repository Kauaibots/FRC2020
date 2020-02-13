package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

public class IndexingFunnel extends CommandBase {

    private final Funnel funnel = Robot.funnel;

    double power = 1.0;




    public IndexingFunnel() {
        SmartDashboard.putNumber("Funnel Power", power);

        addRequirements(funnel);

    }

    @Override
    public void execute() {

        

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        funnel.setRoller1(0);
        funnel.setRoller2(0);
        funnel.setRoller3(0);
        funnel.setRoller4(0);
        funnel.setRoller5(0);
    }


}