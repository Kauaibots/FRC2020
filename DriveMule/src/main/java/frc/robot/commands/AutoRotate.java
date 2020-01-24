package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDBase;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Controller;

    public class AutoRotate extends CommandBase  {
      
        private final double kP = 0.0;
    private final double kI = 0.0;
    private final double kD = 0.0;
        private double pidOut;
    @Override
    public void initialize() {
        

    PIDController pid = new PIDController(kP, kI, kD);

        pidOut = pid.calculate(1, 1);
       
        pid.getVelocityError();

        pid.setTolerance(5);
        pid.atSetpoint();

        pid.setIntegratorRange(-0.5, 0.5);

        pid.enableContinuousInput(-180, 180);

        MathUtil.clamp(pid.calculate(1, 1), -1, 1);

    }

        

    }
