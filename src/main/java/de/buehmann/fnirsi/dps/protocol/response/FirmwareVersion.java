package de.buehmann.fnirsi.dps.protocol.response;

import de.buehmann.fnirsi.dps.protocol.Checksum;
import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.Index;

public record FirmwareVersion(String version) implements VersionResponse {

  FirmwareVersion(final byte[] message) {
    this(Data.toText(message, Index.DATA.get(), message.length - Index.DATA.get() - Checksum.SIZE));
  }
}
