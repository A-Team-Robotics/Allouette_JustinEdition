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
    closeDistance = distanceToObject + 1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = camera.getX();
    distance = camera.getObjectDistance();
    moveSpeed = Constants.LIMELIGHT_FOLLOW_SPEED;
    
    if(distance <= closeDistance) {
      moveSpeed *= 0.5 * (distance / (closeDistance + 0.5));
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
    drive.stop();
    distance = camera.getObjectDistance();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(camera.getObjectDistance() <= 4) {
      return true;
    }
    return false;
  }
}
