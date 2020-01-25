package frc.robot.commands;

import static frc.robot.Robot.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

public class StickDrive extends CommandBase {

	private final Drive m_Drive;

	static final double DEADZONE = .07;

	public class JoystickResponseCurve {
		double adjust;
		double power;
		double multiplier;
		double deadzone;

		public JoystickResponseCurve(final double adj, final double pow, final double mult, final double dead) {
			adjust = adj;
			power = pow;
			multiplier = mult;
			deadzone = dead;
		}

		// f(x) = multiplier * (adjust * x^power+(1-adjust)*x)

		public double transform(final double input) {
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

		public JoystickResponseCurveSet(final JoystickResponseCurve fwd, final JoystickResponseCurve strafe,
				final JoystickResponseCurve rotate) {
			this.fwd = fwd;
			this.strafe = strafe;
			this.rotate = rotate;
		}

		public double transformForward(final double input) {
			return fwd.transform(input);
		}

		public double transformStrafe(final double input) {
			return strafe.transform(input);
		}

		public double transformRotate(final double input) {
			return rotate.transform(input);
		}
	}

	JoystickResponseCurveSet linear = new JoystickResponseCurveSet(new JoystickResponseCurve(.00, 3, 1.0, DEADZONE),
			new JoystickResponseCurve(.00, 0, 0, DEADZONE), new JoystickResponseCurve(.00, 3, 1.0, DEADZONE));

	JoystickResponseCurveSet conservative = new JoystickResponseCurveSet(
			new JoystickResponseCurve(.40, 3, .60, DEADZONE), new JoystickResponseCurve(0, 0, 0, DEADZONE),
			new JoystickResponseCurve(.40, 3, .60, DEADZONE));

	JoystickResponseCurveSet aggressive = new JoystickResponseCurveSet(new JoystickResponseCurve(.40, 3, 1.0, DEADZONE),
			new JoystickResponseCurve(0, 0, 0, DEADZONE), new JoystickResponseCurve(.40, 3, 1.0, DEADZONE));

	public StickDrive(final Drive drive) {
		m_Drive = drive;
		addRequirements(drive);
	}

	@Override
	public void initialize() {
		SmartDashboard.putBoolean("Stick Init", true);
	}

	@Override
	public void execute() {

		final JoystickResponseCurveSet slow = conservative;
		final JoystickResponseCurveSet fast = aggressive;

		final Joystick driver = Robot.oi.driveStick;

		double vY = -driver.getY();
		double vRot = driver.getRawAxis(4);

		if (driver.getRawButton(1)) {
			vY = slow.transformForward(vY);
			vRot = slow.transformRotate(vRot);
			SmartDashboard.putBoolean("Slow Drive Profile", true);
		} else {
			vY = fast.transformForward(vY);
			vRot = fast.transformRotate(vRot);
			SmartDashboard.putBoolean("Slow Drive Profile", false);
		}

		if (Constants.robotDrive != null) {
			((Drive) drive).setDrive(vY, vRot);
		}

	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void end(final boolean interrupted) {
		((Drive) drive).setDrive(0, 0);
    }




}