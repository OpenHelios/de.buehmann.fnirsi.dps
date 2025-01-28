package de.buehmann.fnirsi.dps.protocol;

import org.jspecify.annotations.Nullable;

public class Checksum {

  public static final int SIZE = 1;

  private static byte generate(final byte[] bytes) {
    int sum = 0;
    for (int i = 2; i < bytes.length - 1; i++) {
      sum += bytes[i];
      sum &= 0xFF;
    }
    return (byte) sum;
  }

  public static void write(final byte[] bytes) {
    bytes[bytes.length - 1] = generate(bytes);
  }

  /**
   * @param bytes The message.
   * @return Empty string for a valid checksum, otherwise an error message.
   */
  @Nullable
  public static String check(final byte[] bytes) {
    final byte checksum = generate(bytes);
    if (bytes[bytes.length - 1] != checksum) {
      return "expected checksum " + Data.toHex(checksum) + ", but was " + Data.toHex(bytes[bytes.length - 1]) + " in "
          + Data.toHex(bytes);
    }
    return null;
  }

}
