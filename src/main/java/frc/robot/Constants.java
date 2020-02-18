/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Motors
    public static final int MOTOR_LEFT_1_ID = 2;
    public static final int MOTOR_LEFT_2_ID = 4;
    public static final int MOTOR_RIGHT_1_ID = 1;
    public static final int MOTOR_RIGHT_2_ID = 3;
    public static final int MOTOR_TURRET_TURN_ID = 10;

    // Turret
    public static final double TURRET_SPIN_SPEED = 0.6;
    public static final int TURRET_LEFT_LIMIT = 6;
    public static final int TURRET_RIGHT_LIMIT = 4;
    public static final double TURRET_AIM_SPEED = 0.5;
    public static final double TURRET_AIM_SPEED_SLOW = 0.15;
    public static final double TURRET_AIM_CLOSE = 7.0;
    public static final double TURRET_SPAN = -4422 + 3348;
    public static double lastx;

    // Speed controller groups.
    public static final boolean LEFT_MOTORS_INVERTED = false;
    public static final boolean RIGHT_MOTORS_INVERTED = false;

    // Color Sensor.
    public static final double[] RGB_RED = {0.561, 0.232, 0.114};
    public static final double[] RGB_YELLOW = {0.361, 0.524, 0.113};
    public static final double[] RGB_GREEN = {0.197, 0.561, 0.240};
    public static final double[] RGB_BLUE = {0.143, 0.427, 0.429};
    public static final String COLOR_RED = "Red";
    public static final String COLOR_YELLOW = "Yellow";
    public static final String COLOR_GREEN = "Green";
    public static final String COLOR_BLUE = "Blue";
    public static final String COLOR_UNKNOWN = "Unknown";
    public static final String COLOR_NODETECT = "None Detected";
    public static final int COLOR_CLOSEST_PROXIMITY = 150; // Maximum distance is about 105 - 110.

    // Auto drive.
    public static final double AUTO_DRIVE_SPEED = 0.8;
    public static final double AUTO_DRIVE_SLOW_SPEED = 0.4;
    public static final double MOTOR_DELAY_STOP = 0.1;
    public static final double AUTO_TURN_SPEED = 0.5;
    public static final double AUTO_STOP_DELAY_SECONDS = 0.5;
    
    // Controllers.
    public static final int XBOX_CONTROLLER_PORT = 0;
    public static final boolean driveTrainMotorSafety = false;

    // Gyro.
    public static final double GYRO_CYCLE_TIME = 0.02;
    public static final int ENCODER_VALUE_PER_FOOT = 2440;

    // Limelight.
    public static final int LIMELIGHT_PIPELINE_ID = 0;
    public static final double LIMELIGHT_X_FORGIVENESS = 0.1;
    public static final double LIMELIGHT_X_TURRET_FORGIVENESS = 0.5;
    public static final double LIMELIGHT_X_CLOSE = 2;
    public static final double LIMELIGHT_X_CLOSE_TURN_SPEED = 0.35;
    public static final double LIMELIGHT_X_CENTER_SPEED = 0.45;
    public static final double LIMELIGHT_FOLLOW_SPEED = 0.55;
    public static final double LIMELIGHT_FAST_FOLLOW_SPEED = 0.65;
    public static final double LIMELIGHT_SLOW_FOLLOW_SPEED = 0.45;
    public static final double LIMELIGHT_FOCAL_LENGTH = 2.9272791257541;
    public static final double LIMELIGHT_SEEK_SPEED = 0.6;
    public static final double K = 4 * Math.sqrt(8.75);
}
