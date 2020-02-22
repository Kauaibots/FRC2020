package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANAnalog.AnalogMode;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.AnalogInput;
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

    private static final AlternateEncoderType kAltEncType = AlternateEncoderType.kQuadrature;
    private static final int kCPR = 1024;
    public static CANEncoder leftEncoder;
    public static CANEncoder rightEncoder;



    public static WPI_TalonSRX roller1;
    public static WPI_TalonSRX roller2;
    public static WPI_TalonSRX roller3;
    public static WPI_TalonSRX roller4;
    public static WPI_TalonSRX roller5;

    public static WPI_TalonSRX intakeMotor;
    public static WPI_TalonSRX cpArm;

    public static AnalogInput ir1;
    public static AnalogInput ir2;
    public static AnalogInput ir3;
    public static AnalogInput ir4;
    public static AnalogInput ir5;


    private static final double driveRampRate = .25;



    private final static double encoderConversionValue = 1/(4.5/12);  //inverse of ticks per inch
   

    public static void init() {


        imu = new AHRS(SPI.Port.kMXP);

        driveInit();

        m_left = new SpeedControllerGroup(spark1, spark2, spark3);
        
        m_right = new SpeedControllerGroup(spark4, spark5, spark6);

        robotDrive = new DifferentialDrive(m_left, m_right);
        robotDrive.setRightSideInverted(false);
        robotDrive.setSafetyEnabled(false);
    
        leftEncoder = spark1.getAlternateEncoder(kAltEncType, kCPR);
        leftEncoder.setPositionConversionFactor(encoderConversionValue);


        rightEncoder = spark4.getAlternateEncoder(kAltEncType, kCPR);
        rightEncoder.setPositionConversionFactor(encoderConversionValue);


        roller1 = new WPI_TalonSRX(1);
        roller2 = new WPI_TalonSRX(2);
        roller3 = new WPI_TalonSRX(3);
        roller4 = new WPI_TalonSRX(4);
        roller5 = new WPI_TalonSRX(5);

        intakeMotor = new WPI_TalonSRX(6);
        cpArm = new WPI_TalonSRX(7);

        roller1.setNeutralMode(NeutralMode.Brake);
        roller2.setNeutralMode(NeutralMode.Brake);
        roller3.setNeutralMode(NeutralMode.Brake);
        roller4.setNeutralMode(NeutralMode.Brake);
        roller5.setNeutralMode(NeutralMode.Brake);

        intakeMotor.setNeutralMode(NeutralMode.Brake);
        cpArm.setNeutralMode(NeutralMode.Brake);
        cpArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        cpArm.setSensorPhase(true);

        roller1.setInverted(true);
        roller2.setInverted(false);
        roller3.setInverted(true);
        roller4.setInverted(true);
        roller5.setInverted(true);

        intakeMotor.setInverted(true);

        ir1 = new AnalogInput(0);
        ir2 = new AnalogInput(1);
        ir3 = new AnalogInput(2);
        ir4 = new AnalogInput(3);
        ir5 = new AnalogInput(6);

    }


    void driveInit() {

    }

}