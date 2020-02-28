/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import java.text.DecimalFormat;

public class Camera extends SubsystemBase {
  private static Camera camera;
  private NetworkTable table;
  private NetworkTableEntry tx;
  private NetworkTableEntry ty;
  private NetworkTableEntry ta;
  private double x;
  private double y;
  private double area;
  private DecimalFormat df;
  /**
   * Creates a new Camera.
   */
  public Camera() {
    df = new DecimalFormat("####.##");
  }

  public static Camera getCamera() {
    if(camera == null) {
      camera = new Camera();
    }
    return camera;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getArea() {
    return area;
  }

  public double getObjectDistance() {
    /*
    if(area == 2.23) {
      distance = 92;
    }
    else {
      distance = 5;
    }
    */
    // return Double.parseDouble(df.format(Constants.K / Math.sqrt(getArea())));
    return Constants.K / Math.sqrt(getArea());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    if(table == null) {
      setTable();
    }
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
  
    //read values periodically
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Distance", getObjectDistance());
  }

  public void setTable() {
    try {
      table = NetworkTableInstance.getDefault().getTable("limelight");
    }
    catch(Exception e) {
      System.out.println("Unable to get limelight network table.");
    }
  }

  public void setPipeline(int pipelineNumber){
    if(table == null) {
      setTable();
    }
    table.getEntry("pipeline").setNumber(pipelineNumber);
  }

  public void turnOffLED() {
    if(table == null) {
      setTable();
    }
    table.getEntry("ledMode").forceSetNumber(1);
  }

  public void turnOnLED() {
    if(table == null) {
      setTable();
    }
    table.getEntry("ledMode").forceSetNumber(0); // Or use 3 to force turn on.
  }
}
