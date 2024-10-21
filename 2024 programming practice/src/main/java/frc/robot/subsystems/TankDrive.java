// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class TankDrive extends SubsystemBase {
    double xSpeed;
    double zRotation;
    TankModule LMotor = new TankModule(getName(), 0, 0, null);
    TankModule RMotor = new TankModule(getName(), 0, 0, null);
  /** Creates a new TankDrive. */
  public TankDrive(TankModule motorLeft, TankModule motorRight) {
    this.LMotor = motorLeft;
    this.RMotor = motorRight;
  }

  @Override
  public void periodic() {
    
    // This method will be called once per scheduler run
  }
  public void setxSpeed(double speed){
    this.xSpeed = speed;
  }
    public void setzRotation(double speed){
    this.zRotation = speed;
  }
}
