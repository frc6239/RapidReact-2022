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

//import frc.robot.commands.*;
//import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class dumperSystem extends SubsystemBase {

    private WPI_TalonFX dumperMotor;

    public dumperSystem() {
        dumperMotor = new WPI_TalonFX(6, "rio");
        TalonFXConfiguration configs = new TalonFXConfiguration();
        configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
        dumperMotor.configAllSettings(configs);
        dumperMotor.configFactoryDefault();
        dumperMotor.set(ControlMode.PercentOutput, 0.0);
        dumperMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 20);
        dumperMotor.setInverted(TalonFXInvertType.Clockwise);
        dumperMotor.setNeutralMode(NeutralMode.Brake);
        dumperMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 20);
        dumperMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 20);
        dumperMotor.configOpenloopRamp(0.5);
    }

    public boolean isDumperRaised() {
        return (dumperMotor.isFwdLimitSwitchClosed() == 1);
}

    public boolean isDumperLowered() {
        return (dumperMotor.isRevLimitSwitchClosed() == 1);
}

    public void raiseDumper() {
        dumperMotor.set(Constants.dumperConstants.raiseDumperSpeed);
    }

    public void lowerDumper() {
        dumperMotor.set(Constants.dumperConstants.lowerDumperSpeed);
    }

    public void stopDumperMotor() {
        dumperMotor.set(0.0);
    }

    public void lowerDumperAutonomous() {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
}

