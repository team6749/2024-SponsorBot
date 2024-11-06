package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;

public class TankModule implements Sendable{
    public String name;
    public  CANcoder encoder;
    public TalonFX motor;
    public Translation2d location;
    public double speedMetersPerSecond = motor.getVelocity().getValueAsDouble();
    public final PIDController veloPID = new PIDController(1, 0.1, 0.2);
    //private SwerveModuleState m_targetstate = new SwerveModuleState();

    public TankModule(String name, int encoderport, int motorport, Translation2d locationFromCenter) {
        this.name = name;
        encoder = new CANcoder(encoderport);
        motor = new TalonFX(motorport);
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
            motor.getVelocity().getValueAsDouble());
            final double driveFeedforward = (motor.getVelocity().getValueAsDouble() * 2.8); //don't ask why it is 2.8, I don't know either, probably tweakable
            motor.setVoltage(driveOutput + driveFeedforward);
        }
        public void setModuleNeutralMode(NeutralMode neutralModeValue) {
        motor.setNeutralMode(NeutralModeValue.Brake);
    }
    public double getModuleVelocityMs() {
        return motor.getVelocity().getValueAsDouble() / Constants.ChasisConstants.gearRatio * Constants.ChasisConstants.wheelDiameterCm;
    }
}
