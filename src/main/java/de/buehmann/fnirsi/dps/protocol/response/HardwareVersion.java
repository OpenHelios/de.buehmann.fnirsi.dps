package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Checksum;
import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record HardwareVersion(String version) implements VersionResponse {

  HardwareVersion(final byte[] message) {
    this(Data.toText(message, Index.DATA.get(), message.length - Index.DATA.get() - Checksum.SIZE));
  }
}
