// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class TankDrive extends SubsystemBase {
    double xSpeed;
    double zRotation;
    TankModule LMotor;
    TankModule RMotor;
  /** Creates a new TankDrive. */
  public TankDrive(TankModule motorLeft, TankModule motorRight) {
    this.LMotor = motorLeft;
    this.RMotor = motorRight;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("left motor velocity", LMotor.getModuleVelocityMs());
    LMotor.periodic();
    RMotor.periodic();
    // This method will be called once per scheduler run
  }
  public void setYSpeed(ChassisSpeeds desiredSpeed){
    LMotor.setSubsystemState(desiredSpeed);
    RMotor.setSubsystemState(desiredSpeed);
  }
    public void setzRotation(double speed){
    this.zRotation = speed;
  }
  public double getChassisVelocityMs() {
    return (LMotor.getModuleVelocityMs() + RMotor.getModuleVelocityMs())/2;
  }

}
