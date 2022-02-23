// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
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
  final NeutralMode kBrakeDurNeutral = NeutralMode.Coast;
  int _loops = 0;

  WPI_TalonFX mytalon = new WPI_TalonFX(5, "rio");
  Joystick stickLeft = new Joystick(0);
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
    double appliedMotorOutput = mytalon.getMotorOutputPercent();
		double selSenPos = mytalon.getSelectedSensorPosition(0); /* position units */
		double selSenVel = mytalon.getSelectedSensorVelocity(0); /* position units per 100ms */

		/* scaling depending on what user wants */
		double pos_Rotations = (double) selSenPos / kUnitsPerRevolution;
		double vel_RotPerSec = (double) selSenVel / kUnitsPerRevolution * 10.0; /* scale per100ms to perSecond */
		double vel_RotPerMin = vel_RotPerSec * 60.0;

		/*
		 * Print to console. This is also a good oppurtunity to self-test/plot in Tuner
		 * to see how the values match.
		 * 
		 * Note these prints can cause "Loop time of 0.02s overrun" errors in the console.
		 * This is because prints are slow.
		 */
		if ((++_loops >= 10) && (stopped == false)) {
			_loops = 0;
			System.out.printf("Motor-out: %.2f | ", appliedMotorOutput);
			System.out.printf("Pos-units: %.2f | ", selSenPos);
			System.out.printf("Vel-unitsPer100ms: %.2f | ", selSenVel);
			System.out.printf("Pos-Rotations:%.3f | ", pos_Rotations);
			System.out.printf("Vel-RPS:%.1f | ", vel_RotPerSec);
			System.out.printf("Vel-RPM:%.1f | ", vel_RotPerMin);
			System.out.println();
		}

		/* set position to zero on button 1 */
		if (stickLeft.getRawButtonPressed(6)) {
			mytalon.setSelectedSensorPosition(0);
      System.out.println();
      System.out.println();
      System.out.println();
		}
	}

  @Override
  public void autonomousInit() {
    mytalon.setSelectedSensorPosition(0);
    mytalon.set(ControlMode.PercentOutput, 0.1);
  }

  @Override
  public void autonomousPeriodic() {
    double appliedMotorOutput = mytalon.getMotorOutputPercent();
		double selSenPos = mytalon.getSelectedSensorPosition(0); /* position units */
		double selSenVel = mytalon.getSelectedSensorVelocity(0); /* position units per 100ms */

		/* scaling depending on what user wants */
		double pos_Rotations = (double) selSenPos / kUnitsPerRevolution;
		double vel_RotPerSec = (double) selSenVel / kUnitsPerRevolution * 10; /* scale per100ms to perSecond */
		double vel_RotPerMin = vel_RotPerSec * 60.0;

		/*
		 * Print to console. This is also a good oppurtunity to self-test/plot in Tuner
		 * to see how the values match.
		 * 
		 * Note these prints can cause "Loop time of 0.02s overrun" errors in the console.
		 * This is because prints are slow.
		 */
		if (selSenPos >= 10000) {
			System.out.printf("Motor-out: %.2f | ", appliedMotorOutput);
			System.out.printf("Pos-units: %.2f | ", selSenPos);
			System.out.printf("Vel-unitsPer100ms: %.2f | ", selSenVel);
			System.out.printf("Pos-Rotations:%.3f | ", pos_Rotations);
			System.out.printf("Vel-RPS:%.1f | ", vel_RotPerSec);
			System.out.printf("Vel-RPM:%.1f | ", vel_RotPerMin);
			System.out.println();
      System.out.println("Motor stopped.");
      mytalon.set(ControlMode.PercentOutput, 0.0);
      mytalon.setSelectedSensorPosition(0);
		}

		/* set position to zero on button 1 */
	
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
  }
  if (stickLeft.getRawButtonPressed(2)) {
  speed -= 0.1;
    System.out.println("Button 2 pressed.");
  } 
  
  if (stickLeft.getRawButtonPressed(3)) {
    direction *= -1.0;
    System.out.println("Button 3 pressed.");
  } 

  if (stickLeft.getRawButtonPressed(4)) {
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
  
  if (stickLeft.getRawButtonPressed(5)) {
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