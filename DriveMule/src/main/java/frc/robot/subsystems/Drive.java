package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drive extends SubsystemBase {

    private final DifferentialDrive robotDrive = Constants.robotDrive;

    public enum GetEncoder { LEFT, RIGHT, AVERAGE, RAWLEFT, RAWRIGHT };


    public Drive() {
        
        

    }

   
    

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Encoder", getEncoder(GetEncoder.LEFT));

    }


    public void setDrive(double vY, double vRot) {
        robotDrive.arcadeDrive(vY, vRot);
    }

    public double getEncoder(GetEncoder encoder) {
        switch (encoder) {
        case LEFT:
            return Constants.leftEncoder.getPosition();
        }

        return 0;
    }


}

