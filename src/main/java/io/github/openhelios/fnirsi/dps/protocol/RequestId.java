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

  /** Set and request group 0 voltage in V. */
  GROUP_0_VOLTAGE(0xC5),

  /** Set and request group 0 current in A. */
  GROUP_0_CURRENT(0xC6),

  /** Set and request group 1 voltage in V. */
  GROUP_1_VOLTAGE(0xC7),

  /** Set and request group 1 current in A. */
  GROUP_1_CURRENT(0xC8),

  /** Set and request group 2 voltage in V. */
  GROUP_2_VOLTAGE(0xC9),

  /** Set and request group 2 current in A. */
  GROUP_2_CURRENT(0xCA),

  /** Set and request group 3 voltage in V. */
  GROUP_3_VOLTAGE(0xCB),

  /** Set and request group 3 current in A. */
  GROUP_3_CURRENT(0xCC),

  /** Set and request group 4 voltage in V. */
  GROUP_4_VOLTAGE(0xCD),

  /** Set and request group 4 current in A. */
  GROUP_4_CURRENT(0xCE),

  /** Set and request group 5 voltage in V. */
  GROUP_5_VOLTAGE(0xCF),

  /** Set and request group 5 current in A. */
  GROUP_5_CURRENT(0xD0),

  /** Set and request over voltage protection in V. */
  OVER_VOLTAGE_PROTECTION(0xD1),

  /** Set and request over current protection in V. */
  OVER_CURRENT_PROTECTION(0xD2),

  /** Set and request over power protection in W. */
  OVER_POWER_PROTECTION(0xD3),

  /** Set and request over temperature protection in °C. */
  OVER_TEMPERATURE_PROTECTION(0xD4),

  /** Set and request under voltage protection in V. */
  UNDER_VOLTAGE_PROTECTION(0xD5),

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

  /** Request unknown (0x01). */
  UNKNOWN(0xE1),

  /** Request upper limit voltage in V. */
  UPPER_LIMIT_VOLTAGE(0xE2),

  /** Request upper limit current in A. */
  UPPER_LIMIT_CURRENT(0xE3),

  /** Request unknown voltage in V. */
  UNKNOWN_VOLTAGE(0xE4),

  /** Request unknown current in A. */
  UNKNOWN_CURRENT(0xE5),

  /** Request upper limit power in W. */
  UPPER_LIMIT_POWER(0xE6),

  /** Request upper limit temperature in °C. */
  UPPER_LIMIT_TEMPERATURE(0xE7),

  /** Request unknown voltage 2 in V. */
  UNKNWON_VOLTAGE2(0xE8),

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
