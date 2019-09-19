package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
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

        int[] moveType = {1};
        int moveNum = 0;
    }

    @Override
    public void loop() {
        //Variables being used
        int[] moveType = {1};
        int moveNum = 0;
        double leftMove = gamepad1.left_stick_y;
        double rightMove = gamepad1.right_stick_y;




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
                frontleftmotor.setPower(gamepad1.left_trigger * 0.1);
                backleftmotor.setPower(-gamepad1.left_trigger * 0.1);
                frontrightmotor.setPower(gamepad1.left_trigger * 0.1);
                backrightmotor.setPower(-gamepad1.left_trigger * 0.1);
            } else if (gamepad1.right_trigger > 0) {
                frontleftmotor.setPower(-gamepad1.right_trigger * 0.1);
                backleftmotor.setPower(gamepad1.right_trigger * 0.1);
                frontrightmotor.setPower(-gamepad1.right_trigger * 0.1);
                backrightmotor.setPower(gamepad1.right_trigger * 0.1);
            } else {
                frontleftmotor.setPower(leftMove * 0.1);
                backleftmotor.setPower(leftMove * 0.1);
                frontrightmotor.setPower(rightMove * 0.1);
                backrightmotor.setPower(rightMove * 0.1);
            }

        }

        else{

        }
    }
}
