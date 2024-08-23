// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.TankDrive;

public class DriveCommand extends Command {
    XboxController _Controller = new XboxController(Constants.OperatorConstants.kDriverControllerPort);
    // define the subsystems we use, and controllers.
    XboxController Controller = new XboxController(Constants.OperatorConstants.kDriverControllerPort);
    TankDrive _TankDrive = new TankDrive();
    XboxController xbox = new XboxController(2);
  /** Creates a new DriveCommand. */
  public DriveCommand(TankDrive tankDrive, XboxController Controller) {
    // Constructor, defines the arguments, connects subsystems with inputs.
    _TankDrive = tankDrive;
    this.xbox = Controller;
    addRequirements(tankDrive);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.p;p

  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
if (Math.abs(Controller.getLeftY()) > 0.1){
    _TankDrive.setxSpeed(exponentialDrive(Controller.getLeftY()));
}
if (Math.abs(Controller.getRightX()) > 0.1) {
    _TankDrive.setzRotation(exponentialDrive(Controller.getRightX()));
}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  //Adds an exponential growth to the controller movement to prevent jumpiness
  public double exponentialDrive(double input) {
    return (Constants.Joystick.CurveConst * Math.pow(input,3) + (1-Constants.Joystick.CurveConst) * input );
  }
}
