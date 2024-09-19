// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelSubsystem extends SubsystemBase {
    // Thread-safe singleton design pattern.
    private static volatile WheelSubsystem instance;
    private static Object mutex = new Object();
    private static CANSparkMax drivingMotor = new CANSparkMax(2, MotorType.kBrushless);
    private static CANSparkMax turningMotor = new CANSparkMax(1, MotorType.kBrushless);
    private static CANcoder mainCoder = new CANcoder(3);
    private static StatusSignal<Double> positionSignal = mainCoder.getAbsolutePosition();

    public static WheelSubsystem getInstance() {
        WheelSubsystem result = instance;
       
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new WheelSubsystem();
                }
            }
        }
        return instance;
    }


    /** Creates a new ExampleSubsystem. */
    public WheelSubsystem() {
        super("ExampleSubsystem");
    }

    public void SetDriveSpeed(double speed) {
        drivingMotor.set(speed);
    }
    public void SetTurningSpeed(double speed) {
        turningMotor.set(speed);
    }
    public double GetMoterPos() {
        return Units.rotationsToDegrees(positionSignal.getValue());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
