package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Index;

public record ModeChanged(OutputMode mode) implements Response {
  public ModeChanged(final byte[] message) {
    this(OutputMode.by(message[Index.DATA.get()]));
  }
}
