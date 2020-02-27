package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive extends SubsystemBase {

    private final DifferentialDrive robotDrive = Constants.robotDrive;

    public enum EncoderEnum { LEFT, RIGHT, MIDDLE, RAWLEFT, RAWRIGHT, RAWMIDDLE };

    private double leftEncoderOffset = 0;
    private double rightEncoderOffset = 0;


    public Drive() {
        
        SmartDashboard.putBoolean("Rotate PID Tune", false);
        SmartDashboard.putNumber("Rotate P: ", RobotPreferences.getRotateP());
        SmartDashboard.putNumber("Rotate I: ", RobotPreferences.getRotateI());
        SmartDashboard.putNumber("Rotate D: ", RobotPreferences.getRotateD());
        
    //    SmartDashboard.putBoolean("DriveDist PID Tune", false);
    //    SmartDashboard.putNumber("DriveDist P: ", RobotPreferences.getDriveDistP());
    //    SmartDashboard.putNumber("DriveDist I: ", RobotPreferences.getDriveDistI());
    //    SmartDashboard.putNumber("DriveDist D: ", RobotPreferences.getDriveDistD());

    }

   


    @Override
    public void periodic() {

        SmartDashboard.putNumber("Encoder Left", getEncoder(EncoderEnum.LEFT));
        SmartDashboard.putNumber("Encoder Right", getEncoder(EncoderEnum.RIGHT));
        SmartDashboard.putNumber("Encoder Conversion", Constants.leftEncoder.getPositionConversionFactor());
        SmartDashboard.putNumber("Encoder CPR", Constants.leftEncoder.getCountsPerRevolution());

        SmartDashboard.putNumber("Yaw", getYaw());

        if (Robot.robotContainer.driveStick.getRawButton(3)) {
    //        zeroEncoder(EncoderEnum.MIDDLE);
        }

        if (Robot.robotContainer.driveStick.getRawButton(6)) {
            zeroYaw();
        }


        if (SmartDashboard.getBoolean("Rotate PID Tune", false)) {

            updateRotatePID();
        }





        Constants.m_odometry.update(Rotation2d.fromDegrees(getYaw()), inchToMeter(getEncoder(EncoderEnum.RAWLEFT)), inchToMeter(getEncoder(EncoderEnum.RAWRIGHT)));

    }



    public void setDrive(double vY, double vRot) {
        robotDrive.arcadeDrive(vY, vRot);

    }

    public void setStraightDrive(double vY) {

        double leftEncoder = getEncoder(EncoderEnum.LEFT);
        double rightEncoder = getEncoder(EncoderEnum.RIGHT);

        double leftPower = vY;
        double rightPower = vY;

        if (leftEncoder > rightEncoder) {
            leftPower *= 0.9;
            rightPower *= 1.0;
        }
        else if (rightEncoder > leftEncoder) {
            leftPower *= 1.0;
            rightPower *= 0.9;
        }

        robotDrive.tankDrive(leftPower, rightPower);
    }

    public void setTankVoltage(double leftVolts, double rightVolts) {
        Constants.m_left.setVoltage(leftVolts);
        Constants.m_right.setVoltage(-rightVolts);
        Constants.robotDrive.feed();
    }

    public double getDrivePower(){
        return (Constants.spark1.get()+Constants.spark4.get())/2;
    }

    public Pose2d getPose() {
        return Constants.m_odometry.getPoseMeters();
      }

    public void resetOdometry(Pose2d pose) {
        zeroEncoder(EncoderEnum.RAWLEFT);
        zeroEncoder(EncoderEnum.RAWRIGHT);
        Constants.m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getYaw()));
    }

    public double getEncoder(EncoderEnum encoder) {
        switch (encoder) {
        case LEFT:
            return -Constants.leftEncoder.getPosition()-leftEncoderOffset;
        case RIGHT:
            return Constants.rightEncoder.getPosition()-rightEncoderOffset;
        case MIDDLE:
            return (getEncoder(EncoderEnum.LEFT)+getEncoder(EncoderEnum.RIGHT))/2;
        case RAWLEFT:
            return -Constants.leftEncoder.getPosition();
        case RAWRIGHT:
            return Constants.rightEncoder.getPosition();
        case RAWMIDDLE:
            return (getEncoder(EncoderEnum.RAWLEFT)+getEncoder(EncoderEnum.RAWRIGHT))/2;
        }

        return 0;
    }

    public double getAverageEncoder() {
        return getEncoder(EncoderEnum.MIDDLE);
    }

    public void zeroEncoder(EncoderEnum encoder) {
        switch (encoder) {
            case LEFT:
                leftEncoderOffset = getEncoder(EncoderEnum.RAWLEFT);
                break;
            case RIGHT:
                rightEncoderOffset = getEncoder(EncoderEnum.RAWRIGHT);
                break;
            case MIDDLE:
                zeroEncoder(EncoderEnum.LEFT);
                zeroEncoder(EncoderEnum.RIGHT);
                break;
            case RAWLEFT:
                Constants.leftEncoder.setPosition(0);
                leftEncoderOffset = 0;
                break;
            case RAWRIGHT:
                Constants.rightEncoder.setPosition(0);
                rightEncoderOffset = 0;
                break;
            case RAWMIDDLE:
                zeroEncoder(EncoderEnum.RAWLEFT);
                zeroEncoder(EncoderEnum.RAWRIGHT);
                break;
        }
            
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(Constants.leftEncoder.getVelocity(), Constants.leftEncoder.getVelocity());
      }

    public double getYaw() {
        return Constants.imu.getYaw();
    }

    public double getPitch() {
        return Constants.imu.getPitch();
    }

    public double getTurnRate() {
        return Constants.imu.getRate();
    }

    public void zeroYaw() {
        Constants.imu.zeroYaw();
    }

    public double inchToMeter(double inch) {
        return inch/39.37;
    }

    public double meterToInch(double meter) {
        return meter*39.37;
    }


    public void updateRotatePID() {
        RobotPreferences.setRotateP(SmartDashboard.getNumber("Rotate P: ", 0.0));
        RobotPreferences.setRotateI(SmartDashboard.getNumber("Rotate I: ", 0.0));
        RobotPreferences.setRotateD(SmartDashboard.getNumber("Rotate D: ", 0.0));
    }

    public void updateDriveDistPID() {
        RobotPreferences.setDriveDistP(SmartDashboard.getNumber("DriveDist P: ", 0.0));
        RobotPreferences.setDriveDistI(SmartDashboard.getNumber("DriveDist I: ", 0.0));
        RobotPreferences.setDriveDistD(SmartDashboard.getNumber("DriveDist D: ", 0.0));
    }

}

