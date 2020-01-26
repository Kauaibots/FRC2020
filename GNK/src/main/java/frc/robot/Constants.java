package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Constants {

    public static CANSparkMax spark1;
    public static CANSparkMax spark2;
    public static CANSparkMax spark3;
    public static CANSparkMax spark4;
    public static CANSparkMax spark5;
    public static CANSparkMax spark6;
    public static SpeedControllerGroup m_left;
    public static SpeedControllerGroup m_right;
    public static DifferentialDrive robotDrive;
    public static WPI_TalonSRX intakeMotor;
   

    public static void init() {

        spark1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark1.setInverted(false);

        spark2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark2.setInverted(false);

        spark3 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
       spark3.setInverted(false);
       
        spark4 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark4.setInverted(true);

        spark5 = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark5.setInverted(true);

        spark6 = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark6.setInverted(true);
        
        m_left = new SpeedControllerGroup(spark1, spark2, spark3);
        
        m_right = new SpeedControllerGroup(spark4, spark5, spark6);

        robotDrive = new DifferentialDrive(m_left, m_right);
        robotDrive.setRightSideInverted(false);
        robotDrive.setSafetyEnabled(false);
        

        intakeMotor = new WPI_TalonSRX(1);

    }

}