/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private static Turret turret;
  private WPI_TalonSRX motor;
  private DigitalInput leftTurretSwitch;
  private DigitalInput rightTurretSwitch;
  
  /**
   * Creates a new Turret.
   */
  public Turret() {
    motor = new WPI_TalonSRX(Constants.MOTOR_TURRET_TURN_ID);
    leftTurretSwitch = new DigitalInput(Constants.TURRET_LEFT_LIMIT);
    rightTurretSwitch = new DigitalInput(Constants.TURRET_RIGHT_LIMIT);
  }

  public static Turret getTurret() {
    if(turret == null) {
      turret = new Turret();
    }

    return turret;
  }

  public void turn(double speed) {
    if(speed > 0 && !getRightLimitSwitch()) {
      stop();
    }
    else if(speed < 0 && !getLeftLimitSwitch()) {
      stop();
    }
    else {
      motor.set(speed);
    }
  }

  public void center() {
    int middle = (int) (Constants.TURRET_SPAN / 2) - 55;
    int position = motor.getSelectedSensorPosition();

    if(position > middle) {
      do {
        turn(-Constants.TURRET_SPIN_SPEED / 2);
      }
      while(motor.getSelectedSensorPosition() > middle);
    }
    else if(position < middle) {
      do {
        turn(Constants.TURRET_SPIN_SPEED / 2);
      }
      while(motor.getSelectedSensorPosition() < middle);
    }
  }

  public void resetEncoder() {
    motor.setSelectedSensorPosition(0);
  }

  public void turnTurret(XboxController controller) {
    if(controller.getYButton() && getRightLimitSwitch()) {
      turn(Constants.TURRET_SPIN_SPEED);
    }
    else if(controller.getXButton() && getLeftLimitSwitch()) {
      turn(-Constants.TURRET_SPIN_SPEED);
    }
    else {
      stop();
    }
  }

  public void stop() {
    motor.set(0);
  }

  public boolean getLeftLimitSwitch() {
    return leftTurretSwitch.get();
  }

  public boolean getRightLimitSwitch() {
    return rightTurretSwitch.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
