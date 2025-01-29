package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

public record ModelName(String name) implements NameResponse {
  public ModelName(final byte[] message) {
    this(Data.text(message, Index.DATA.get(), message[Index.DATA_SIZE.get()] & 0xFF));
  }
}
