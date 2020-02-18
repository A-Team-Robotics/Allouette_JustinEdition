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
  private double closeDistance;
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
    distance = camera.getObjectDistance();

    // Get the distance to cut power based on how far the robot must drive initially.
    if(distance <= distanceToObject + 3) {
      closeDistance = distanceToObject + 1.5;
    }
    else if(distance <= distanceToObject + 1) {
      closeDistance = distanceToObject + 0.5;
    }
    else {
      closeDistance = distanceToObject + 2;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = camera.getX();
    distance = camera.getObjectDistance();
    moveSpeed = Constants.LIMELIGHT_FOLLOW_SPEED;
    
    if(distance <= closeDistance) {
      moveSpeed = Constants.LIMELIGHT_SLOW_FOLLOW_SPEED;
    }
    else if(distance >= 10) {
      moveSpeed = Constants.LIMELIGHT_FAST_FOLLOW_SPEED;
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
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Follow ended.");
    Robot.isFollowing = false;
    drive.stop();
    if(!Robot.cancelSeekAndFollow) {
      System.out.println("Cancel false.");
      distance = camera.getObjectDistance();
      do {
        distance = camera.getObjectDistance();
        System.out.println("Distance is.... " + distance);
        x = camera.getX();

        if(x > 0) {
          drive.autoDrive(-Constants.LIMELIGHT_SLOW_FOLLOW_SPEED, Constants.LIMELIGHT_X_CLOSE_TURN_SPEED);
        }
        else if(x < 0) {
          drive.autoDrive(-Constants.LIMELIGHT_SLOW_FOLLOW_SPEED, -Constants.LIMELIGHT_X_CLOSE_TURN_SPEED);
        }
        else {
          drive.autoDrive(-Constants.LIMELIGHT_SLOW_FOLLOW_SPEED, 0);
        }

        if(Robot.cancelSeekAndFollow) {
          break;
        }
      } while(distance < distanceToObject);
    }
    drive.stop();
    System.out.println("All stop.");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(camera.getObjectDistance() <= distanceToObject) {
      return true;
    }

    if(camera.getArea() <= 0.38) {
      return true;
    }
    
    if(Robot.cancelSeekAndFollow) {
      return true;
    }
    return false;
  }
}