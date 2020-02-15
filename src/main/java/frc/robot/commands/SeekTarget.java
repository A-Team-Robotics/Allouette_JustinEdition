/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

public class SeekTarget extends CommandBase {
  private DriveTrain drive;
  private Camera camera;
  private XboxController controller;
  private int turn;
  /**
   * Creates a new SeekTarget.
   */
  public SeekTarget(int spinDirection) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.driveTrain, Robot.limelight);
    drive = DriveTrain.getDriveTrain();
    camera = Camera.getCamera();

    turn = spinDirection;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.autoDrive(0, Constants.LIMELIGHT_SEEK_SPEED * turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.isSeeking = false;
    System.out.println("Seek done.");
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(turn == 0) {
      return true; // No turning, so command is cancelled.
    }

    if(camera.getArea() != 0) {
      return true;
    }

    if(Robot.cancelSeekAndFollow) {
      return true;
    }
    return false;
  }
}
