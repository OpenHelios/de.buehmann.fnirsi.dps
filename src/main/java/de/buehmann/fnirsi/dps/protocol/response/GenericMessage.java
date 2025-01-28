package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;

public record GenericMessage(byte[] message) implements Response {

  @Override
  public final String toString() {
    return Data.hex(message);
  }

}
