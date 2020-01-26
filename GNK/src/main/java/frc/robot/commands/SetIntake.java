package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

public class SetIntake extends CommandBase {

    private final Funnel funnel = Robot.funnel;

    double power = 0;

    public SetIntake(double power) {
        addRequirements(funnel);
        this.power = power;
    }

    @Override
    public void execute() {
        funnel.setIntake(power); 

    }


    
    @Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(final boolean interrupted) {
        funnel.setIntake(0);
    }


}