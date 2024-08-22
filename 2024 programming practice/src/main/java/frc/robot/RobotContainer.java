// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

<<<<<<< Updated upstream
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommand;
=======
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OperatorConstants;

import frc.robot.subsystems.ShooterSystem;
>>>>>>> Stashed changes
import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private TankDrive m_TankDrive = new TankDrive();


  // Replace with CommandPS4Controller or CommandJoystick if needed
<<<<<<< Updated upstream
  private final XboxController m_driverController = new XboxController(OperatorConstants.kDriverControllerPort);

=======
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);
  public TankDrive tankDrive = new TankDrive();
 
      private final ShooterSystem shooterSubsystem = new ShooterSystem();
  JoystickButton b = new JoystickButton(m_driverController, 2);
>>>>>>> Stashed changes

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    // set default to run every period(20ms)
    tankDrive.setDefaultCommand(new DriveCommand(tankDrive,m_driverController));
  }
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
<<<<<<< Updated upstream
=======
    // use on true or while true as conditional executions
    b.onTrue(shootCommand());
>>>>>>> Stashed changes
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    m_TankDrive.setDefaultCommand(new DriveCommand(m_TankDrive, m_driverController));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
<<<<<<< Updated upstream
  public Command getAutonomousCommand() {
    return Commands.waitSeconds(1);
=======
    public Command getAutonomousCommand() {
    // An example command will be run in autonomous
        return Commands.waitSeconds(1);
  }
  // define commands inline within the robot container
  public Command shootCommand() {
    return Commands.startEnd(
        () -> {
            shooterSubsystem.shoot(5);
        },
        () ->{

         shooterSubsystem.shoot(0.0);
  }, shooterSubsystem).withTimeout(0.3);
>>>>>>> Stashed changes
  }
}
