package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Index;

public record ProtectionStateChanged(ProtectionState protectionState) implements Response {
  public ProtectionStateChanged(final byte[] message) {
    this(ProtectionState.byIndex(message[Index.DATA.get()]));
  }

}
