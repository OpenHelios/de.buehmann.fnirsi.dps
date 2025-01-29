package io.github.openhelios.fnirsi.dps.protocol.response;

import io.github.openhelios.fnirsi.dps.protocol.Checksum;
import io.github.openhelios.fnirsi.dps.protocol.Data;
import io.github.openhelios.fnirsi.dps.protocol.Index;

public record HardwareVersion(String version) implements VersionResponse {

  HardwareVersion(final byte[] message) {
    this(Data.text(message, Index.DATA.get(), message.length - Index.DATA.get() - Checksum.SIZE));
  }
}
