// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Currency;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSystem extends SubsystemBase {
    private TalonFX topShooter;
    private TalonFX bottomShooter;

    private double Voltage = 5;
    private double topSpeed = 0;
    private double currentSpeed = 0;
    private boolean itemHad = false;
    private Debouncer debounce = new Debouncer(0.1);
  /** Creates a new ShooterSystem. */
  public ShooterSystem() {
    topShooter = new TalonFX(Constants.electronics.topShooterPort);
    bottomShooter = new TalonFX(Constants.electronics.bottomShooterPort);
  }
  //Calculates whether the note has left or not based on the debounce
  public boolean itemLeft() 
  {return debounce.calculate(!(topSpeed * 0.9 > currentSpeed && itemHad == true));}

  @Override
  public void periodic() {
    topShooter.setVoltage(Voltage);
    bottomShooter.setVoltage(-Voltage);
    //detects if the item has left the shooter
    if (Voltage != 0) {
        currentSpeed = topShooter.getVelocity().getValueAsDouble() + topShooter.getVelocity().getValueAsDouble() * 0.5;
        if (topSpeed < currentSpeed) {topSpeed = currentSpeed;}
        if (topSpeed * 0.9 > currentSpeed && itemHad == true) {
            itemHad = itemLeft();
            Voltage = 0;
        }
    }

    // This method will be called once per scheduler run
  }
  public void shoot(double Voltage) {
    this.Voltage = Voltage;
  }
}
