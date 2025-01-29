package io.github.openhelios.fnirsi.dps.protocol.response;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

public enum ProtectionState {

  NONE,

  /** Over Voltage Protection. */
  OVP,

  /** Over Current Protection. */
  OCP,

  /** Over Power Protection. */
  OPP,

  /** Over Temperature Protection. */
  OTP,

  /** Lower Voltage Protection. */
  LVP,

  /** Reverse Input Protection. */
  RIP,

  /** Reverse Output Protection. */
  ROP,

  ;

  private static Map<Byte, ProtectionState> map = new HashMap<>();

  static {
    int index = 0;
    for (final ProtectionState state : values()) {
      map.put((byte) index, state);
      index++;
    }
  }

  @Nullable
  public static ProtectionState byIndex(final byte index) {
    return map.get(index);
  }

}
