// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDrive extends SubsystemBase {
    double xSpeed;
    double zRotation;
    WPI_TalonSRX flMotor = new WPI_TalonSRX(1);
    WPI_TalonSRX blMotor = new WPI_TalonSRX(2);
    WPI_TalonSRX frMotor = new WPI_TalonSRX(3);
    WPI_TalonSRX brMotor = new WPI_TalonSRX(4);
    XboxController m_controller = new XboxController(0);
    DifferentialDrive myDriveah = new DifferentialDrive(flMotor, frMotor);
  /** Creates a new TankDrive. */
  public TankDrive() {
    brMotor.follow(frMotor);
    blMotor.follow(flMotor);
  }

  @Override
  public void periodic() {
    myDriveah.arcadeDrive(-xSpeed, -zRotation);
    // This method will be called once per scheduler run
  }
  public void setxSpeed(double speed){
    this.xSpeed = speed;
  }
    public void setzRotation(double speed){
    this.zRotation = speed;
  }
}
