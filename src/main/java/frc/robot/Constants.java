/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Motors
    public static final int MOTOR_LEFT_1_ID = 2;
    public static final int MOTOR_LEFT_2_ID = 4;
    public static final int MOTOR_RIGHT_1_ID = 1;
    public static final int MOTOR_RIGHT_2_ID = 3;
    public static final int MOTOR_TURRET_TURN_ID = 10;

    // Speed controller groups.
    public static final boolean LEFT_MOTORS_INVERTED = false;
    public static final boolean RIGHT_MOTORS_INVERTED = false;

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
    public static final double LIMELIGHT_X_FORGIVENESS = 0.01;
    public static final double LIMELIGHT_X_CLOSE = 2;
    public static final double LIMELIGHT_X_CLOSE_TURN_SPEED = 0.3;
    public static final double LIMELIGHT_FOLLOW_SPEED = 0.5;
    public static final double LIMELIGHT_FOCAL_LENGTH = 2.9272791257541;
    public static final double K = 4 * Math.sqrt(8.75);
}
