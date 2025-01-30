package io.github.openhelios.fnirsi.dps.protocol;

import org.jspecify.annotations.Nullable;

/**
 * Utility class to generate and check the checksum.
 */
public class Checksum {

  /** The amount of bytes used for the checksum, which is 1. */
  public static final int SIZE = 1;

  private static byte generate(final byte[] bytes) {
    int sum = 0;
    for (int i = 2; i < bytes.length - 1; i++) {
      sum += bytes[i];
      sum &= 0xFF;
    }
    return (byte) sum;
  }

  /**
   * Generates a checksum from the given bytes and writes into the last byte.
   *
   * @param bytes The message.
   */
  public static void write(final byte[] bytes) {
    bytes[bytes.length - 1] = generate(bytes);
  }

  /**
   * Verifies the checksum in the given message.
   *
   * @param bytes The message.
   * @return Empty string for a valid checksum, otherwise an error message.
   */
  @Nullable
  public static String check(final byte[] bytes) {
    final byte checksum = generate(bytes);
    if (bytes[bytes.length - 1] != checksum) {
      return "expected checksum " + Data.hex(checksum) + ", but was " + Data.hex(bytes[bytes.length - 1]) + " in "
          + Data.hex(bytes);
    }
    return null;
  }

  private Checksum() {
    // hide
  }
}
