/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Robot;
import frc.robot.Constants;

public class DriveStraight extends CommandBase {
  /**
   * Creates a new DriveStraight.
   */
  private DriveTrain drive;
  private double distance;

  public DriveStraight(double distanceInInches) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain);
    drive = DriveTrain.getDriveTrain();
    distance = distanceInInches;
    initialize();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
    drive.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(drive.getDistance(false)) <= Math.abs(distance / 2)) {
      if(distance < 0) {
        if(distance <= 10) {
          drive.autoDrive(-Constants.AUTO_DRIVE_SLOW_SPEED, 0);
        }
        else {
          drive.autoDrive(-Constants.AUTO_DRIVE_SPEED, 0);
        }
        drive.autoDrive(-Constants.AUTO_DRIVE_SPEED, 0);
      }
      else {
        if(distance <= 10) {
          drive.autoDrive(Constants.AUTO_DRIVE_SLOW_SPEED, 0);
        }
        else {
          drive.autoDrive(Constants.AUTO_DRIVE_SPEED, 0);
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(drive.getDistance(false)) >= Math.abs(distance)) {
      return true;
    }
    return false;
  }
}
