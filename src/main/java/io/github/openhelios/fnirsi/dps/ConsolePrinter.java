package io.github.openhelios.fnirsi.dps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.openhelios.fnirsi.dps.protocol.response.All;
import io.github.openhelios.fnirsi.dps.protocol.response.FirmwareVersion;
import io.github.openhelios.fnirsi.dps.protocol.response.HardwareVersion;
import io.github.openhelios.fnirsi.dps.protocol.response.InputVoltage;
import io.github.openhelios.fnirsi.dps.protocol.response.OutputEnergy;
import io.github.openhelios.fnirsi.dps.protocol.response.OutputMode;
import io.github.openhelios.fnirsi.dps.protocol.response.OutputModeChanged;
import io.github.openhelios.fnirsi.dps.protocol.response.OutputVoltageCurrentPower;
import io.github.openhelios.fnirsi.dps.protocol.response.ProtectionStateChanged;
import io.github.openhelios.fnirsi.dps.protocol.response.Response;
import io.github.openhelios.fnirsi.dps.protocol.response.Temperature;
import io.github.openhelios.fnirsi.dps.protocol.response.Unknown;
import io.github.openhelios.fnirsi.dps.protocol.response.UpperLimitTemperature;

/**
 * A console printer of the values received from DPS-150.
 */
public class ConsolePrinter implements DPS150Listener {

  private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");

  private float temperatureInC;
  private InputVoltage inputVoltage = new InputVoltage(0);
  private OutputVoltageCurrentPower outputVoltageCurrentPower = new OutputVoltageCurrentPower(0, 0, 0);
  private OutputEnergy outputEnergy = new OutputEnergy(0);
  private OutputMode outputModeId = OutputMode.CV;

  /**
   * The default constructor.
   */
  public ConsolePrinter() {
    // do nothing
  }

  private boolean print(final Response response) {
    System.out.println(response);
    return false;
  }

  private void print() {
    System.out.println(DTF.format(LocalDateTime.now()) + " " + String.format( //
        "Ui=%5.2fV U=%5.2fV I=%5.3fA P=%5.2fW E=%7.3fWh T=%2d°C", //
        inputVoltage.voltageInV(), outputVoltageCurrentPower.voltageInV(), //
        outputVoltageCurrentPower.currentInA(), //
        outputVoltageCurrentPower.powerInW(), //
        outputEnergy.energyInWh(), //
        Math.round(temperatureInC)) + " " + outputModeId);
  }

  private boolean update(final InputVoltage v) {
    final boolean modified = !inputVoltage.isSameDisplay(v);
    if (modified) {
      this.inputVoltage = v;
    }
    return modified;
  }

  private boolean update(final OutputVoltageCurrentPower o) {
    final boolean modified = !outputVoltageCurrentPower.isSameDisplay(o);
    if (modified) {
      this.outputVoltageCurrentPower = o;
    }
    return modified;
  }

  private boolean update(final OutputEnergy e) {
    final boolean modified = !outputEnergy.isSameDisplay(e);
    if (modified) {
      this.outputEnergy = e;
    }
    return modified;
  }

  private boolean update(final OutputModeChanged m) {
    final boolean modified = m.outputMode() != outputModeId;
    if (modified) {
      this.outputModeId = m.outputMode();
    }
    return modified;
  }

  private boolean update(final Temperature t) {
    final boolean modified = !t.isSameDisplay(temperatureInC);
    if (modified) {
      this.temperatureInC = t.temperatureInC();
    }
    return modified;
  }

  @Override
  public void onMessage(final Response response) {
    final boolean update = switch (response) {
      case final HardwareVersion d -> print(response);
      case final FirmwareVersion f -> print(response);
      case final Temperature t -> update(t);
      case final OutputVoltageCurrentPower c -> update(c);
      case final OutputEnergy e -> update(e);
      case final OutputModeChanged m -> update(m);
      case final InputVoltage v -> update(v);
      case final ProtectionStateChanged c -> print(response);
      case final UpperLimitTemperature u -> print(response);
      case final Unknown u -> print(response);
      case final All a -> print(response);
      default -> false;
    };
    if (update) {
      print();
    }
  }

}
