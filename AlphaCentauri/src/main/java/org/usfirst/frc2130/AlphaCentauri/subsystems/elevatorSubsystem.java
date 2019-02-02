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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class elevatorSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX elevatorMotorMaster;
    private WPI_VictorSPX elevatorMotorSlavetwo;
    private DigitalInput lowProx;
    private DigitalInput midProx;
    private DoubleSolenoid elevatorBrakeSolenoid;
    private WPI_TalonSRX elevatorMotorSlaves;
    private DigitalInput maxProx;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private String desiredProx = "Low";

    public elevatorSubsystem() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        elevatorMotorMaster = new WPI_TalonSRX(4);
        
        
        
        elevatorMotorSlavetwo = new WPI_VictorSPX(5);
        
        
        
        lowProx = new DigitalInput(0);
        addChild("lowProx",lowProx);
        
        
        midProx = new DigitalInput(1);
        addChild("midProx",midProx);
        
        
        elevatorBrakeSolenoid = new DoubleSolenoid(0, 0, 1);
        addChild("elevatorBrakeSolenoid",elevatorBrakeSolenoid);
        
        
        elevatorMotorSlaves = new WPI_TalonSRX(3);
        
        
        
        maxProx = new DigitalInput(2);
        addChild("maxProx",maxProx);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        elevatorMotorSlaves.follow(elevatorMotorMaster);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new moveElevatorCommand());

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

    public boolean getProx(String prox) {
        if(prox == "Max") {
            return !maxProx.get();
        } else if (prox == "Mid") {
            return !midProx.get();
        } else {
            return !lowProx.get();
        }
    }

    public void moveElevator() {
        if (getProx(desiredProx)) {
            disableElevator();
        } else {
            elevatorBrakeSolenoid.set(Value.kReverse);
            elevatorMotorMaster.set(-Robot.oi.operatorJoystick.getRawAxis(1));
        }
    }

    private void disableElevator() {
        elevatorMotorMaster.set(0);
        elevatorBrakeSolenoid.set(Value.kForward);
    }

    public void startupRoutine() {
        elevatorBrakeSolenoid.set(Value.kReverse);
    }

    public void setDesiredOutput(String setDesiredProx) {
        desiredProx = setDesiredProx;
    }

    public String returnDesiredProx() {
        return desiredProx;
    }
}
