// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.*;

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
  boolean button1Pressed = false;
  boolean button2Pressed = false;
  boolean button3Pressed = false;
  boolean button4Pressed = false;
  double speed;
  double direction;
  boolean stopped = false;
  double lastSpeed;
  Faults _faults = new Faults();

  @Override
  public void robotInit() {
  
    speed = 0.2;
    direction = 1.0;
    lastSpeed = 0.0;
    mytalon.configFactoryDefault();

    mytalon.set(ControlMode.PercentOutput, 0);
}

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {

  button1Pressed = stickLeft.getRawButtonPressed(1);
  button2Pressed = stickLeft.getRawButtonPressed(2);
  button3Pressed = stickLeft.getRawButtonPressed(3);
  button4Pressed = stickLeft.getRawButtonPressed(4);
  double stick = stickLeft.getY();

  if (button1Pressed == true) {
    speed += 0.1;
    System.out.println("Button 1 pressed.");
  }
  if (button2Pressed == true) {
  speed -= 0.1;
    System.out.println("Button 2 pressed.");
  } 
  
  if (button3Pressed == true) {
    direction *= -1.0;
    System.out.println("Button 3 pressed.");
  } 

  if (button4Pressed == true) {
  if (stopped == false) {
    lastSpeed = speed;
    speed = 0.0;
    stopped = true;
  }

  else {
    speed = lastSpeed;
    lastSpeed = 0.0;
    stopped = false;
  }
    System.out.println("Button 4 pressed.");
  }
  
  if (speed < 0.0) {
    speed = 0.0;
  }
  if (speed > 1.0) {
  speed = 1.0;
  }
  mytalon.set(ControlMode.PercentOutput, direction*speed*stick);
  
  if (stickLeft.getRawButton(5)) {
    System.out.println("Sensor Vel:" + mytalon.getSelectedSensorVelocity());
    System.out.println("Sensor Pos:" + mytalon.getSelectedSensorPosition());
    System.out.println("Out %" + mytalon.getMotorOutputPercent());
    System.out.println("Out Of Phase:" + _faults.SensorOutOfPhase);
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