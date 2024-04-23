// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSystem extends SubsystemBase {
    private TalonFX topShooter;
    private TalonFX bottomShooter;

    private double Voltage;
  /** Creates a new ShooterSystem. */
  public ShooterSystem() {
    topShooter = new TalonFX(Constants.electronics.topShooterPort);
    bottomShooter = new TalonFX(Constants.electronics.bottomShooterPort);
  }
  @Override
  public void periodic() {
    topShooter.setVoltage(Voltage);
    bottomShooter.setVoltage(Voltage);
    // This method will be called once per scheduler run
  }
  public void shoot(double Voltage) {
    this.Voltage = Voltage;
  }
}
