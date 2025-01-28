package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;

public record Group(float voltageInV, float currentInA) {
  Group(final byte[] message, final int index) {
    this(Data.toFloat(message, index), Data.toFloat(message, index + 4));
  }
}
