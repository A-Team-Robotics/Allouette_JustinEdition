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

public class WaitToStop extends CommandBase {
  private DriveTrain drive;
  private double previousSensorPosition;
  private double sensorPosition;
  /**
   * Creates a new WaitToStop.
   */
  public WaitToStop() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive = DriveTrain.getDriveTrain();
    previousSensorPosition = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sensorPosition = drive.getDistance(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(previousSensorPosition == sensorPosition) {
      return true;
    }
    previousSensorPosition = sensorPosition;
    return false;
  }
}