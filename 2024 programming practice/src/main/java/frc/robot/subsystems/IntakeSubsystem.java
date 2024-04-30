// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.electronics;
import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
@SuppressWarnings("unused")
public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
    TalonFX topIntake;
    TalonFX bottomIntake;
    TalonFX indexMotor;
    double voltage = 2;
    double topModifier = 1.5;
    double indexVoltage = 2;
    double delay;
    Debouncer debounce = new Debouncer(delay);
    DigitalInput beamSensor = new DigitalInput(Constants.electronics.SensorPort);
  public IntakeSubsystem() {
    topIntake = new TalonFX(Constants.electronics.topIntake);
    bottomIntake = new TalonFX(Constants.electronics.bottomIntake);
    indexMotor = new TalonFX(Constants.electronics.motorIntake);
  }
  public void stopIntake() {
    voltage = 0;
  }
  public void stopIndex() {
    indexVoltage = 0;
  }
  public boolean isBeamBreakTriggered() {
    return !beamSensor.get();
  }
  public boolean isIndex(){
    return debounce.calculate(isBeamBreakTriggered());
  }



  @Override

  public void periodic() {
    // This method will be called once per scheduler run
    bottomIntake.setVoltage(voltage * topModifier);
    topIntake.setVoltage(voltage);
    indexMotor.setVoltage(indexVoltage);
  }
  public Command intake() {
    return Commands.runEnd(
        ()-> {
        bottomIntake.setVoltage(voltage * topModifier);
        topIntake.setVoltage(voltage);
        indexMotor.setVoltage(indexVoltage);
        },
        () -> {
            stopIntake();
            stopIndex();
        },this
    ); 

    }
  }
