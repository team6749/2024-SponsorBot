package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;

public class TankModule implements Sendable{
    public String name;
    public TalonFX motor;
    public ChassisSpeeds motorSpeed;
    public Translation2d location;
    public final PIDController veloPID = new PIDController(1, 0.1, 0.2);
    //private SwerveModuleState m_targetstate = new SwerveModuleState();

    public TankModule(String name, int motorport, Translation2d locationFromCenter) {
        this.name = name;
        motor = new TalonFX(motorport);
        motorSpeed = new ChassisSpeeds(motor.getVelocity().getValueAsDouble(),0,0);
        location = locationFromCenter;
        veloPID.enableContinuousInput(0, 360);
        this.setModuleNeutralMode(NeutralMode.Brake); 
    }
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initSendable'");
    }
        public String getname() {
            return name;
        }

        public void periodic() {
            final double driveOutput = veloPID.calculate(getModuleVelocityMs(),
            motorSpeed.vyMetersPerSecond);
            final double driveFeedforward = (motorSpeed.vyMetersPerSecond * 2.8); //don't ask why it is 2.8, I don't know either, probably tweakable
            motor.setVoltage(driveOutput + driveFeedforward);
        }
        public void setModuleNeutralMode(NeutralMode neutralModeValue) {
        motor.setNeutralMode(NeutralModeValue.Brake);
    }
    public double getModuleVelocityMs() {
        return motorSpeed.vyMetersPerSecond / Constants.ChasisConstants.gearRatio * Constants.ChasisConstants.wheelDiameterCm;
    }
    public void setSubsystemState(ChassisSpeeds desiredSpeed) {
        motorSpeed = desiredSpeed;
      }
}
