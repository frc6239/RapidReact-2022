// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
   /**
    * public static final class DriveConstants {
    *   public static final int kLeftMotor1Port = 0;
    *   public static final int kLeftMotor2Port = 1;
    *   public static final int kRightMotor1Port = 2;
    *   public static final int kRightMotor2Port = 3; 
    * }
    */ 
    public static final class DriveConstants {
        
        public static final int kLeftFrontMotorPort = 1;
        public static final int kLeftBackMotorPort = 2;
        public static final int kRightFrontMotorPort = 3;
        public static final int kRightBackMotorPort = 4;
    }

    // Constants.DriveConstants.kLeftFrontMotorPort
    // Constants.DumperMotorPort

    public static final class DumperConstants {
        public static final int kDumperMotorPort = 6;
    }

    public static final class IntakeConstants {
        public static final int kIntakeLowerMotorPort = 5;
        public static final int kIntakeWheelMotorPort = 7;
        public static final double lowerIntakeSpeed = 0.25;
        public static final double intakeWheelSpeed = 0.25;
        public static final double intakePositionLimit = 20.0;
    }

    // Joystick buttons for commands

    public static final class LeftJoystickConstants {
        public static final int kPickupCargo = 1;
        public static final int kChangeIntakeDirection = 2;
    }

    public static final class RightJoystickConstants {
        public static final int kMoveDumper = 1;
        public static final int kLowerIntake = 2;
    }
}