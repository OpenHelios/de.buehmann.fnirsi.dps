package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Index;

public record ProtectionStateChanged(ProtectionState protectionState) implements Response {
  public ProtectionStateChanged(final byte[] message) {
    this(ProtectionState.byIndex(message[Index.DATA.get()]));
  }

}
