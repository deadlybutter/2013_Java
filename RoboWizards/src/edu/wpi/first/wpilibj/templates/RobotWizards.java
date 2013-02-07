/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotWizards extends SimpleRobot {
    
    public static final double JOYSTICK_DEAD_ZONE = 10;
    
    private final WizardArmController armController;
    private final RobotDrive robotDrive;
    private final Joystick joystick1;
    private final Joystick joystick2;
    private final Joystick joystick3;

    public RobotWizards() {
        this.armController = new WizardArmController(RobotMap.RAISE_ARM_RELAY, 
                RobotMap.ROTATE_ARM_RELAY);
        this.robotDrive = new RobotDrive(RobotMap.MOTOR_ONE, RobotMap.MOTOR_TWO);
        joystick1 = new Joystick(1);
        joystick2 = new Joystick(2);
        joystick3 = new Joystick(3);
    } 
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while(isOperatorControl() && isEnabled()){
            robotDrive.tankDrive(joystick1, joystick2);
            checkRotateJoystick();
            checkClimbButtons();
            checkAutoClimbButtons();
            
            Timer.delay(0.01);
        }
    }
    
    private void checkRotateJoystick(){
        if(joystick3.getY() > JOYSTICK_DEAD_ZONE){
            armController.rotateArmsForward();
        }
        else if(joystick3.getY() < -JOYSTICK_DEAD_ZONE){
            armController.rotateArmsBackward();
        }
        else{
            armController.stopArmRotation();
        }
    }
    
    private void checkClimbButtons(){
        if(joystick3.getRawButton(1)){
            armController.raiseClimbArms();
        }
        else if(joystick3.getRawButton(2) || joystick3.getRawButton(3)){
            armController.lowerClimbArms();
        }
        else{
            armController.stopClimbArms();
        }
    }
    
    private void checkAutoClimbButtons(){
        //To-Do
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
