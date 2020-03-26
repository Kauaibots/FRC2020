package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Funnel.FunnelState;

public class IndexingFunnel extends CommandBase {

    private final Funnel funnel = Robot.funnel;

    FunnelState state;

    double power = 1.0;

    int timer = 0;



    public IndexingFunnel(FunnelState state) {

        this.state = state;

        SmartDashboard.putNumber("Funnel Power", power);

        addRequirements(funnel);

    }

    @Override
    public void initialize() {
        timer = 0;
    }

    @Override
    public void execute() {

        switch (state) {
            case STOP:
                funnel.stopAllRollers();
                break;
            case DUMP:
                dumpLemons();
                break;
            case REVERSE1:
                funnel.setRoller1(-power);
                funnel.setRoller2(0);
                funnel.setRoller3(0);
                funnel.setRoller4(0);
                funnel.setRoller5(0);
                break;
            case REVERSEALL:
                funnel.setRollerUpTo(5, -power);
                break;
            case COLLECT:
                funnel.collectLemons(power);
                break;
        }

        timer++;

    }



    private void dumpLemons() {

        funnel.setRoller(3, power);
        funnel.setRoller(4, power);
        funnel.setRoller(5, power);
        if (timer > 10) {
            funnel.setRoller(2, power);
        }
        if (timer > 15) {
            funnel.setRoller(1, power);
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