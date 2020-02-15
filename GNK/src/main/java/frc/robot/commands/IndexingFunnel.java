package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Funnel.FunnelState;

public class IndexingFunnel extends CommandBase {

    private final Funnel funnel = Robot.funnel;

    FunnelState state;

    double power = 1.0;




    public IndexingFunnel(FunnelState state) {

        this.state = state;

        SmartDashboard.putNumber("Funnel Power", power);

        addRequirements(funnel);

    }

    @Override
    public void execute() {

        switch (state) {
            case STOP:
                funnel.setRoller1(0);
                funnel.setRoller2(0);
                funnel.setRoller3(0);
                funnel.setRoller4(0);
                funnel.setRoller5(0);
                break;
            case DUMP:
                funnel.setRoller1(power);
                funnel.setRoller2(power);
                funnel.setRoller3(power);
                funnel.setRoller4(power);
                funnel.setRoller5(power);
                break;
            case REVERSE1:
                funnel.setRoller1(-power);
                funnel.setRoller2(0);
                funnel.setRoller3(0);
                funnel.setRoller4(0);
                funnel.setRoller5(0);
                break;
            case REVERSEALL:
                funnel.setRoller1(-power);
                funnel.setRoller2(-power);
                funnel.setRoller3(-power);
                funnel.setRoller4(-power);
                funnel.setRoller5(-power);
                break;
            case COLLECT:
                collectLemons();
                break;
        }

    }

    public void collectLemons() {

        if (!funnel.ballPresent1()) {
            funnel.setRoller1(power);

            if (!funnel.ballPresent2()) {
                funnel.setRoller2(power);

                if (!funnel.ballPresent3()) {
                    funnel.setRoller3(power);

                    if (!funnel.ballPresent4()) {
                        funnel.setRoller4(power);

                        if (!funnel.ballPresent5()) {
                            funnel.setRoller5(power);
                        }
                    }
                }
            }
        }

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