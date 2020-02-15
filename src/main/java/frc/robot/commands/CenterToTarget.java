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

public class CenterToTarget extends CommandBase {
  private DriveTrain drive;
  private Camera camera;
  private double x;
  /**
   * Creates a new CenterToTarget.
   */
  public CenterToTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain, Robot.limelight);
    drive = DriveTrain.getDriveTrain();
    camera = Camera.getCamera();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x = camera.getX();
    if(x > 0) {
      drive.autoDrive(0, Constants.LIMELIGHT_X_CENTER_SPEED);
    }
    else if(x < 0) {
      drive.autoDrive(0, -Constants.LIMELIGHT_X_CENTER_SPEED);
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
    if(x >= -Constants.LIMELIGHT_X_FORGIVENESS && x <= Constants.LIMELIGHT_X_FORGIVENESS) {
      return true;
    }

    if(Robot.cancelSeekAndFollow) {
      return true;
    }
    return false;
  }
}
