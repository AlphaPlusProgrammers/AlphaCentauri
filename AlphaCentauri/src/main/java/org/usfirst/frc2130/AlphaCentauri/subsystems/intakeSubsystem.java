// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2130.AlphaCentauri.subsystems;


import org.usfirst.frc2130.AlphaCentauri.Robot;
import org.usfirst.frc2130.AlphaCentauri.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class intakeSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DoubleSolenoid positionSolenoid;
    private DoubleSolenoid rocketPlacementSolenoid;
    private WPI_VictorSPX leftRollerMotor;
    private WPI_VictorSPX rightRollerMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    //This is a boolean that is used for the Handler Position, so it will only run once at a time.
    public boolean handlerPositionSetting;

    //I'm not entirely sure what this is used for, but it is needed for the defense/play handler methods.
    private int i;

    public intakeSubsystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        positionSolenoid = new DoubleSolenoid(0, 4, 5);
        addChild("positionSolenoid",positionSolenoid);
        
        
        rocketPlacementSolenoid = new DoubleSolenoid(0, 0, 1);
        addChild("rocketPlacementSolenoid",rocketPlacementSolenoid);
        
        
        leftRollerMotor = new WPI_VictorSPX(6);
        
        
        
        rightRollerMotor = new WPI_VictorSPX(7);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new RocketPlacementBackCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    //This pushes the defense/play handler solenoid forward.
    public void handlerPlayPosition() {
        positionSolenoid.set(Value.kForward);
    }
    
    //This pushes the defense.play handler solenoid backward.
    public void handlerDefensePosition() {
        positionSolenoid.set(Value.kReverse);
    }
    
    //This is a command that makes the defense/play handler only move once when the button is pressed, rather than repeatedly.
    public void handlerPosition() {
        if (handlerPositionSetting) {
            handlerPlayPosition();
        }
        else {
            handlerDefensePosition();
        }
    }
    
    //This makes sure that our boolean is set to the right setting, and that our bot starts with the handler in play position.
    public void startingPosition() {
        handlerPositionSetting = true;
        handlerPlayPosition();
    }
    
    //Not sure entirely how this works, but it makes sure that our defense/play handler only runs once at a time.
    public void setHandlerBoolean() {
        if(i == 0 && Robot.oi.handlerPositionValue()) {
            handlerPositionSetting = !handlerPositionSetting;
            i++;
        } else if (!Robot.oi.handlerPositionValue()){
            i = 0;
        }
    
    }
    //This pushes the solenoid that handles the Rocket Placement Handler forward.
    public void handlerRocketForward() {
        rocketPlacementSolenoid.set(Value.kForward);
    }
    //This pushes the solenoid that handles the Rocket Placement Handler backward.
    public void handlerRocketBackward() {
        rocketPlacementSolenoid.set(Value.kReverse);
    }

    //This Runs the intake/outtake. If neither triggers are held down, it will do nothing. If both triggers are held down, it will do nothing.
    //If Right Trigger is held, it will run the intake. If Left Trigger is held, it will run the outtake.
    public void runIntake() {
        if(Robot.oi.driverJoystick.getRawAxis(2) == 0 && Robot.oi.driverJoystick.getRawAxis(3) == 0){
            leftRollerMotor.set(0);
            rightRollerMotor.set(0);
        }else if(Robot.oi.driverJoystick.getRawAxis(2) > 0 && Robot.oi.driverJoystick.getRawAxis(3) > 0) {
            leftRollerMotor.set(0);
            rightRollerMotor.set(0);
        }else if(Robot.oi.driverJoystick.getRawAxis(2) > 0 && Robot.oi.driverJoystick.getRawAxis(3) == 0) {
            leftRollerMotor.set(-1);
            rightRollerMotor.set(-1);
        }else if(Robot.oi.driverJoystick.getRawAxis(2) == 0 && Robot.oi.driverJoystick.getRawAxis(3) > 0) {
            leftRollerMotor.set(1);
            rightRollerMotor.set(1);
        }        
        }     

}

