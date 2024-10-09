// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
    // define every instance field we'll ever need. speed, pid controllers, voltage, motors itself, etc...
    double xSpeed;
    double zRotation;
    TalonFX leftMotor = new TalonFX(Constants.NumIdStorage.frontLeftDrive);
    TalonFX rightMotor = new TalonFX(Constants.NumIdStorage.frontRightDrive);
    DifferentialDrive myDriveah = new DifferentialDrive(rightMotor, leftMotor);
  /** Creates a new TankDrive. */
  public TankDrive() {
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
