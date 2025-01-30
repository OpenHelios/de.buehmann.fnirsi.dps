package io.github.openhelios.fnirsi.dps.protocol.response;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * The protection state.
 */
public enum ProtectionState {

  /** Currently no protection needed with index = 0. */
  NONE,

  /** Over Voltage Protection with index = 1. */
  OVP,

  /** Over Current Protection with index = 2. */
  OCP,

  /** Over Power Protection with index = 3. */
  OPP,

  /** Over Temperature Protection with index = 4. */
  OTP,

  /** Lower Voltage Protection with index = 5. */
  LVP,

  /** Reverse Input Protection with index = 6. */
  RIP,

  /** Reverse Output Protection with index = 7. */
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

  /**
   * Gets the protection state by the index.
   *
   * @param index The index.
   * @return The protection state for the given index.
   */
  @Nullable
  public static ProtectionState byIndex(final byte index) {
    return map.get(index);
  }

}
