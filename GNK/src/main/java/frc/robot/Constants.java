package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SPI;

public class Constants {

    public static AHRS imu;
    public static DifferentialDriveOdometry m_odometry;
    public static CANSparkMax spark1;
    public static CANSparkMax spark2;
    public static CANSparkMax spark3;
    public static CANSparkMax spark4;
    public static CANSparkMax spark5;
    public static CANSparkMax spark6;
    public static SpeedControllerGroup m_left;
    public static SpeedControllerGroup m_right;
    public static DifferentialDrive robotDrive;
    public static CANEncoder leftEncoder;
    public static CANEncoder rightEncoder;


    public static WPI_TalonSRX roller1;
    public static WPI_TalonSRX roller2;
    public static WPI_TalonSRX roller3;
    public static WPI_TalonSRX roller4;
    public static WPI_TalonSRX roller5;



    private final static double encoderConversionValue = 1/(4.5/12);  //inverse of ticks per inch
   

    public static void init() {


        imu = new AHRS(SPI.Port.kMXP);

        m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(imu.getAngle()));


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
    
        leftEncoder = new CANEncoder(spark1);
        leftEncoder.setPositionConversionFactor(encoderConversionValue);

        rightEncoder = new CANEncoder(spark4);
        rightEncoder.setPositionConversionFactor(encoderConversionValue);


        roller1 = new WPI_TalonSRX(1);
        roller2 = new WPI_TalonSRX(2);
        roller3 = new WPI_TalonSRX(3);
        roller4 = new WPI_TalonSRX(4);
        roller5 = new WPI_TalonSRX(5);

        roller1.setInverted(true);
        roller2.setInverted(true);
        roller3.setInverted(true);
        roller4.setInverted(true);
        roller5.setInverted(true);
    }

}