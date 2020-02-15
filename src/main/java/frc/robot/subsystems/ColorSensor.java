/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {
  private static ColorSensor colorSensor;
  private ColorSensorV3 m_colorSensor;
  private ColorMatch m_colorMatcher;
  private static I2C.Port i2cPort;
  private final Color kBlueTarget;
  private final Color kGreenTarget;
  private final Color kRedTarget;
  private final Color kYellowTarget;
  /**
   * Creates a new ColorSensor.
   */
  public ColorSensor() {
    i2cPort = I2C.Port.kOnboard;
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    kBlueTarget = ColorMatch.makeColor(Constants.RGB_BLUE[0], Constants.RGB_BLUE[1], Constants.RGB_BLUE[2]);
    kGreenTarget = ColorMatch.makeColor(Constants.RGB_GREEN[0], Constants.RGB_GREEN[1], Constants.RGB_GREEN[2]);
    kRedTarget = ColorMatch.makeColor(Constants.RGB_RED[0], Constants.RGB_RED[1], Constants.RGB_RED[2]);
    kYellowTarget = ColorMatch.makeColor(Constants.RGB_YELLOW[0], Constants.RGB_YELLOW[1], Constants.RGB_YELLOW[2]);

    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
  }

  public static ColorSensor getColorSensor() {
    if(colorSensor == null) {
      colorSensor = new ColorSensor();
    }
    return colorSensor;
  }

  public String getMatchedColor() {
    Color colorRead = m_colorSensor.getColor();
    m_colorMatcher.setConfidenceThreshold(0);
    ColorMatchResult match = m_colorMatcher.matchClosestColor(colorRead);

    // System.out.println("Color: " + colorRead.red + ", " + colorRead.green + ", " + colorRead.blue + ", Distance: " + m_colorSensor.getProximity());

    if(m_colorSensor.getProximity() >= Constants.COLOR_CLOSEST_PROXIMITY) {
      if(match.color == kBlueTarget) {
        return Constants.COLOR_BLUE;
      } else if(match.color == kRedTarget) {
        return Constants.COLOR_RED;
      } else if(match.color == kGreenTarget) {
        return Constants.COLOR_GREEN;
      } else if(match.color == kYellowTarget) {
        return Constants.COLOR_YELLOW;
      } else {
        return Constants.COLOR_UNKNOWN;
      }
    }
    else {
      return Constants.COLOR_NODETECT;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putString("Detected_Color", getMatchedColor());
  }
}
