package io.github.openhelios.fnirsi.dps.protocol.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

/**
 * Response containing all data from DSP-150.
 *
 * @param inputVoltageInV The input voltage in V.
 * @param nominalVoltageInV The nominal output voltage in V.
 * @param nominalCurrentInA The nominal output current in A.
 * @param voltageInV The current output voltage in V.
 * @param currentInA The current output current in A.
 * @param powerInW The current output power in W.
 * @param temperatureInC The current device temperature in °C.
 * @param groups The 6 groups containing nominal output voltage in V and current in A.
 * @param overVoltageProtectionInV The over voltage protection in V.
 * @param overCurrentProtectionInA The over current protection in A.
 * @param overPowerProtectionInWh The over power protection in Wh.
 * @param overTemperatureProtectionInC The over temperature protection in °C.
 * @param underVoltageProtectionInV The under voltage protection in V.
 * @param brightnessState The brightness state.
 * @param volumeState The volume state
 * @param isMeteringOn True, if metering is on.
 * @param capacityInAh The capacity in Ah.
 * @param energyInWh The energy in Wh.
 * @param isPowerOn True, if power is on.
 * @param protectionState The protection state
 * @param outputMode The output mode.
 * @param upperLimitVoltageInV The upper limit voltage in V.
 * @param upperLimitCurrentInV the upper limit current in V.
 */
public record All( //
    float inputVoltageInV, //
    float nominalVoltageInV, //
    float nominalCurrentInA, //
    float voltageInV, //
    float currentInA, //
    float powerInW, //
    float temperatureInC, //
    List<Group> groups, // six groups with index from 0 to 5
    float overVoltageProtectionInV, //
    float overCurrentProtectionInA, //
    float overPowerProtectionInWh, //
    float overTemperatureProtectionInC, //
    float underVoltageProtectionInV, //
    int brightnessState, //
    int volumeState, //
    boolean isMeteringOn, //
    float capacityInAh, //
    float energyInWh, //
    boolean isPowerOn, //
    ProtectionState protectionState, //
    OutputMode outputMode, //
    float upperLimitVoltageInV, //
    float upperLimitCurrentInV) implements VoltageResponse, CurrentResponse, PowerResponse {

  /**
   * The constructor.
   *
   * @param message The raw message.
   */
  public All(final byte[] message) {
    this( //
        Data.float32(message, Index.DATA.get() + 0), //
        Data.float32(message, Index.DATA.get() + 4), //
        Data.float32(message, Index.DATA.get() + 8), //
        Data.float32(message, Index.DATA.get() + 12), //
        Data.float32(message, Index.DATA.get() + 16), //
        Data.float32(message, Index.DATA.get() + 20), //
        Data.float32(message, Index.DATA.get() + 24), //
        createGroups(message, Index.DATA.get() + 28), //
        Data.float32(message, Index.DATA.get() + 76), //
        Data.float32(message, Index.DATA.get() + 80), //
        Data.float32(message, Index.DATA.get() + 84), //
        Data.float32(message, Index.DATA.get() + 88), //
        Data.float32(message, Index.DATA.get() + 92), //
        Data.uint8(message, Index.DATA.get() + 96), //
        Data.uint8(message, Index.DATA.get() + 97), //
        Data.bool(message, Index.DATA.get() + 98), //
        Data.float32(message, Index.DATA.get() + 99), //
        Data.float32(message, Index.DATA.get() + 103), //
        Data.bool(message, Index.DATA.get() + 107), //
        ProtectionState.byIndex(message[Index.DATA.get() + 108]), //
        OutputMode.by(message[Index.DATA.get() + 109]), //
        Data.float32(message, Index.DATA.get() + 110), //
        Data.float32(message, Index.DATA.get() + 114) //
    );
  }

  /**
   * Creates the groups.
   *
   * @param message The raw message.
   * @param index The index.
   * @return The list of created groups.
   */
  private static List<Group> createGroups(final byte[] message, final int index) {
    final List<Group> groups = new ArrayList<>(6);
    for (int k = 0; k < 6; k++) {
      groups.add(new Group(message, index + 8 * k));
    }
    return Collections.unmodifiableList(groups);
  }

}
