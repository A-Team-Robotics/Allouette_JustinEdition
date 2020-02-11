/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  private static Turret turret;
  private WPI_TalonSRX motor;
  /**
   * Creates a new Turret.
   */
  public Turret() {
    motor = new WPI_TalonSRX(Constants.MOTOR_TURRET_TURN_ID);
  }

  public static Turret getTurret() {
    if(turret == null) {
      turret = new Turret();
    }

    return turret;
  }

  public void turnTurret(XboxController controller) {
    if(controller.getYButton()) {
      motor.set(0.5);
    }
    else if(controller.getXButton()) {
      motor.set(-0.5);
    }
    else {
      motor.set(0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
