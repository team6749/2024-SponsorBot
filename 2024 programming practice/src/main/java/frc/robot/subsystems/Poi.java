package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class Poi extends Pose2d{
    //poi = position of interest
    //define 
    public Pose2d target;
    double meterTolerance;
    double angleTolerance;
    public String name;
    public Poi(String name, double toleranceMeter, double toleranceAngle, Translation2d translation, Rotation2d rotation) {
        super(translation, rotation);
        this.name = name;
        meterTolerance = toleranceMeter;
        angleTolerance = toleranceAngle;
        target = new Pose2d(translation, rotation);
    }

    public boolean withinMeterTolerance(Pose2d otherPose) {
        // check to see whether we're close enough by checking our target position relative to another pose. 
        Pose2d pose = target.relativeTo(otherPose);
        // locate us then check to see whether our position is less than the meter tolerance.
        return pose.getTranslation().getNorm() < meterTolerance;
        // (calibrate pose to target, measure distance to target and see if less)
    }

    public boolean withinAngleTolerance(Pose2d otherPose) {
        // check to see whether we're close enough by checking our target position relative to another pose. 
        Pose2d pose = target.relativeTo(otherPose);
        // locate us then check to see whether our position is less than the angle tolerance. We also take the absolute value to avoid negative values. 
        return Math.abs(pose.getRotation().getDegrees()) < angleTolerance;
        // (calibrate to target, measure angle to see if off)
    }

     /**
     * @param otherPose the pose to compare to the POI pose
     * @return True if withinMetersTolerance AND withinDegreesTolerance are True
     */

    public boolean withinTolerance(Pose2d otherPose) {
        return withinAngleTolerance(otherPose) && withinMeterTolerance(otherPose);
    }
}
