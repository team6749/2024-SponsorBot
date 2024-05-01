// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
    double xSpeed;
    double zRotation;
    WPI_TalonSRX flMotor = new WPI_TalonSRX(Constants.NumIdStorage.frontLeftDrive);
    WPI_TalonSRX blMotor = new WPI_TalonSRX(Constants.NumIdStorage.frontRightDrive);
    WPI_TalonSRX frMotor = new WPI_TalonSRX(Constants.NumIdStorage.backLeftDrive);
    WPI_TalonSRX brMotor = new WPI_TalonSRX(Constants.NumIdStorage.backRightDrive);
    XboxController m_controller = new XboxController(0);
    DifferentialDrive myDriveah = new DifferentialDrive(flMotor, frMotor);
  // Creates a new TankDrive. 
  public TankDrive() {
    brMotor.follow(frMotor);
    blMotor.follow(flMotor);
    AutoBuilder.configureLTV(
            this::getPose, // Robot pose supplier
            this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
            this::getCurrentSpeeds, // Current ChassisSpeeds supplier
            this::drive, // Method that will drive the robot given ChassisSpeeds
            0.02, // Robot control loop period in seconds. Default is 0.02
            new ReplanningConfig(), // Default path replanning config. See the API for the options here
            () -> {
              // Boolean supplier that controls when the path will be mirrored for the red alliance
              // This will flip the path being followed to the red side of the field.
              // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

              var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
            },
            this // Reference to this subsystem to set requirements
    );
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
