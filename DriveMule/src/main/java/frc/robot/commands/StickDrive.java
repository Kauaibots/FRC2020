package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drive;



public class StickDrive extends Command {

    static final double DEADZONE = .07;

	public class JoystickResponseCurve {
		double adjust;
		double power;
		double multiplier;
		double deadzone;

		public JoystickResponseCurve(double adj, double pow, double mult, double dead) {
			adjust = adj;
			power = pow;
			multiplier = mult;
			deadzone = dead;
		}

		// f(x) = multiplier * (adjust * x^power+(1-adjust)*x)

		public double transform(double input) {
			double output = 0.0;
			if ((input > deadzone) || (input < (-1 * deadzone))) {
				output = multiplier * (adjust * Math.pow(input, power) + (1 - adjust) * input);
			}
			return output;
		}
	}

    public class JoystickResponseCurveSet {
		JoystickResponseCurve fwd;
		JoystickResponseCurve strafe;
		JoystickResponseCurve rotate;

		public JoystickResponseCurveSet(JoystickResponseCurve fwd, JoystickResponseCurve strafe,
				JoystickResponseCurve rotate) {
			this.fwd = fwd;
			this.strafe = strafe;
			this.rotate = rotate;
		}

		public double transformForward(double input) {
			return fwd.transform(input);
		}

		public double transformStrafe(double input) {
			return strafe.transform(input);
		}

		public double transformRotate(double input) {
			return rotate.transform(input);
		}
	}

	JoystickResponseCurveSet linear = new JoystickResponseCurveSet(
			new JoystickResponseCurve(.00, 3, 1.0, DEADZONE),
			new JoystickResponseCurve(.00, 0, 0, DEADZONE), 
			new JoystickResponseCurve(.00, 3, 1.0, DEADZONE));

	JoystickResponseCurveSet conservative = new JoystickResponseCurveSet(
			new JoystickResponseCurve(.40, 3, .60, DEADZONE), 
			new JoystickResponseCurve(0, 0, 0, DEADZONE),
			new JoystickResponseCurve(.40, 3, .60, DEADZONE));

	JoystickResponseCurveSet aggressive = new JoystickResponseCurveSet(
			new JoystickResponseCurve(.40, 3, 1.0, DEADZONE),
			new JoystickResponseCurve(0, 0, 0, DEADZONE), 
			new JoystickResponseCurve(.40, 3, 1.0, DEADZONE));


    public StickDrive() {
        requires(Robot.drive);
    }


    @Override
    protected void initialize() {
        SmartDashboard.putBoolean("Stick Init", true);
    }

    @Override
    protected void execute() {

        JoystickResponseCurveSet slow = conservative;
		JoystickResponseCurveSet fast = aggressive;
        
        Joystick driver = Robot.oi.driveStick;

        double vY = -driver.getY();
        double vRot = driver.getRawAxis(4);

        if (driver.getRawButton(1)) {
            vY = slow.transformForward(vY);
            vRot = slow.transformRotate(vRot);
            SmartDashboard.putBoolean("Slow Drive Profile", true);
        }
        else {
            vY = fast.transformForward(vY);
            vRot = fast.transformRotate(vRot);
            SmartDashboard.putBoolean("Slow Drive Profile", false);
        }

        if (RobotMap.robotDrive != null) {
            Robot.drive.setDrive(vY, vRot);
        }

    }


    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drive.setDrive(0, 0);
    }

    @Override
    protected void interrupted() {
        Robot.drive.setDrive(0, 0);
    }

}