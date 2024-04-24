// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDrive;

public class DriveCommand extends Command {
    XboxController _Controller;
    TankDrive _TankDrive = new TankDrive();
  /** Creates a new DriveCommand. */
  public DriveCommand(TankDrive tankDrive, XboxController controller) {
    _TankDrive = tankDrive;
    _Controller = controller;
    addRequirements(tankDrive);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    _TankDrive.setxSpeed(_Controller.getLeftY());
    _TankDrive.setzRotation(_Controller.getRightX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
