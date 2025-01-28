package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Index;

public record OutputModeChanged(OutputMode outputMode) implements Response {
  public OutputModeChanged(final byte[] message) {
    this(OutputMode.by(message[Index.DATA.get()]));
  }
}
