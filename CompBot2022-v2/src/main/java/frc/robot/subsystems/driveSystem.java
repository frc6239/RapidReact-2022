// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.RobotContainer;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class driveSystem extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonFX m_leftFront;
    private WPI_TalonFX m_leftBack;
    private WPI_TalonFX m_rightFront;
    private WPI_TalonFX m_rightBack;
    private DifferentialDrive differentialDrive1;
    private double joystickY;
    private double joystickZ;
    private double joystickYCubed;
    private double joystickZCubed;
    private Joystick joystick0;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public driveSystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        m_leftFront = new WPI_TalonFX(1, "rio");
        m_leftBack = new WPI_TalonFX(2, "rio");
        m_rightFront = new WPI_TalonFX(3, "rio");
        m_rightBack = new WPI_TalonFX(4, "rio");

        TalonFXConfiguration configs = new TalonFXConfiguration();
        configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
        m_leftFront.configAllSettings(configs);
        m_leftFront.configFactoryDefault();
       
        m_leftFront.configFactoryDefault();
        m_leftBack.configFactoryDefault();
        m_rightFront.configFactoryDefault();
        m_rightBack.configFactoryDefault();

        m_leftFront.set(ControlMode.PercentOutput, 0);
        m_leftBack.set(ControlMode.PercentOutput, 0);
        m_rightFront.set(ControlMode.PercentOutput, 0);
        m_rightBack.set(ControlMode.PercentOutput, 0);

        m_leftBack.follow(m_leftFront);
        m_rightBack.follow(m_rightFront);

        m_rightFront.setInverted(TalonFXInvertType.CounterClockwise);
        m_leftFront.setInverted(TalonFXInvertType.Clockwise);

        m_rightBack.setInverted(InvertType.FollowMaster);
        m_leftBack.setInverted(InvertType.FollowMaster);

        differentialDrive1 = new DifferentialDrive(m_leftFront, m_rightFront);
        addChild("Differential Drive 1",differentialDrive1);
        differentialDrive1.setSafetyEnabled(true);
        differentialDrive1.setExpiration(0.1);
        differentialDrive1.setMaxOutput(0.3);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        joystick0 = RobotContainer.getInstance().getrobotController();
        joystickY = joystick0.getY();
        joystickYCubed = Math.pow(joystickY, 3);
        joystickZ = joystick0.getZ();
        joystickZCubed = Math.pow(joystickY, 3);

        SmartDashboard.putNumber("Joystick Y Value", joystickY);
        SmartDashboard.putNumber("Joystick Z Value", joystickZ);

        SmartDashboard.putNumber("Joystick Y Value Cubed", joystickYCubed);
        SmartDashboard.putNumber("Joystick Z Value Cubed", joystickZCubed);

        differentialDrive1.arcadeDrive(joystickYCubed, joystickZCubed);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

