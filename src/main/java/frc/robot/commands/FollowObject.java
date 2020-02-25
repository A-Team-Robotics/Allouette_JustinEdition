/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Camera;
import frc.robot.Constants;
import frc.robot.Robot;

public class FollowObject extends CommandBase {
  private DriveTrain drive;
  private Camera camera;
  private double x;
  private double distance;
  private double distanceToObject;
  private double moveSpeed;
  private double close;
  private boolean driveForward;
  private int count;
  /**
   * Creates a new FollowObject.
   */
  public FollowObject(double distanceToObjectInFeet) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain, Robot.limelight);
    drive = DriveTrain.getDriveTrain();
    camera = Camera.getCamera();
    distanceToObject = distanceToObjectInFeet;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    count = 0;
    distance = camera.getObjectDistance();

    // Get the distance to cut power based on how far the robot must drive initially.
    if(distance > distanceToObject) {
      driveForward = true;
    }
    else if(distance < distanceToObject) {
      driveForward = false;
    }

    if(distance >= (distanceToObject - 3) && distance <= (distanceToObject + 3)) {
      close = 1.5;
    }
    else if(distance >= (distanceToObject - 1) && distance <= (distanceToObject + 1)) {
      close = 0.5;
    }
    else {
      close = 2;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = camera.getX();
    distance = camera.getObjectDistance();
    moveSpeed = Constants.LIMELIGHT_FOLLOW_SPEED;
    
    if(distance >= (distanceToObject - close) && distance <= (distanceToObject + close)) {
      moveSpeed = Constants.LIMELIGHT_SLOW_FOLLOW_SPEED;
    }

    if(distance >= (distanceToObject - 0.09) && distance <= (distanceToObject + 0.09)) {
      moveSpeed = Constants.LIMELIGHT_SLOTH_FOLLOW_SPEED;
    }

    if(distance > distanceToObject) {
      driveForward = true;
    }
    else if(distance < distanceToObject) {
      driveForward = false;
    }

    if(!driveForward) {
      moveSpeed *= -1;
    }

    if(x > 0) {
      if(x <= Constants.LIMELIGHT_X_CLOSE) {
        drive.autoDrive(moveSpeed, Constants.LIMELIGHT_X_CLOSE_TURN_SPEED);
      }
      else {
        drive.autoDrive(moveSpeed, Constants.AUTO_TURN_SPEED);
      }
    }
    else if(x < 0) {
      if(Math.abs(x) <= Constants.LIMELIGHT_X_CLOSE) {
        drive.autoDrive(moveSpeed, -Constants.LIMELIGHT_X_CLOSE_TURN_SPEED);
      }
      else {
        drive.autoDrive(moveSpeed, -Constants.AUTO_TURN_SPEED);
      }
    }
    else {
      drive.autoDrive(moveSpeed, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.isFollowing = false;
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(camera.getObjectDistance() >= (distanceToObject - Constants.LIMELIGHT_DISTANCE_ACCEPTABLE) && camera.getObjectDistance() <= (distanceToObject + Constants.LIMELIGHT_DISTANCE_ACCEPTABLE)) {
      count++;
      if(count >= 3) {
        return true;
      }
    }
    else {
      count = 0;
    }

    if(camera.getArea() <= Constants.LIMELIGHT_MINIMUM_VIEWABLE_AREA) {
      return true;
    }
    
    if(Robot.cancelSeekAndFollow) {
      return true;
    }
    return false;
  }
}