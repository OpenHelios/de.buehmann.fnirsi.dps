package de.buehmann.fnirsi.dps.protocol;

import java.util.HashMap;
import java.util.Map;

import org.jspecify.annotations.Nullable;

public enum RequestId {

  INPUT_VOLTAGE(0xC0),

  // SET allowed
  OUTPUT_VOLTAGE(0xC1),

  // SET allowed
  OUTPUT_CURRENT(0xC2),

  OUTPUT_VOLTAGE_CURRENT_POWER(0xC3),

  TEMPERATURE(0xC4),

  // SET allowed, between 0 and 15
  BRIGHTNESS_STATE(0xD6),

  // SET allowed, between 0 and 15
  VOLUME_STATE(0xD7),

  // SET allowed
  METERING_ON(0xD8),

  OUTPUT_CAPACITY(0xD9),

  OUTPUT_ENERGERY(0xDA),

  // SET allowed
  OUTPUT_ON(0xDB),

  PROTECTION_STATE(0xDC),

  OUTPUT_MODE(0xDD),

  MODEL_NAME(0xDE),

  HARDWARE_VERSION(0xDF),

  FIRMWARE_VERSION(0xE0),

  // SET allowed
  UPPER_LIMIT_VOLTAGE(0xE2),

  // SET allowed
  UPPER_LIMIT_CURRENT(0xE3),

  // SET allowed
  ALL(0xFF),

  ;

  private final byte id;

  RequestId(final int id) {
    this.id = (byte) id;
  }

  public byte get() {
    return id;
  }

  private final static Map<Byte, RequestId> map = new HashMap<>(RequestId.values().length);

  static {
    for (final RequestId requestId : values()) {
      map.put(requestId.id, requestId);
    }
  }

  @Nullable
  public static RequestId findById(final byte b) {
    return map.get(b);
  }

}
