package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


public class SkystoneAuto extends LinearOpMode{
    DcMotor         frontleft,frontright,backleft,backright;
    BNO055IMU       imu;
    Orientation     lastAngles = new Orientation();
    double          globalAngle,power = 1.0, correction, rotation;
    boolean         buttona,buttonb;
    PIDcontroller   pidRotate,pidDrive;
    DistanceSensor  d1;
    @Override
    public void runOpmode() throws InterruptedException{
        frontleft = hardwareMap.dcMotor.get("frontleft");
        backleft = hardwareMap.dcMotor.get("frontleft");//change the names
        frontright = hardwareMap.dcMotor.get("frontleft");
        backright = hardwareMap.dcMotor.get("frontleft");

        frontright.setDirection(DcMotorSimple.Direction.REVERSE);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);

        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //same for other motor

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode          =BNO055IMU.SensorMode.IMU;
        parameters.angleUnit     =BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit     =BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled=false;

        imu=hardwareMap.get(BNO055IMU.class,"imu");//or whatever we call it

        imu.initialize(parameters);
        pidRotate = new PIDcontroller(0,0,0);
        pidDrive =new PIDcontroller(0.5,0,0);
        telemetry.addData("Starting up...");
        telemetry.update();
        while (!isStopRequested()) && !imu.isGyroCalibrated();
        {
            sleep(50);
            idle();

        }
        telemetry.addData("Ready");
        telemetry.addData("IMU:", imu.getCalibrationStatus().toString());
        telemetry.update();

        waitForStart();
        sleep(1000);
        pidDrive.setSetpoint();
        pidDrive.setOutputRange(0, power);
        pidDrive.setInputRange(-90, 90);
        pidDrive.enable();

        while (opModeIsActive())//This all put inside a while loop so the sensor is constantly checking correcting hence the name PID loop
        {
            // Use PID with imu input to drive in a straight line.
            correction = pidDrive.performPID(getAngle());

            telemetry.addData("Current Angle", lastAngles.firstAngle);
            telemetry.addData("Wanted Angle", globalAngle);
            telemetry.addData("Correction amount required", correction);
            telemetry.addData("Rotation applied", rotation);
            telemetry.update();
            frontleft.setPower(power - correction);//do for all 4 but add correction to the right motors

            if(opModeIsActive()){//essentially where to put the pathways
                frontleft.setPower(power);
                sleep(1000);
                rotate (-90, power);

            }

    }

        private void resetAngle()
        {
            lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            globalAngle = 0;
        }
        private double getAngle()
        {
            Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            double deltaAngle = angles.firstAngle - lastAngles.firstAngle;
            if (deltaAngle < -180)
                deltaAngle += 360;
            else if (deltaAngle > 180)
                deltaAngle -= 360;

            globalAngle += deltaAngle;

            lastAngles = angles;

            return globalAngle;
        }
        private void rotate (int degrees,double power)
        {
            // restart imu angle tracking.
            resetAngle();

            // If input degrees > 359, we cap at 359 with same sign as input.
            if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

            // start pid controller. PID controller will monitor the turn angle with respect to the
            // target angle and reduce power as we approach the target angle. We compute the p and I
            // values based on the input degrees and starting power level. We compute the tolerance %
            // to yield a tolerance value of about 1 degree.
            // Overshoot is dependant on the motor and gearing configuration, starting power, weight
            // of the robot and the on target tolerance.

            pidRotate.reset();

            double p = Math.abs(power/degrees);
            double i = p / 100.0;
            pidRotate.setPID(p, i, 0);

            pidRotate.setSetpoint(degrees);
            pidRotate.setInputRange(0, degrees);
            pidRotate.setOutputRange(0, power);
            pidRotate.setTolerance(1.0 / Math.abs(degrees) * 100.0);
            pidRotate.enable();

            // getAngle() returns + when rotating counter clockwise (left) and - when rotating
            // clockwise (right).

            // rotate until turn is completed.

            if (degrees < 0)
            {
                // On right turn we have to get off zero first.
                while (opModeIsActive() && getAngle() == 0)
                {
                    frontleft.setPower(power);
                    frontright.setPower(-power);
                    sleep(100);
                }

                do
                {
                    power = pidRotate.performPID(getAngle()); // power will be - on right turn.
                    frontleft.setPower(-power);
                    frontright.setPower(power);
                } while (opModeIsActive() && !pidRotate.onTarget());
            }
            else    // left turn.
                do
                {
                    power = pidRotate.performPID(getAngle()); // power will be + on left turn.
                    frontleft.setPower(-power);
                    frontright.setPower(power);
                } while (opModeIsActive() && !pidRotate.onTarget());

            // turn the motors off.
            frontleft.setPower(0);
            frontright.setPower(0);

            rotation = getAngle();

            // wait for rotation to stop.
            sleep(500);

            // reset angle tracking on new heading.
            resetAngle();
        }
}
