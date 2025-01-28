package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;

public record Group(float voltageInV, float currentInA) {
  Group(final byte[] message, final int index) {
    this(Data.float32(message, index), Data.float32(message, index + 4));
  }
}
