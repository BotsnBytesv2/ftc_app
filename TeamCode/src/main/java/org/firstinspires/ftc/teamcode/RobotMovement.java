package org.firstinspires.ftc.teamcode;

import static com.company.Robot.*;

public class RobotMovement {
    public static void goToPosistion(double x1, double y1, double x2, double y2, double movementSpeed){
        double worldXPosistion = x1;
        double worldYPosistion = y1;
        double y = y2;
        double x = x2;

        double absoluteAngleToTarget = Math.atan2(y-worldYPosistion, x-worldXPosistion);

        double relativeAngleToPoint = absoluteAngleToTarget -  (worldAngle_rad - Math.toRadians(90));



    }
}
