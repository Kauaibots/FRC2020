/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.hal.PWMJNI;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Lights extends SubsystemBase {


  SPI spi = new SPI(SPI.Port.kOnboardCS0);

  byte[] message = new byte[128];


  public Lights() {

    spi.setClockActiveLow();
    spi.setClockRate(360);
    spi.setMSBFirst();

    message[0] = 0;
    message[1] = 0;
    message[2] = 0;
    message[3] = 0;

    for(int i = 4; i < 124; i += 4) {
      message[i] = 127;
      message[i+1] = 127;
      message[i+2] = 127;
      message[i+3] = 127;
    }

    message[124] = 127;
    message[125] = 127;
    message[126] = 127;
    message[127] = 127;


  }

  @Override
  public void periodic() {

    spi.write(message, message.length);


  }
}
