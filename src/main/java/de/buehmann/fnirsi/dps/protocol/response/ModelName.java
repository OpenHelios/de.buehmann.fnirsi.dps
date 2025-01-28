package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record ModelName(String name) implements NameResponse {
  public ModelName(final byte[] message) {
    this(Data.toText(message, Index.DATA.get(), message[Index.DATA_SIZE.get()] & 0xFF));
  }
}
