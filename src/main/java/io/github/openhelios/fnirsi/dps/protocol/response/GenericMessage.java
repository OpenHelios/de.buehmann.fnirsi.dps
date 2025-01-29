package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;

public record GenericMessage(byte[] message) implements Response {

  @Override
  public final String toString() {
    return Data.hex(message);
  }

}
