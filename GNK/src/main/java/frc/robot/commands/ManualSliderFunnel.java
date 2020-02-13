package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.*;

public class ManualSliderFunnel extends CommandBase {

    private final Funnel funnel = Robot.funnel;

    double power = 1.0;


    //stage --- A number 0 to 5.9 stating which stages of the funnel to run.
    //Eg. a setting of 2 will make the first two stages run, a setting of 5.3 will make all stages run, set to less than 1 to stop
    double stage = 0;


    public ManualSliderFunnel() {
        SmartDashboard.putNumber("Funnel Power", power);

        addRequirements(funnel);

    }

    @Override
    public void execute() {

        stage = ((-Robot.robotContainer.driveStick.getRawAxis(2))+1)*3;

        power = SmartDashboard.getNumber("Funnel Power", power);
        SmartDashboard.putNumber("Funnel Stage", stage);

        if (stage >= 1) {
            funnel.setRoller1(power);
        }
        else {
            funnel.setRoller1(0);
        }

       if (stage >= 2) {
            funnel.setRoller2(power);
        }
        else {
            funnel.setRoller2(0);
        }

       if (stage >= 3) {
            funnel.setRoller3(power);
        }
        else {
            funnel.setRoller3(0);
        }

        if (stage >= 4) {
            funnel.setRoller4(power);
        }
        else {
            funnel.setRoller4(0);
        }

        if (stage >= 5) {
            funnel.setRoller5(power);
        }
        else {
            funnel.setRoller5(0);
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