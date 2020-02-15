/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {
  private static ColorSensor colorSensor;
  private final Color kBlueTarget;
  private final Color kGreenTarget;
  private final Color kRedTarget;
  private final Color kYellowTarget;
  /**
   * Creates a new ColorSensor.
   */
  public ColorSensor() {
    kBlueTarget = ColorMatch.makeColor(Constants.RGB_BLUE[0], Constants.RGB_BLUE[1], Constants.RGB_BLUE[2]);
    kGreenTarget = ColorMatch.makeColor(Constants.RGB_GREEN[0], Constants.RGB_GREEN[1], Constants.RGB_GREEN[2]);
    kRedTarget = ColorMatch.makeColor(Constants.RGB_RED[0], Constants.RGB_RED[1], Constants.RGB_RED[2]);
    kYellowTarget = ColorMatch.makeColor(Constants.RGB_YELLOW[0], Constants.RGB_YELLOW[1], Constants.RGB_YELLOW[2]);
  }

  public static ColorSensor getColorSensor() {
    if(colorSensor == null) {
      colorSensor = new ColorSensor();
    }
    return colorSensor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
