package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class TankModule implements Sendable{
    public String name;
    public TalonFX motor;
    public ChassisSpeeds motorSpeed;
    public Translation2d location;
    public final PIDController veloPID = new PIDController(0.5, 0, 0);
    // SimpleMotorFeedforward instance with example values for kS, kV, and kA
    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.1, 2.0, 0.3); // Example values
    //private SwerveModuleState m_targetstate = new SwerveModuleState();

    public TankModule(String name, int motorport, Translation2d locationFromCenter,boolean isInverted) {
        this.name = name;
        motor = new TalonFX(motorport);
        motor.setInverted(isInverted);
        motorSpeed = new ChassisSpeeds(0,motor.getVelocity().getValueAsDouble(),0);
        location = locationFromCenter;
        //veloPID.enableContinuousInput(0, 360);
        this.setModuleNeutralMode(NeutralMode.Brake); 
    }
    public void initSendable(SendableBuilder builder) {
        // TODO Auto-generated method stub
        builder.setSmartDashboardType("DifferentialModule");
        builder.addDoubleProperty("Speed", this::getForward, null);
    }
        public String getname() {
            return name;
        }

        public void periodic() {
            double actualVelocity = getModuleVelocityMs();
            double desiredVelocity = motorSpeed.vyMetersPerSecond;
        
            // Log actual velocity for debugging
            SmartDashboard.putNumber(name + " Actual Velocity (m/s)", actualVelocity);
            SmartDashboard.putNumber(name + " Desired Velocity (m/s)", desiredVelocity);
        
            // Adjust PID gains as necessary
            final double driveOutput = veloPID.calculate(actualVelocity, desiredVelocity);
        
            // If using feedforward, consider using a physics-based model or WPILib's SimpleMotorFeedforward
            final double driveFeedforward = feedforward.calculate(desiredVelocity);
        
            motor.setVoltage(driveOutput + driveFeedforward);
        }
        public void setModuleNeutralMode(NeutralMode neutralModeValue) {
        motor.setNeutralMode(NeutralModeValue.Brake);
    }
    public double getModuleVelocityMs() {
        double sensorVelocity = motor.getVelocity().getValueAsDouble(); // Returns units per 100ms
        double wheelCircumference = Math.PI * Constants.ChasisConstants.wheelDiameterCm / 100; // Convert cm to meters
        double velocityRps = (sensorVelocity * 10) / 2048; // Convert to rotations per second (assuming 2048 units per rotation)
        double velocityMs = (velocityRps * wheelCircumference) / Constants.ChasisConstants.gearRatio;
        return velocityMs;
    }
    public void setSubsystemState(ChassisSpeeds desiredSpeed) {
        motorSpeed = desiredSpeed;
      }
    public double getForward() {return motorSpeed.vxMetersPerSecond;}
    public void setForward() {this.motorSpeed.vyMetersPerSecond = motorSpeed.vyMetersPerSecond;}
}
