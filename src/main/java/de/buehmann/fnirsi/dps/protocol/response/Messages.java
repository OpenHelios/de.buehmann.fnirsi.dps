package de.buehmann.fnirsi.dps.protocol.response;

import java.util.Arrays;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.buehmann.fnirsi.dps.DPS150Listener;
import de.buehmann.fnirsi.dps.protocol.Checksum;
import de.buehmann.fnirsi.dps.protocol.CommandId;
import de.buehmann.fnirsi.dps.protocol.Data;
import de.buehmann.fnirsi.dps.protocol.HeaderId;
import de.buehmann.fnirsi.dps.protocol.Index;
import de.buehmann.fnirsi.dps.protocol.RequestId;

@SuppressWarnings("ArrayRecordComponent")
public class Messages {

  private static final Logger LOG = LoggerFactory.getLogger(Messages.class);

  private final byte[] bytes;
  private final Set<DPS150Listener> listeners;

  private int startMessageIndex;
  private int nextMessageIndex;
  private Response response;

  public Messages(final byte[] bytes, final Set<DPS150Listener> listeners) {
    this.bytes = bytes;
    this.listeners = listeners;
  }

  private boolean hasMore() {
    if (startMessageIndex >= bytes.length) {
      return false;
    }
    final byte headerId = bytes[startMessageIndex];
    if (HeaderId.INPUT.get() != headerId) {
      LOG.error("expected header input ID {}, but was {}", Data.hex(HeaderId.INPUT.get()), Data.hex(headerId));
      return false;
    }
    final int sizeIndex = startMessageIndex + Index.DATA_SIZE.get();
    if (sizeIndex >= bytes.length) {
      LOG.error("expected lengthIndex={} < bytes.length={}", sizeIndex, bytes.length);
      return false;
    }
    final int dataSize = Data.uint8(bytes[sizeIndex]);
    final int messageSize = Index.DATA.get() + dataSize + Checksum.SIZE;
    nextMessageIndex = startMessageIndex + messageSize;
    if (nextMessageIndex > bytes.length) {
      LOG.error("expected next message starts at {}, but was not inside bytes.length={}", nextMessageIndex,
          bytes.length);
      return false;
    }
    final byte[] message = Arrays.copyOfRange(bytes, startMessageIndex, nextMessageIndex);
    final String error = Checksum.check(message);
    if (null != error) {
      LOG.error(error);
      return false;
    }
    final byte commandId = message[Index.COMMAND.get()];
    if (commandId != CommandId.GET.get()) {
      System.err.println("unexpected response command ID " + Data.hex(commandId));
      return false;
    }
    final RequestId requestId = RequestId.findById(message[Index.TYPE.get()]);
    if (null == requestId) {
      LOG.error("unknown requestId={}", Data.hex(message[Index.TYPE.get()]));
      response = new GenericMessage(message);
    } else {
      response = switch (requestId) {
        case INPUT_VOLTAGE -> new InputVoltage(message);
        case TEMPERATURE -> new Temperature(message);
        case UPPER_LIMIT_VOLTAGE -> new UpperLimitVoltage(message);
        case UPPER_LIMIT_CURRENT -> new UpperLimitCurrent(message);
        case OUTPUT_VOLTAGE_CURRENT_POWER -> new OutputVoltageCurrentPower(message);
        case MODEL_NAME -> new ModelName(message);
        case OUTPUT_CAPACITY -> new OutputCapacity(message);
        case OUTPUT_ENERGERY -> new OutputEnergy(message);
        case HARDWARE_VERSION -> new HardwareVersion(message);
        case FIRMWARE_VERSION -> new FirmwareVersion(message);
        case PROTECTION_STATE -> new ProtectionStateChanged(message);
        case OUTPUT_MODE -> new OutputModeChanged(message);
        case ALL -> new All(message);
        default -> new GenericMessage(message);
      };
    }
    return true;
  }

  private Response next() {
    startMessageIndex = nextMessageIndex;
    return response;
  }

  public void readAll() {
    while (hasMore()) {
      final Response response = next();
      for (final DPS150Listener listener : listeners) {
        listener.onMessage(response);
      }
    }
  }

}
