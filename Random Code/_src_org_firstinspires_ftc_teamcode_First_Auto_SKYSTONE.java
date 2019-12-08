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

@Autonomous(name = "Test_AUTO_SKYSTONE", group = "Concept")

public class First_Auto_SKYSTONE extends LinearOpMode{
    
    private ElapsedTime runtime = new ElapsedTime();
    
    private static DcMotor frontleftmotor = null;
    private static DcMotor frontrightmotor = null;
    private static DcMotor backleftmotor = null;
    private static DcMotor backrightmotor = null;
    
    double frontleftspeed = -0.8;
    double frontrightspeed = -0.8;
    double backleftspeed = -0.8;
    double backrightspeed = -0.8;
    
    
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
        spinrighttest();
        
        
    }

    private void testSpin(){
        frontrightmotor.setTargetPosition(2240);
        frontleftmotor.setTargetPosition(2240);
        backrightmotor.setTargetPosition(2240);
        backleftmotor.setTargetPosition(2240);
        
        frontrightmotor.setPower(frontrightspeed);
        frontleftmotor.setPower(-frontleftspeed);
        backrightmotor.setPower(-backrightspeed);
        backleftmotor.setPower(backleftspeed);

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

    private void spinrighttest(){
        frontrightmotor.setTargetPosition(1120);
        frontleftmotor.setTargetPosition(1120);
        backrightmotor.setTargetPosition(1120);
        backleftmotor.setTargetPosition(1120);
        
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
        else if(type == 'b'){
            moveBackward(distance_full);
        }
        else{

        }
        
    }
    
    private void distanceFormula(double x1, double y1, double x2, double y2, char type){
        double final_x = Math.pow(x2 - x1, 2);
        double final_y = Math.pow(y2 - y1, 2);
        double distance = (Math.sqrt(final_x + final_y));
        distanceCalculate(distance, type);
    }   
    
    private void path_1(){
        straferight(9);
        distanceFormula(-54, 45, -54, 60, 'f');
        spinright(90);
        distanceFormula(-45, 53, -30, 53, 'f');
        spinleft(180);
        distanceFormula(-30, 53, -26, 53, 'b' );
        distanceFormula(-26, 53, -53, 53, 'f');
        straferight(53);
    }
    
    private void test(){
        distanceCalculate(1, 'f');
    }

    private void path_2(){
        straferight(9);
        distanceFormula(-54, 45, -54, 60, 'f');
        spinright(90);
        distanceFormula(-45, 53, -30, 53, 'f');
        spinleft(180);
        distanceFormula(-30, 53, -26, 53, 'b' );
        distanceFormula(-26, 53, -53, 53, 'f');
        straferight(53);
        distanceFormula(-53, 0, -27, 0, 'b');
    }

    private void path_3(){
        straferight(9);
        distanceFormula(-54, 21, -54, 60, 'f');
        spinright(90);
        distanceFormula(-45, 53, -30, 53, 'f');
        spinleft(180);
        distanceFormula(-30, 53, -26, 53, 'b' );
        distanceFormula(-26, 53, -53, 53, 'f');
        straferight(53);

    }

    private void path_4(){
        straferight(9);
        distanceFormula(-54, 21, -54, 60, 'f');
        spinright(90);
        distanceFormula(-45, 53, -30, 53, 'f');
        spinleft(180);
        distanceFormula(-30, 53, -26, 53, 'b' );
        distanceFormula(-26, 53, -53, 53, 'f');
        straferight(53);
        distanceFormula(-53, 0, -27, 0, 'b');
    }

    /*private void path_5(){
        strafeleft(25);
        distanceFormula(-38, -21, -38, -61, 'f');
        distanceFormula(-38, -61, -38, 23, 'b');
        spinright(180);
        distanceFormula(-38, 41, -38, 32, 'b');
        straferight(25);
        strafeleft(25);
        distanceFormula(-38, 32, -38, -61, 'b');
        distanceFormula(-38, -61, -38, 32, 'f');
        straferight(25);
        strafeleft(25);
        spinleft(90);
        distanceFormula(-29, 23, -70, 23, 'f');
        strafeleft(23);
    }

    private void path_6(){
        strafeleft(25);
        distanceFormula(-38, -21, -38, -61, 'f');
        distanceFormula(-38, -61, -38, 23, 'b');
        spinright(180);
        distanceFormula(-38, 41, -38, 32, 'b');
        straferight(25);
        strafeleft(25);
        distanceFormula(-38, 32, -38, -61, 'b');
        distanceFormula(-38, -61, -38, 32, 'f');
        straferight(25);
        strafeleft(25);
        spinleft(90);
        distanceFormula(-29, 23, -44, 23, 'f');
        strafeleft(23);
    }

    private void path_7(){
        strafeleft(25);
        distanceFormula(-38, -45, -38, -61, 'f');
        distanceFormula(-38, -61, -38, 23, 'b');
        spinright(180);
        distanceFormula(-38, 41, -38, 32, 'b');
        straferight(25);
        strafeleft(25);
        distanceFormula(-38, 32, -38, -61, 'b');
        distanceFormula(-38, -61, -38, 32, 'f');
        straferight(25);
        strafeleft(25);
        spinleft(90);
        distanceFormula(-29, 23, -70, 23, 'f');
        strafeleft(23);
    }

    private void path_8(){
        strafeleft(25);
        distanceFormula(-38, -45, -38, -61, 'f');
        distanceFormula(-38, -61, -38, 23, 'b');
        spinright(180);
        distanceFormula(-38, 41, -38, 32, 'b');
        straferight(25);
        strafeleft(25);
        distanceFormula(-38, 32, -38, -61, 'b');
        distanceFormula(-38, -61, -38, 32, 'f');
        straferight(25);
        strafeleft(25);
        spinleft(90);
        distanceFormula(-29, 23, -44, 23, 'f');
        strafeleft(23);
    }*/

    private void path_9(){
        strafeleft(9);
        distanceFormula(54, 45, 54, 60, 'f');
        spinleft(90);
        distanceFormula(45, 53, 30, 53, 'f');
        spinright(180);
        distanceFormula(30, 53, 26, 53, 'b' );
        distanceFormula(26, 53, 53, 53, 'f');
        strafeleft(53);
    }
    
    private void path_10(){
        strafeleft(9);
        distanceFormula(54, 45, 54, 60, 'f');
        spinleft(90);
        distanceFormula(45, 53, 30, 53, 'f');
        spinright(180);
        distanceFormula(30, 53, 26, 53, 'b' );
        distanceFormula(26, 53, 53, 53, 'f');
        strafeleft(53);
        distanceFormula(53, 0, 27, 0, 'b');
    }

    private void path_11(){
        strafeleft(9);
        distanceFormula(54, 21, 54, 60, 'f');
        spinleft(90);
        distanceFormula(45, 53, 30, 53, 'f');
        spinright(180);
        distanceFormula(30, 53, 26, 53, 'b' );
        distanceFormula(26, 53, 53, 53, 'f');
        strafeleft(53);
    }

    private void path_12(){
        strafeleft(9);
        distanceFormula(54, 21, 54, 60, 'f');
        spinleft(90);
        distanceFormula(45, 53, 30, 53, 'f');
        spinright(180);
        distanceFormula(30, 53, 26, 53, 'b' );
        distanceFormula(26, 53, 53, 53, 'f');
        strafeleft(53);
        distanceFormula(53, 0, 27, 0, 'b');
    }

    /*private void path_13(){
        straferight(25);
        distanceFormula(38, -21, 38, -61, 'f');
        distanceFormula(38, -61, 38, 23, 'b');
        spinright(180);
        distanceFormula(38, 41, 38, 32, 'b');
        strafeleft(25);
        straferight(25);
        distanceFormula(38, 32, 38, -61, 'b');
        distanceFormula(38, -61, 38, 32, 'f');
        strafeleft(25);
        straferight(25);
        spinright(90);
        distanceFormula(29, 23, 70, 23, 'f');
        straferight(23);
    }

    private void path_14(){
        straferight(25);
        distanceFormula(38, -21, 38, -61, 'f');
        distanceFormula(38, -61, 38, 23, 'b');
        spinright(180);
        distanceFormula(38, 41, 38, 32, 'b');
        strafeleft(25);
        straferight(25);
        distanceFormula(38, 32, 38, -61, 'b');
        distanceFormula(38, -61, 38, 32, 'f');
        strafeleft(25);
        straferight(25);
        spinright(90);
        distanceFormula(29, 23, 44, 23, 'f');
        straferight(23);
    }*/

}
