// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

//import frc.robot.subsystems.*;
//import java.time.Instant;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Joysticks
    private final Joystick robotController = new Joystick(0);

    // The robot's subsystems
    public final dumperSystem m_dumperSystem = new dumperSystem();
    public final intakeSystem m_intakeSystem = new intakeSystem();
    public final driveSystem m_driveSystem = new driveSystem();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems
    SmartDashboard.putData(m_dumperSystem);
    SmartDashboard.putData(m_intakeSystem);
    SmartDashboard.putData(m_driveSystem);


    // SmartDashboard Buttons
    SmartDashboard.putData("autonomousCommandGroup", new autonomousCommandGroup(m_intakeSystem, m_dumperSystem, m_driveSystem));
    SmartDashboard.putData("startIntake", new startIntake( m_intakeSystem ));
    SmartDashboard.putData("lowerDumper", new lowerDumper( m_dumperSystem ));
    SmartDashboard.putData("raiseDumper", new raiseDumper( m_dumperSystem ));
    SmartDashboard.putData("reverseIntakeDirection", new reverseIntakeDirection( m_intakeSystem ));
    SmartDashboard.putData("leaveTarmac", new leaveTarmac( m_driveSystem, 30 ));
    SmartDashboard.putData("printSensors", new printSensors( m_driveSystem ));
    SmartDashboard.putData("moveDumper", new moveDumper( m_dumperSystem ));
    SmartDashboard.putData("lowerIntake", new lowerIntake( m_intakeSystem ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
            // Set the default command for the drive system to driveCommand.
            // m_driveSystem.setDefaultCommand(new SYSTEM_NAME(m_SYSTEM_NAME));
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.addOption("autonomousCommandGroup", new autonomousCommandGroup(m_intakeSystem, m_dumperSystem, m_driveSystem));
    m_chooser.setDefaultOption("autonomousCommandGroup", new autonomousCommandGroup(m_intakeSystem, m_dumperSystem, m_driveSystem));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
    // Create some buttons
        final JoystickButton button8 = new JoystickButton(robotController, 8);        
        button8.whenHeld(new raiseDumper( m_dumperSystem ) ,true);
        button8.whenReleased(new lowerDumper( m_dumperSystem ) ,true);
        
        SmartDashboard.putData("button8",new raiseDumper( m_dumperSystem ) );
        SmartDashboard.putData("button8",new lowerDumper( m_dumperSystem ) );

        final JoystickButton button7 = new JoystickButton(robotController, 7);        
        button7.whenHeld(new startIntake( m_intakeSystem ) ,true);
        SmartDashboard.putData("button7",new startIntake( m_intakeSystem ) );

        final JoystickButton button6 = new JoystickButton(robotController, 6);        
        button6.whenHeld(new lowerIntake( m_intakeSystem ) ,true);
        SmartDashboard.putData("button6",new lowerIntake( m_intakeSystem ) );

        final JoystickButton button5 = new JoystickButton(robotController, 5);        
        button5.whenPressed(new InstantCommand( m_intakeSystem::changeIntakeDirection, m_intakeSystem ) ,true);
        SmartDashboard.putData("button5",new InstantCommand( m_intakeSystem::changeIntakeDirection, m_intakeSystem ) );

        final JoystickButton button9 = new JoystickButton(robotController, 9);        
        button9.whenPressed(new printSensors( m_driveSystem ) ,true);
        SmartDashboard.putData("button9",new printSensors( m_driveSystem ) );



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public Joystick getrobotController() {
        return robotController;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}

