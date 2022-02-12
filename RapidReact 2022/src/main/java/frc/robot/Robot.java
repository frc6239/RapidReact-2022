// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private WPI_TalonFX  m_frontL = new WPI_TalonFX(1, "rio");
  private WPI_TalonFX  m_backL = new WPI_TalonFX(2, "rio");
  private WPI_TalonFX  m_frontR = new WPI_TalonFX(3, "rio");
  private WPI_TalonFX  m_backR = new WPI_TalonFX(4, "rio");

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

    m_frontL.set(ControlMode.PercentOutput, 0);
    m_backL.set(ControlMode.PercentOutput, 0);
    m_frontR.set(ControlMode.PercentOutput, 0);
    m_backR.set(ControlMode.PercentOutput, 0);

    m_backL.follow(m_frontL);
    m_backR.follow(m_frontR);

    m_frontR.setInverted(TalonFXInvertType.Clockwise);
    m_frontL.setInverted(TalonFXInvertType.CounterClockwise);
    
    m_backR.setInverted(InvertType.FollowMaster);
    m_backL.setInverted(InvertType.FollowMaster);

    m_myRobot = new DifferentialDrive(m_frontL, m_frontR);
    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
  }
}
