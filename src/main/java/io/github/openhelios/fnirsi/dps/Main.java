package io.github.openhelios.fnirsi.dps;

import java.io.IOException;

import io.github.openhelios.fnirsi.dps.protocol.response.All;
import io.github.openhelios.fnirsi.dps.protocol.response.OutputVoltageCurrentPower;
import io.github.openhelios.fnirsi.dps.protocol.response.Response;

/**
 * Example console program for the usage of the DPS150 class.
 *
 * Sends commands to and receives responses from the DPS-150
 */
public class Main {

  private final DPS150 device;

  private float outputVoltageInV;
  private float outputCurrentInA;
  private boolean isMeteringOn;
  private boolean isPowerOn;
  private int brightnessState;
  private int volumeState;

  /**
   * The main method.
   *
   * @param args The command line arguments, which are not used.
   * @throws IOException If there is an IO exception.
   */
  public static void main(final String[] args) throws IOException {
    final Main main = new Main(new DPS150());
    main.start();
  }

  private Main(final DPS150 device) {
    this.device = device;
  }

  private void printHelp() {
    System.out.println("""
        + / - Increase or decrease voltage by 0.01V
        > / < Increase or decrease current by 0.001A
        p     Toggle between output power on and off
        L / l Increase or decrease brightness of the display
        V / v Increase or decrease sound volume
        q     Quit application
        a     Request all
        h     Print this help
        """);
  }

  private void start() throws IOException {
    device.connect();
    final ConsolePrinter consolePrinter = new ConsolePrinter();
    device.addListener(consolePrinter);
    device.addListener(this::onMessage);
    device.requestModelName();
    device.requestHardwareVersion();
    device.requestFirmwareVersion();
    //    device.requestUpperLimitTemperature();
    //    device.setGroup(new Group(12.00f, 0.000f), 0);
    //    device.setGroup(new Group(12.01f, 0.001f), 1);
    //    device.setGroup(new Group(12.02f, 0.002f), 2);
    //    device.setGroup(new Group(12.03f, 0.003f), 3);
    //    device.setGroup(new Group(12.04f, 0.004f), 4);
    //    device.setGroup(new Group(12.05f, 0.005f), 5);
    //    device.setOverVoltageProtectionInV(19);
    //    device.setOverCurrentProtectionInA(3);
    //    device.setOverPowerProtectionInW(50f);
    //    device.setOverTemperatureProtectionInC(77);
    //    device.setUnderVoltageProtectionInV(1);
    device.requestAll();
    // setOutputVoltageInV(11.00f);
    // setOutputCurrentInA(0.02f);
    // setPowerOn(true);
    setBrightness(15 * 7 / 10);
    setVolume(15 * 0 / 10);
    boolean isRunning = true;
    while (isRunning) {
      switch (System.in.read()) {
        case '+' -> setOutputVoltageInV(outputVoltageInV + 0.01f);
        case '-' -> setOutputVoltageInV(outputVoltageInV - 0.01f);
        case '>' -> setOutputCurrentInA(outputCurrentInA + 0.001f);
        case '<' -> setOutputCurrentInA(outputCurrentInA - 0.001f);
        case 'm' -> setMeteringOn(!isMeteringOn);
        case 'p' -> setPowerOn(!isPowerOn);
        case 'L' -> setBrightness(brightnessState + 1);
        case 'l' -> setBrightness(brightnessState - 1);
        case 'V' -> setVolume(volumeState + 1);
        case 'v' -> setVolume(volumeState - 1);
        case 'a' -> device.requestAll();
        case 'h' -> printHelp();
        case 'q' -> isRunning = false;
      }
    }
    device.disconnect();
    device.removeAllListeners();
  }

  private void setOutputVoltageInV(final float outputVoltageInV) {
    System.out.println(String.format("Set: U=%5.2fV", outputVoltageInV));
    this.outputVoltageInV = outputVoltageInV;
    device.setOutputVoltageInV(outputVoltageInV);
  }

  private void setOutputCurrentInA(final float outputCurrentInA) {
    System.out.println(String.format("Set: I=%5.3fA", outputCurrentInA));
    this.outputCurrentInA = outputCurrentInA;
    device.setOutputCurrentInA(outputCurrentInA);
  }

  private void setMeteringOn(final boolean isEnabled) {
    System.out.println("Metering: " + (isEnabled ? "ON" : "OFF"));
    this.isMeteringOn = isEnabled;
    device.setMeteringOn(isEnabled);
  }

  private void setPowerOn(final boolean isEnabled) {
    System.out.println("Power: " + (isEnabled ? "ON" : "OFF"));
    this.isPowerOn = isEnabled;
    device.setOutputOn(isEnabled);
  }

  private void setBrightness(final int state) {
    System.out.println("Brightness: " + Math.round(state * 20 / 3f) + "%");
    this.brightnessState = state;
    device.setBrightness(brightnessState);
  }

  private void setVolume(final int state) {
    System.out.println("Volume: " + Math.round(state * 20 / 3f) + "%");
    this.volumeState = state;
    device.setVolume(volumeState);
  }

  private void onMessage(final Response response) {
    if (response instanceof final OutputVoltageCurrentPower o) {
      if (0 == outputCurrentInA) {
        outputCurrentInA = o.currentInA();
      }
      if (0 == outputVoltageInV) {
        outputVoltageInV = o.voltageInV();
      }
    } else if (response instanceof final All a) {
      isMeteringOn = a.isMeteringOn();
      isPowerOn = a.isPowerOn();
      brightnessState = a.brightnessState();
      volumeState = a.volumeState();
    }
  }

}
