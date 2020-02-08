/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Constants;
import frc.robot.commands.TurnAngle;

public class DriveTrain extends SubsystemBase {
  private WPI_TalonSRX motorLeft1;
  private WPI_TalonSRX motorLeft2;
  private WPI_TalonSRX motorRight1;
  private WPI_TalonSRX motorRight2;
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;
  private DifferentialDrive drive;
  private Gyro gyro;
  private double angle;
  private static DriveTrain robotDriveTrain;
  private TurnAngle turnAngle;

  public enum DriveMode {
    MANUAL,
    AUTO
  }
  /**
   * Creates a new DriveTrain.
   */
  private DriveTrain() {    
    motorLeft1 = new WPI_TalonSRX(Constants.MOTOR_LEFT_1_ID);
    motorLeft2 = new WPI_TalonSRX(Constants.MOTOR_LEFT_2_ID);
    motorRight1 = new WPI_TalonSRX(Constants.MOTOR_RIGHT_1_ID);
    motorRight2 = new WPI_TalonSRX(Constants.MOTOR_RIGHT_2_ID);

    leftMotors = new SpeedControllerGroup(motorLeft1, motorLeft2);
    rightMotors = new SpeedControllerGroup(motorRight1, motorRight2);

    drive = new DifferentialDrive(leftMotors, rightMotors);
    gyro = new ADXRS450_Gyro();

    resetEncoders();

    // Motor Configuration
    motorLeft1.setNeutralMode(NeutralMode.Brake);
    motorLeft2.setNeutralMode(NeutralMode.Brake);
    motorRight1.setNeutralMode(NeutralMode.Brake);
    motorRight2.setNeutralMode(NeutralMode.Brake);
  }

  public void resetGyro() {
    gyro.reset();
  }

  public static DriveTrain getDriveTrain(){
    if(robotDriveTrain == null) {
      
      robotDriveTrain = new DriveTrain();
    } 
       return robotDriveTrain;
  }

  public void autoDrive(double speed, double desiredAngle) {
    drive.arcadeDrive(speed, desiredAngle);
  }

  public void manualDrive(XboxController controller) {
    drive.arcadeDrive(controller.getY() * -1, controller.getX());
  }

  public double getVelocity() {
    return motorLeft1.getSelectedSensorVelocity();
  }

  public void stop() {
    leftMotors.set(-1);
    rightMotors.set(1);

    //Timer.delay(Constants.MOTOR_DELAY_STOP);
    leftMotors.stopMotor();
    rightMotors.stopMotor();

    Timer.delay(Constants.AUTO_STOP_DELAY_SECONDS);
    resetGyro();
    resetEncoders();
  }

  public void init() {
    drive.setSafetyEnabled(false);
    leftMotors.setInverted(Constants.LEFT_MOTORS_INVERTED);
    rightMotors.setInverted(Constants.RIGHT_MOTORS_INVERTED);
    gyro.reset();
    gyro.calibrate();
    angle = 0;
  }

  public double getGyroInfo() {
    return gyro.getAngle();
  }

  public void resetEncoders() {
    motorLeft1.setSelectedSensorPosition(0);
    motorRight1.setSelectedSensorPosition(0);
  }
  
  /** Returns distance travelled in inches. */
  public double getDistance(boolean shouldResetEncoders) {
    /*
    if(shouldResetEncoders) {
      resetEncoders();
    }
    */
    /*
    System.out.println("Left motor position - " + Math.abs(motorLeft1.getSelectedSensorPosition()));
    System.out.println("Right motor position - " + Math.abs(motorRight1.getSelectedSensorPosition()));
    System.out.println("Average position - " + (Math.abs(motorLeft1.getSelectedSensorPosition() + Math.abs(motorRight1.getSelectedSensorPosition()) / 2)));
    */
    double averageSensorReading = ((Math.abs(motorLeft1.getSelectedSensorPosition()) + Math.abs(motorRight1.getSelectedSensorPosition())) / 2);
    return (averageSensorReading * 12) / Constants.ENCODER_VALUE_PER_FOOT;
  }
}