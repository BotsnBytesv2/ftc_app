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

@Autonomous(name = "First_AUTO_SKYSTONE", group = "Concept")

public class First_Auto_SKYSTONE extends LinearOpMode{
    
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
        
        frontleftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        
        
        frontleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        
        
        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        
        waitForStart();
        path_1();
        
        
    }
    
    private void moveForward(double distance){
        int distance_real = (int) distance;
        frontrightmotor.setTargetPosition(distance_real);
        frontleftmotor.setTargetPosition(distance_real);
        backrightmotor.setTargetPosition(distance_real);
        backleftmotor.setTargetPosition(distance_real);
        
        frontrightmotor.setPower(-frontrightspeed);
        frontleftmotor.setPower(frontleftspeed);
        backrightmotor.setPower(-backrightspeed);
        backleftmotor.setPower(backleftspeed);
        
        stopMotor1();

    }

    private void moveBackward(double distance){
        int distance_real = (int) distance;
        frontrightmotor.setTargetPosition(distance_real);
        frontleftmotor.setTargetPosition(distance_real);
        backrightmotor.setTargetPosition(distance_real);
        backleftmotor.setTargetPosition(distance_real);
        
        frontrightmotor.setPower(frontrightspeed);
        frontleftmotor.setPower(-frontleftspeed);
        backrightmotor.setPower(backrightspeed);
        backleftmotor.setPower(-backleftspeed);
        
        stopMotor1();
    }


    private void spinleft(double degrees){
        int distance_spin = (int) (degrees * 1120/90);

        frontrightmotor.setTargetPosition(distance_spin);
        frontleftmotor.setTargetPosition(distance_spin);
        backrightmotor.setTargetPosition(distance_spin);
        backleftmotor.setTargetPosition(distance_spin);
        
        frontrightmotor.setPower(frontrightspeed);
        frontleftmotor.setPower(-frontleftspeed);
        backrightmotor.setPower(-backrightspeed);
        backleftmotor.setPower(backleftspeed);
        
        stopMotor1();
    }

    private void spinright(double degrees){
        int distance_spin = (int) (degrees * 1120/90);

        frontrightmotor.setTargetPosition(distance_spin);
        frontleftmotor.setTargetPosition(distance_spin);
        backrightmotor.setTargetPosition(distance_spin);
        backleftmotor.setTargetPosition(distance_spin);
        
        frontrightmotor.setPower(-frontrightspeed);
        frontleftmotor.setPower(frontleftspeed);
        backrightmotor.setPower(backrightspeed);
        backleftmotor.setPower(-backleftspeed);
        
        stopMotor1();
    }

    private void straferight(double distance){
        int distance_real = (int) (distance * 1120/ 2.5);

        frontrightmotor.setTargetPosition(distance_real);
        frontleftmotor.setTargetPosition(distance_real);
        backrightmotor.setTargetPosition(distance_real);
        backleftmotor.setTargetPosition(distance_real);
        
        frontrightmotor.setPower(-frontrightspeed);
        frontleftmotor.setPower(frontleftspeed);
        backrightmotor.setPower(backrightspeed);
        backleftmotor.setPower(-backleftspeed);
        
        stopMotor1();

    }

    private void strafeleft(double distance){
        int distance_real = (int) (distance * 1120/ 2.5);

        frontrightmotor.setTargetPosition(distance_real);
        frontleftmotor.setTargetPosition(distance_real);
        backrightmotor.setTargetPosition(distance_real);
        backleftmotor.setTargetPosition(distance_real);
        
        frontrightmotor.setPower(frontrightspeed);
        frontleftmotor.setPower(-frontleftspeed);
        backrightmotor.setPower(-backrightspeed);
        backleftmotor.setPower(backleftspeed);
        
        stopMotor1();

    }

    private void stopMotor1() {
        
        frontleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftmotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        frontleftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        frontleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    
    private void distanceCalculate(double inches, char type){
        double wheelTicks = 1120;
        double wheelCirc = 12.57;
        double distance_x = inches/wheelCirc;
        double distance_full = distance_x * wheelTicks;
        if(type == 'f'){
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
        distanceFormula(-63, 45, -63, 56, 'f');
        spinright(45);
        distanceFormula(-63, 56, -57, 62, 'f');
        spinright(45);
        distanceFormula(-57, 62,-26 ,62 , 'f');
        sleep(1000);



    }
    
    
}
