// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
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
  final int kUnitsPerRevolution = 2048;
  final TalonFXInvertType kInvertType = TalonFXInvertType.Clockwise;
  final NeutralMode kBrakeDurNeutral = NeutralMode.Brake;
  // Motor 6 is the intake. 
  WPI_TalonFX mytalon = new WPI_TalonFX(6, "rio");
  Joystick stickLeft = new Joystick(0);
  double speed;
  double direction;
  double downMultiplier;
  boolean stopped = false;
  double lastSpeed;
  Faults _faults = new Faults();

  @Override
  public void robotInit() {
  
    speed = 0.10;
    direction = 1.0;
    lastSpeed = 0.0;
    downMultiplier = speed/3;
    TalonFXConfiguration configs = new TalonFXConfiguration();
    configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    mytalon.configAllSettings(configs);
    mytalon.configFactoryDefault();
    mytalon.set(ControlMode.PercentOutput, 0);
    mytalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 20);
    mytalon.setInverted(kInvertType);
    mytalon.setNeutralMode(kBrakeDurNeutral);
}

  @Override
  public void robotPeriodic() {

		/*
		 * Print to console. This is also a good oppurtunity to self-test/plot in Tuner
		 * to see how the values match.
		 * 
		 * Note these prints can cause "Loop time of 0.02s overrun" errors in the console.
		 * This is because prints are slow.
		 */

		/* set position to zero on button 1 */
	}

  // hello you found a secret :)
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

  double stick = stickLeft.getY();

  if (stickLeft.getRawButtonPressed(1)) {
    speed += 0.1;
    System.out.println("Button 1 pressed.");
    System.out.println("Speed = " + speed);
  }

  if (stickLeft.getRawButtonPressed(2)) {
  speed -= 0.1;
    System.out.println("Button 2 pressed.");
    System.out.println("Speed = " + speed);
  } 
  
  if (stickLeft.getRawButtonPressed(3)) {
    direction *= -1.0;
    System.out.println("Button 3 pressed.");
    System.out.println("Changed direction.");
  } 

  if (stickLeft.getRawButtonPressed(4)) {
    System.out.println("Button 4 pressed.");

    if (stopped == false) {
      lastSpeed = speed;
      speed = 0.0;
      stopped = true;
      System.out.println("Motor stopped.");
    }

    else {
      speed = lastSpeed;
      lastSpeed = 0.0;
      stopped = false;
      System.out.println("Motor started.");
    }
  }
  
  if (stickLeft.getRawButtonPressed(14)) {
    System.out.println("Congrats, you found a secret :)");
  }

  if (speed < 0.0) {
    speed = 0.0;
  }

  if (speed > 1.0) {
  speed = 1.0;
  }

  // if (stick > 0.0) {
    // mytalon.set(ControlMode.PercentOutput, downMultiplier*direction*speed*stick);
  // } else {
    mytalon.set(ControlMode.PercentOutput, direction*speed*stick);
  // }
  
  if (stickLeft.getRawButtonPressed(5)) {
    System.out.println("Sensor Vel: " + mytalon.getSelectedSensorVelocity());
    System.out.println("Sensor Pos: " + mytalon.getSelectedSensorPosition());
    System.out.println("Out %: " + mytalon.getMotorOutputPercent());
    System.out.println("Out Of Phase: " + _faults.SensorOutOfPhase);
  }
  
  if (stickLeft.getRawButtonPressed(6)) {
    mytalon.setSelectedSensorPosition(0);
    System.out.println("Sensor value reset.");
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