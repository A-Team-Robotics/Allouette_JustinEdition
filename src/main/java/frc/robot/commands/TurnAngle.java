/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;
import frc.robot.Robot;

public class TurnAngle extends CommandBase {
  /**
   * Creates a new TurnAngle.
   */
  private DriveTrain drive;
  public double targetAngle;

  public TurnAngle(double ta) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain);
    drive = DriveTrain.getDriveTrain();
    targetAngle = ta;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle = drive.getGyroInfo();
    if(Math.abs(angle) <= Math.abs(targetAngle / 2)) {
      if(targetAngle < 0) {
        drive.autoDrive(0, -Constants.AUTO_TURN_SPEED);
      }
      else {
        drive.autoDrive(0, Constants.AUTO_TURN_SPEED);
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
    double angle = drive.getGyroInfo();
    if(Math.abs(angle) >= Math.abs(targetAngle * 0.88) && Math.abs(angle) <= Math.abs(targetAngle)) {
      return true;
    }
    return false;
  }
}
