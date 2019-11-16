package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;

@Autonomous

public class First_Auto_SKYSTONE {
    private ElapsedTime runtime = new ElapsedTime();
    
    private static DcMotor frontleftmotor = null;
    private static DcMotor frontrightmotor = null;
    private static DcMotor backleftmotor = null;
    private static DcMotor backrightmotor = null;
    
    double frontleftspeed = 0.8;
    double frontrightspeed = 0.8;
    double backleftspeed = 0.8;
    double backrightspeed = 0.8;
    
    
    public void runOpMode(){
        frontleftmotor = hardwareMap.get(DcMotor.class, "frontleftmotor");
        frontrightmotor = hardwareMap.get(DcMotor.class, "frontrightmotor");
        backrightmotor = hardwareMap.get(DcMotor.class, "backrightmotor");
        backleftmotor = hardwareMap.get(DcMotor.class, "backleftmotor");
        
        frontleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        
        
        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        
        waitforstart();
        
    }
    
    private void moveForward(double distance){
        int distance_real = (int) distance;
        frontrightmotor.setTargetPosition(distance_real);
        frontleftmotor.setTargetPosition(distance_real);
        backrightmotor.setTargetPosition(distance_real);
        backleftmotor.setTargetPosition(distance_real);
        
        frontrightmotor.setPower(-frontrightmotor);
        frontleftmotor.setPower(frontleftmotor);
        backrightmotor.setPower(-backrightmotor);
        backleftmotor.setPower(backleftmotor);
        
        stopMotor();

    }

    private void moveBackward(double distance){
        int distance_real = (int) distance;
        frontrightmotor.setTargetPosition(distance);
        frontleftmotor.setTargetPosition(distance);
        backrightmotor.setTargetPosition(distance);
        backleftmotor.setTargetPosition(distance);
        
        frontrightmotor.setPower(frontrightmotor);
        frontleftmotor.setPower(-frontleftmotor);
        backrightmotor.setPower(backrightmotor);
        backleftmotor.setPower(-backleftmotor);
        
        stopMotor();
    }


    /*private void spinleft(){
        frontrightmotor.setTargetPosition();
        frontleftmotor.setTargetPosition();
        backrightmotor.setTargetPosition();
        backleftmotor.setTargetPosition();
        
        frontrightmotor.setPower(frontrightmotor);
        frontleftmotor.setPower(frontleftmotor);
        backrightmotor.setPower(backrightmotor);
        backleftmotor.setPower(backleftmotor);
        
        stopMotor();
    }

    private void spinright(){
        frontrightmotor.setTargetPosition();
        frontleftmotor.setTargetPosition();
        backrightmotor.setTargetPosition();
        backleftmotor.setTargetPosition();
        
        frontrightmotor.setPower(frontrightmotor);
        frontleftmotor.setPower(frontleftmotor);
        backrightmotor.setPower(backrightmotor);
        backleftmotor.setPower(backleftmotor);
        
        stopMotor();
    }*/

    private void stopMotor() {
        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    
    
    private void distanceCalculate(double inches, char type){
        double wheelTicks = 1120;
        double wheelCirc = 12.57;
        double distance_x = inches/wheelCirc;
        double distance_full = distance_x * wheelTicks;
        this.type = type;
        if(type == "f"){
            moveForward(distance_full);
        }
        else{
            moveBackward(distance_full);
        }
        
    }
    
    private void distanceFormula(double x1, double y1, double x2, double y2, char type){
        double final_x = Math.pow(x2 - x1, 2);
        double final_y = Math.pow(y2 - y1, 2);
        double distance = (Math.sqrt(final_x + final_y));
        distanceCalculate(distance, type);
    }
    
    private void path_1(){
        distanceFormula(-63, 45, -63, 56);
        spinright(45);
        
    }
    
    
}
