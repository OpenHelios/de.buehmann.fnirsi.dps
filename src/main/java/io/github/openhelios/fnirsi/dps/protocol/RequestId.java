package io.github.openhelios.fnirsi.dps.protocol;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

/**
 * Enumeration for all request IDs.
 */
public enum RequestId {

  /** Request input voltage. */
  INPUT_VOLTAGE(0xC0),

  /** Set and request output voltage. */
  OUTPUT_VOLTAGE(0xC1),

  /** Set and request output current. */
  OUTPUT_CURRENT(0xC2),

  /** Request output voltage, current and power. */
  OUTPUT_VOLTAGE_CURRENT_POWER(0xC3),

  /** Request device temperature. */
  TEMPERATURE(0xC4),

  /** Set and request brightness state. */
  BRIGHTNESS_STATE(0xD6),

  /** Set and request volume state. */
  VOLUME_STATE(0xD7),

  /** Set and request metering enabled. */
  METERING_ON(0xD8),

  /** Request output capacity. */
  OUTPUT_CAPACITY(0xD9),

  /** Request output energy. */
  OUTPUT_ENERGERY(0xDA),

  /** Set and request output enabled. */
  OUTPUT_ON(0xDB),

  /** Request protection state. */
  PROTECTION_STATE(0xDC),

  /** Request output mode. */
  OUTPUT_MODE(0xDD),

  /** Request model name. */
  MODEL_NAME(0xDE),

  /** Request hardware version. */
  HARDWARE_VERSION(0xDF),

  /** Request firmware version. */
  FIRMWARE_VERSION(0xE0),

  /** Set and request upper limit for voltage. */
  UPPER_LIMIT_VOLTAGE(0xE2),

  /** Set and request upper limit for current. */
  UPPER_LIMIT_CURRENT(0xE3),

  /** Request all values. */
  ALL(0xFF),

  ;

  private final byte id;

  RequestId(final int id) {
    this.id = (byte) id;
  }

  /**
   * The byte for the request ID.
   *
   * @return The byte for the request ID.
   */
  public byte get() {
    return id;
  }

  private final static Map<Byte, RequestId> map = new HashMap<>(RequestId.values().length);

  static {
    for (final RequestId requestId : values()) {
      map.put(requestId.id, requestId);
    }
  }

  /**
   * Finds the request ID by the given byte representing the ID.
   *
   * @param b The byte.
   * @return The found request ID, otherwise {@code null}.
   */
  @Nullable
  public static RequestId findById(final byte b) {
    return map.get(b);
  }

}
