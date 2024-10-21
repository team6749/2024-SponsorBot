// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class NumIdStorage{
   public static int frontLeftDrive = 1;
   public static int frontRightDrive = 2;
   public static int backLeftDrive = 3;
   public static int backRightDrive = 4;
  }
  public static class Electronics{
    public static int controller = 5;
    public static int lEncoder = 7;
    public static int rEncoder = 8; 
  }
  public static class PoiConstants{
    //what is life - Austin
  }
  public static class SwerveConstants {
    public static double gearRatio = 1000000000;
    public static double wheelDiameterCm = 15.2;
  }
  public static class JoystickConstants {
    public static final double deadZoneRange = 0.15;
    public static final double deadZoneRotation = 0.10;

    public static final double maxLinearSpeedms = 4.0;
    public static final double maxRotationalSpeedDegrees = 360;

    public static final double joystickLinearityAdjustment = 0.8;
}
}


//hi i like foodjrefbhuvb rvjn
