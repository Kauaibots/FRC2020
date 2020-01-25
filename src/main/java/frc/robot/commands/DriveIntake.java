package frc.robot.commands;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class DriveIntake extends CommandBase {

    public final Funnel m_Funnel;
    double power = 0;

    public DriveIntake(Funnel funnel, double power) {
            m_Funnel = funnel;
            addRequirements(funnel);
            this.power = power;
        }




        @Override
        public void execute() {
            RobotContainer.funnel.setIntake(power); 

        }
    }