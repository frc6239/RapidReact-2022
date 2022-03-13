package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.driveSystem;
import frc.robot.subsystems.dumperSystem;
import frc.robot.subsystems.intakeSystem;

public class autonomousCommandGroup extends SequentialCommandGroup {
    public autonomousCommandGroup(driveSystem drive, dumperSystem dumper, intakeSystem intake) {
        addCommands(
            //new lowerIntakeCommand(intake),
            //new raiseDumperCommand(dumper),
            //new lowerDumperCommand(dumper),
            new AutonomousCommand(drive)
        );
    }
}
