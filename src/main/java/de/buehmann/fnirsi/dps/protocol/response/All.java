package de.buehmann.fnirsi.dps.protocol.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

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

  private static List<Group> createGroups(final byte[] message, final int index) {
    final List<Group> groups = new ArrayList<>(6);
    for (int k = 0; k < 6; k++) {
      groups.add(new Group(message, index + 8 * k));
    }
    return Collections.unmodifiableList(groups);
  }

}
