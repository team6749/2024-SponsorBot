package frc.robot.subsystems;

import java.time.Period;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;

public class TankModule implements Sendable{
    public String name;
    public  CANcoder encoder;
    public  WPI_TalonSRX motor;
    public TalonFX m;
    public Translation2d location;
    public final PIDController veloPID = new PIDController(0, 0, 0);

    public TankModule(String name, int encoderport, int motorport, Translation2d locationFromCenter) {
        this.name = name;
        encoder = new CANcoder(encoderport);
        motor = new WPI_TalonSRX(motorport);
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
            state.speedMetersPerSecond);
        }
        public void setModuleNeutralMode(NeutralMode neutralModeValue) {
        motor.setNeutralMode(neutralModeValue);
    }
}
