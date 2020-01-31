package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Funnel.IntakeState;

public class SetIntake extends CommandBase {

    private final Funnel funnel = Robot.funnel;

    IntakeState state = IntakeState.STOP;

    double power = 1.0;

    public SetIntake(IntakeState state) {
        addRequirements(funnel);
        this.state = state;
    }

    @Override
    public void execute() {

        switch (state) {
            case IN:
                funnel.setIntake(power);
                break;
            case OUT:
                funnel.setIntake(-power);
                break;
            case STOP:
                funnel.setIntake(0);
                break;
        }
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