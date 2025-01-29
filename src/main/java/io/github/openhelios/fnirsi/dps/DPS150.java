package io.github.openhelios.fnirsi.dps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import io.github.openhelios.fnirsi.dps.protocol.Command;
import io.github.openhelios.fnirsi.dps.protocol.ConstCommand;
import io.github.openhelios.fnirsi.dps.protocol.SetCommand;
import io.github.openhelios.fnirsi.dps.protocol.response.Messages;

public class DPS150 implements SerialPortDataListener {

  private static final String DESCRIPTIVE_PORT_NAME = "AT32 Virtual Com Port";

  private static final Logger LOG = LoggerFactory.getLogger(DPS150.class);

  private final SerialPort serialPort;

  private final Set<DPS150Listener> listeners = new HashSet<>();

  private static SerialPort getSerialPortByDescrptvePortName(final SerialPort[] serialPorts) {
    final List<String> names = new ArrayList<>(serialPorts.length);
    for (final SerialPort serialPort : serialPorts) {
      final String name = serialPort.getDescriptivePortName();
      if (DESCRIPTIVE_PORT_NAME.equals(name)) {
        return serialPort;
      }
      names.add(name);
    }
    throw new IllegalStateException(DESCRIPTIVE_PORT_NAME + " not found in: " + names);
  }

  public DPS150(final SerialPort serialPort) {
    this.serialPort = serialPort;
    serialPort.setBaudRate(115200);
    serialPort.setNumDataBits(8);
    serialPort.setNumStopBits(1);
    serialPort.setParity(SerialPort.NO_PARITY);
    serialPort.setFlowControl(SerialPort.FLOW_CONTROL_RTS_ENABLED | SerialPort.FLOW_CONTROL_CTS_ENABLED);
    if (!serialPort.openPort()) {
      throw new IllegalStateException("opening commPort " + serialPort.getDescriptivePortName() + " failed: error code "
          + serialPort.getLastErrorCode() + ", line " + serialPort.getLastErrorLocation());
    }
    SerialPort.addShutdownHook(new Thread(this::onShutdown));
  }

  public DPS150() {
    this(getSerialPortByDescrptvePortName(SerialPort.getCommPorts()));
  }

  public void addListener(final DPS150Listener listener) {
    this.listeners.add(listener);
    if (1 == listeners.size()) {
      serialPort.addDataListener(this);
    }
  }

  public void removeListener(final DPS150Listener listener) {
    if (listeners.remove(listener) && listeners.isEmpty()) {
      serialPort.removeDataListener();
    }
  }

  public void removeAllListeners() {
    for (final var listener : new ArrayList<>(listeners)) {
      removeListener(listener);
    }
  }

  private void writeBytes(final byte[] bytes) {
    final int count = serialPort.writeBytes(bytes, bytes.length);
    if (count != bytes.length) {
      throw new IllegalStateException("expected " + bytes.length + " send bytes, but returned " + count);
    }
  }

  private void writeBytes(final Command command) {
    writeBytes(command.get());
  }

  public void connect() {
    writeBytes(ConstCommand.CONNECT);
  }

  public void disconnect() {
    if (serialPort.isOpen()) {
      serialPort.flushDataListener();
      writeBytes(ConstCommand.DISCONNECT);
      serialPort.closePort();
    }
  }

  public void baudRate() {
    writeBytes(ConstCommand.BAUD_RATE_115200);
  }

  public void requestModelName() {
    writeBytes(ConstCommand.REQUEST_MODEL_NAME);
  }

  public void requestHardwareVersion() {
    writeBytes(ConstCommand.REQUEST_HARDWARE_VERSION);
  }

  public void requestFirmwareVersion() {
    writeBytes(ConstCommand.REQUEST_FIRMWARE_VERSION);
  }

  public void requestAll() {
    writeBytes(ConstCommand.REQUEST_ALL);
  }

  public void setOutputVoltageInV(final float voltageInV) {
    writeBytes(SetCommand.outputVoltage(voltageInV));
  }

  public void setOutputCurrentInA(final float currentInA) {
    writeBytes(SetCommand.outputCurrent(currentInA));
  }

  public void setMeteringOn(final boolean isEnabled) {
    writeBytes(SetCommand.meteringOn(isEnabled));
  }

  public void setMeteringState(final byte state) {
    writeBytes(SetCommand.meteringState(state));
  }

  public void setOutputOn(final boolean isEnabled) {
    writeBytes(SetCommand.outputOn(isEnabled));
  }

  public void setBrightness(final int state) {
    writeBytes(SetCommand.brightness(state));
  }

  public void setVolume(final int state) {
    writeBytes(SetCommand.volume(state));
  }

  @Override
  public int getListeningEvents() {
    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
  }

  @Override
  public void serialEvent(final SerialPortEvent event) {
    final int count = serialPort.bytesAvailable();
    if (0 == count) {
      LOG.error("serialEvent with 0 bytes");
      return; // ignore
    }
    final byte[] bytes = new byte[count];
    final int readCount = serialPort.readBytes(bytes, count);
    if (readCount != count) {
      LOG.error("expected {}, but get {}", count, readCount);
      return; // ignore
    }
    if (!listeners.isEmpty()) {
      final Messages messages = new Messages(bytes, listeners);
      new Thread(messages::readAll).start();
    }
  }

  public boolean isConnected() {
    return serialPort.isOpen();
  }

  private void onShutdown() {
    disconnect();
    removeAllListeners();
  }

}
