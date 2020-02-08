/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class OI {
    private static XboxController controller;

    public OI() {
        controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    }

    public XboxController getControllerInstant() {
        if (controller == null) {
            controller = new XboxController(Constants.XBOX_CONTROLLER_PORT);
        }
        
        return controller;
    }
}