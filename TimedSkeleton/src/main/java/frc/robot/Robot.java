// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  WPI_TalonFX mytalon = new WPI_TalonFX(5, "rio");
  Joystick stickLeft = new Joystick(0);
  boolean buttonPressed = false;

  @Override
  public void robotInit() {
  mytalon.configFactoryDefault();

  mytalon.set(ControlMode.PercentOutput, 0);
}

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    buttonPressed = stickLeft.getRawButtonPressed(1);

  double stick = stickLeft.getY();

if (buttonPressed = true) {
  mytalon.set(ControlMode.PercentOutput, 0.5*stick);
} else {
  mytalon.set(ControlMode.PercentOutput, 0.25*stick);
}

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
