package io.github.openhelios.fnirsi.dps.protocol;

import java.util.List;

/**
 * Utility class for converting bytes into int, float or hexadecimal String.
 */
public class Data {

  private Data() {
    // hide constructor for this utility class
  }

  /**
   * Converts the given byte to a hexadecimal String.
   *
   * @param data The byte.
   * @return The hexadecimal String.
   */
  public static String hex(final byte data) {
    return String.format("%02X ", data & 0xFF);
  }

  /**
   * Converts the given bytes to a hexadecimal String.
   *
   * @param data The bytes.
   * @return The hexadecimal String.
   */
  public static String hex(final byte[] data) {
    final StringBuilder sb = new StringBuilder(data.length * 2);
    for (final byte b : data) {
      sb.append(String.format("%02X ", b & 0xFF));
    }
    return sb.toString();
  }

  /**
   * Converts the given bytes to a hexadecimal String.
   *
   * @param data The bytes.
   * @return The hexadecimal String.
   */
  public static String hex(final List<Byte> data) {
    final StringBuilder sb = new StringBuilder(data.size() * 2);
    for (final byte b : data) {
      sb.append(String.format("%02X ", b & 0xFF));
    }
    return sb.toString();
  }

  /**
   * Converts the given bytes to a String starting at the given index and using only the given number of bytes by
   * converting each byte into a char. Chars lower than 0x20 are converted in to a dot.
   *
   * @param data The bytes.
   * @param index The start index.
   * @param size The size.
   * @return The created String.
   */
  public static String text(final byte[] data, final int index, final int size) {
    final StringBuilder sb = new StringBuilder(size);
    for (int i = index; i < index + size; i++) {
      final char c = (char) (data[i] & 0xFF);
      sb.append(c < 0x20 ? '.' : c);
    }
    return sb.toString();
  }

  /**
   * Converts four bytes representing a float32 from the given data starting at the given index into a Java float.
   *
   * @param data The bytes.
   * @param index The start index.
   * @return The created Java float.
   */
  public static float float32(final byte[] data, final int index) {
    return Float.intBitsToFloat( //
        (data[index + 3] & 0xFF) << 24 //
            | (data[index + 2] & 0xFF) << 16 //
            | (data[index + 1] & 0xFF) << 8 //
            | data[index] & 0xFF //
    );
  }

  /**
   * Sets four bytes representing a float32 into the data starting at the given index from a Java float.
   *
   * @param data The bytes.
   * @param index The start index.
   * @param value The Java float.
   */
  public static void setFloat32(final byte[] data, final int index, final float value) {
    final int bits = Float.floatToIntBits(value);
    data[index + 0] = (byte) (bits & 0xFF);
    data[index + 1] = (byte) (bits >>> 8 & 0xFF);
    data[index + 2] = (byte) (bits >>> 16 & 0xFF);
    data[index + 3] = (byte) (bits >>> 24 & 0xFF);
  }

  /**
   * Converts a byte representing an unsigned integer with 8 bits into a Java int.
   *
   * @param b The byte.
   * @return The Java integer.
   */
  public static int uint8(final byte b) {
    return b & 0xFF;
  }

  /**
   * Converts a byte representing an unsigned integer with 8 bits at the given index into a Java integer.
   *
   * @param bytes The bytes.
   * @param index The index.
   * @return The Java integer.
   */
  public static int uint8(final byte[] bytes, final int index) {
    return uint8(bytes[index]);
  }

  /**
   * Converts a byte representing a C boolean at the given index into a Java boolean.
   *
   * @param bytes The bytes.
   * @param index The index.
   * @return The Java integer.
   */
  public static boolean bool(final byte[] bytes, final int index) {
    return bytes[index] != 0;
  }

}
