package frc.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotPreferences {



    public static double getRotateP() {
        return Preferences.getInstance().getDouble("Rotate kP", 0.0);
    }

    public static double getRotateI() {
        return Preferences.getInstance().getDouble("Rotate kI", 0.0);
    }

    public static double getRotateD() {
        return Preferences.getInstance().getDouble("Rotate kD", 0.0);
    }

    public static double getDriveDistP() {
        return Preferences.getInstance().getDouble("DriveDist kP", 0.0);
    }

    public static double getDriveDistI() {
        return Preferences.getInstance().getDouble("DriveDist kI", 0.0);
    }

    public static double getDriveDistD() {
        return Preferences.getInstance().getDouble("DriveDist kD", 0.0);
    }









    public static void setRotateP(double kP) {
        Preferences.getInstance().putDouble("Rotate kP", kP);
    }

    public static void setRotateI(double kI) {
        Preferences.getInstance().putDouble("Rotate kI", kI);
    }

    public static void setRotateD(double kD) {
        Preferences.getInstance().putDouble("Rotate kD", kD);
    }

    public static void setDriveDistP(double kP) {
        Preferences.getInstance().putDouble("DriveDist kP", kP);
    }

    public static void setDriveDistI(double kI) {
        Preferences.getInstance().putDouble("DriveDist kI", kI);
    }

    public static void setDriveDistD(double kD) {
        Preferences.getInstance().putDouble("DriveDist kD", kD);
    }
}