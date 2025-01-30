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
    device.requestAll();
    // setOutputVoltageInV(11.00f);
    // setOutputCurrentInA(0.02f);
    // setPowerOn(true);
    setBrightness(15 * 7 / 10);
    setVolume(15 * 0 / 10);
    boolean isRunning = true;
    while (isRunning) {
      final int i = System.in.read();
      if ('+' == i) {
        setOutputVoltageInV(outputVoltageInV + 0.01f);
      } else if ('-' == i) {
        setOutputVoltageInV(outputVoltageInV - 0.01f);
      } else if ('>' == i) {
        setOutputCurrentInA(outputCurrentInA + 0.001f);
      } else if ('<' == i) {
        setOutputCurrentInA(outputCurrentInA - 0.001f);
      } else if ('m' == i) {
        setMeteringOn(!isMeteringOn);
      } else if ('p' == i) {
        setPowerOn(!isPowerOn);
      } else if ('L' == i) {
        setBrightness(brightnessState + 1);
      } else if ('l' == i) {
        setBrightness(brightnessState - 1);
      } else if ('V' == i) {
        setVolume(volumeState + 1);
      } else if ('v' == i) {
        setVolume(volumeState - 1);
      } else if ('q' == i) {
        isRunning = false;
      } else if ('a' == i) {
        device.requestAll();
      } else if ('h' == i) {
        printHelp();
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
