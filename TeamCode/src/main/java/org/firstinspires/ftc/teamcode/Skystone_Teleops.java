package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.ArrayList;
import java.util.Arrays;


@TeleOp(name = "Skystone_Teleops", group = " Skystone")
public class Skystone_Teleops extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor frontleftmotor = null;
    private DcMotor frontrightmotor = null;
    private DcMotor backleftmotor = null;
    private DcMotor backrightmotor = null;

    int[] moveType = new int[50];
    int moveNum = 0;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");

        frontleftmotor.setPower(0.0);
        frontrightmotor.setPower(0.0);
        backleftmotor.setPower(0.0);
        backrightmotor.setPower(0.0);


    }

    @Override
    public void loop() {
        //Variables being used
        double leftMove = gamepad1.left_stick_y;
        double rightMove = gamepad1.right_stick_y;

        moveType[0] = 0;

        if(gamepad1.dpad_up = true){
            moveNum+=1;
            moveType[moveNum] = 1;
        }

        if(gamepad1.dpad_down = true){
            moveNum+=1;
            moveType[moveNum] = 2;
        }

        if(gamepad1.dpad_left = true){
            moveNum+=1;
            moveType[moveNum] = 3;
        }


        if(moveNum == 0 || moveType[moveNum] == 1){
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.sin(robotAngle) - rightX;
            final double v2 = -r * Math.cos(robotAngle) - rightX;
            final double v3 = r * Math.cos(robotAngle) - rightX;
            final double v4 = -r * Math.sin(robotAngle) - rightX;

            frontleftmotor.setPower(v1);
            frontrightmotor.setPower(v2);
            backleftmotor.setPower(v3);
            backrightmotor.setPower(v4);
            telemetry.addData("Front Left Motor:", v1 );
            telemetry.addData("Front Right Motor:", v2 );
            telemetry.addData("Front Right Motor:", v3 );
            telemetry.addData("Front Right Motor:", v4 );
        }

        else if(moveType[moveNum] == 2){
            if(gamepad1.left_trigger>0){
                frontleftmotor.setPower(gamepad1.left_trigger);
                backleftmotor.setPower(-gamepad1.left_trigger);
                frontrightmotor.setPower(gamepad1.left_trigger);
                backrightmotor.setPower(-gamepad1.left_trigger);
            }
            else if(gamepad1.right_trigger>0){
                frontleftmotor.setPower(-gamepad1.right_trigger);
                backleftmotor.setPower(gamepad1.right_trigger);
                frontrightmotor.setPower(-gamepad1.right_trigger);
                backrightmotor.setPower(gamepad1.right_trigger);
            }
            else{
                frontleftmotor.setPower(leftMove);
                backleftmotor.setPower(leftMove);
                frontrightmotor.setPower(rightMove);
                backrightmotor.setPower(rightMove);
            }
        }

        else if(moveType[moveNum] == 3) {
            if (gamepad1.left_trigger > 0) {
                frontleftmotor.setPower(gamepad1.left_trigger * 0.2);
                backleftmotor.setPower(-gamepad1.left_trigger * 0.2);
                frontrightmotor.setPower(gamepad1.left_trigger * 0.2);
                backrightmotor.setPower(-gamepad1.left_trigger * 0.2);
            } else if (gamepad1.right_trigger > 0) {
                frontleftmotor.setPower(-gamepad1.right_trigger * 0.2);
                backleftmotor.setPower(gamepad1.right_trigger * 0.2);
                frontrightmotor.setPower(-gamepad1.right_trigger * 0.2);
                backrightmotor.setPower(gamepad1.right_trigger * 0.2);
            } else {
                frontleftmotor.setPower(leftMove * 0.2);
                backleftmotor.setPower(leftMove * 0.2);
                frontrightmotor.setPower(rightMove * 0.2);
                backrightmotor.setPower(rightMove * 0.2);
            }

        }

        else{
            telemetry.addLine("YEET YEET MY NAME IS BALJEET");
        }
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }
}
