package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Index;

public record OutputModeChanged(OutputMode outputMode) implements Response {
  public OutputModeChanged(final byte[] message) {
    this(OutputMode.by(message[Index.DATA.get()]));
  }
}
