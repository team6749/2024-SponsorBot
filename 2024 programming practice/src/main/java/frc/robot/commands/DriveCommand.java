// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.JoystickConstants;
import frc.robot.subsystems.TankDrive;

public class DriveCommand extends Command {
    TankDrive _TankDrive; //= new TankDrive(null, null);
    XboxController m_controller = new XboxController(Constants.Electronics.controller);
  /** Creates a new DriveCommand. */
  public DriveCommand(TankDrive subsystem, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    _TankDrive = subsystem;
    this.m_controller = controller;
    addRequirements(subsystem);
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}
  double yJoystickInput = m_controller.getLeftY();
  public double xSpeedms = joystickResponseCurve(yJoystickInput) * JoystickConstants.maxLinearSpeedms;

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  public double joystickResponseCurve(double input) {
    return (JoystickConstants.joystickLinearityAdjustment * (Math.pow(input, 3)))
            + ((1 - JoystickConstants.joystickLinearityAdjustment) * input);
}
public void deadzone() {
  if (yJoystickInput < JoystickConstants.deadZoneRange);
    yJoystickInput = 0;
}

}
