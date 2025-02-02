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
import io.github.openhelios.fnirsi.dps.protocol.response.Group;
import io.github.openhelios.fnirsi.dps.protocol.response.Messages;

/**
 * Represents the DPS-150 to send commands to and receive responses.
 */
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

  /**
   * Opens a serial connection with a DPS-150.
   *
   * @param serialPort The serial port to be used to open the connection with DPS-150.
   */
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

  /**
   * Default constructor searches automatically for DPS-150.
   */
  public DPS150() {
    this(getSerialPortByDescrptvePortName(SerialPort.getCommPorts()));
  }

  /**
   * Adds a listener, which wants to be informed by each received response from DPS-150.
   *
   * @param listener The DPS-150 listener.
   */
  public void addListener(final DPS150Listener listener) {
    this.listeners.add(listener);
    if (1 == listeners.size()) {
      serialPort.addDataListener(this);
    }
  }

  /**
   * Removes the given previously added listener.
   *
   * @param listener The listener.
   */
  public void removeListener(final DPS150Listener listener) {
    if (listeners.remove(listener) && listeners.isEmpty()) {
      serialPort.removeDataListener();
    }
  }

  /**
   * Removes all previously added listeners.
   */
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

  /**
   * Sends a connection message to DPS-150. The device shows the key icon while connected.
   */
  public void connect() {
    writeBytes(ConstCommand.CONNECT);
  }

  /**
   * Sends a disconnect message to DPS-150. The key icon disappears after disconnection.
   */
  public void disconnect() {
    if (serialPort.isOpen()) {
      serialPort.flushDataListener();
      writeBytes(ConstCommand.DISCONNECT);
      serialPort.closePort();
    }
  }

  /**
   * Sends the baud rate, which is normally 115200 bits per second.
   */
  public void baudRate() {
    writeBytes(ConstCommand.BAUD_RATE_115200);
  }

  /**
   * Sends a request command to get the model name, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.ModelName} response.
   */
  public void requestModelName() {
    writeBytes(ConstCommand.REQUEST_MODEL_NAME);
  }

  /**
   * Sends a request command to get the hardware version, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.HardwareVersion} response.
   */
  public void requestHardwareVersion() {
    writeBytes(ConstCommand.REQUEST_HARDWARE_VERSION);
  }

  /**
   * Sends a request command to get the firmware version, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.FirmwareVersion} response.
   */
  public void requestFirmwareVersion() {
    writeBytes(ConstCommand.REQUEST_FIRMWARE_VERSION);
  }

  /**
   * Sends a request command to get the unknown, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.Unknown} response.
   */
  public void requestUnknown() {
    writeBytes(ConstCommand.REQUEST_UNKNOWN);
  }

  /**
   * Sends a request command to get the upper limit temperature, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.UpperLimitTemperature} response.
   */
  public void requestUpperLimitTemperature() {
    writeBytes(ConstCommand.REQUEST_UPPER_LIMIT_TEMPERATURE);
  }

  /**
   * Sends a request command to get all values, which results in a
   * {@link io.github.openhelios.fnirsi.dps.protocol.response.All} response.
   */
  public void requestAll() {
    writeBytes(ConstCommand.REQUEST_ALL);
  }

  /**
   * Sends a command to set the output voltage in V.
   *
   * @param voltageInV The voltage in V.
   */
  public void setOutputVoltageInV(final float voltageInV) {
    writeBytes(SetCommand.outputVoltage(voltageInV));
  }

  /**
   * Sends a command to set the output current in A.
   *
   * @param currentInA The current in A.
   */
  public void setOutputCurrentInA(final float currentInA) {
    writeBytes(SetCommand.outputCurrent(currentInA));
  }

  /**
   * Sends a command to enable or disable the metering.
   *
   * @param isEnabled True to enable the metering.
   */
  public void setMeteringOn(final boolean isEnabled) {
    writeBytes(SetCommand.meteringOn(isEnabled));
  }

  /**
   * Sends a command to enable or disable the output.
   *
   * @param isEnabled True to enable the output.
   */
  public void setOutputOn(final boolean isEnabled) {
    writeBytes(SetCommand.outputOn(isEnabled));
  }

  /**
   * Sends a command to set the brightness state of the LCD display.
   *
   * @param state The brightness state between 0 and 15.
   */
  public void setBrightness(final int state) {
    writeBytes(SetCommand.brightness(state));
  }

  /**
   * Sends a command to set the volume state for the beep sound.
   *
   * @param state The volume state between 0 and 15.
   */
  public void setVolume(final int state) {
    writeBytes(SetCommand.volume(state));
  }

  /**
   * Sends two commands to set the voltage and the current of the given group to the given index.
   *
   * @param group The group.
   * @param index The index between 0 and 15.
   */
  public void setGroup(final Group group, final int index) {
    writeBytes(SetCommand.groupVoltage(group, index));
    writeBytes(SetCommand.groupCurrent(group, index));
  }

  /**
   * Sends a command to set the over output voltage protection.
   *
   * @param maxVoltageInV The maximum output voltage in V.
   */
  public void setOverVoltageProtectionInV(final float maxVoltageInV) {
    writeBytes(SetCommand.overVoltageProtectionInV(maxVoltageInV));
  }

  /**
   * Sends a command to set the over current protection.
   *
   * @param maxCurrentInA The maximum current in A.
   */
  public void setOverCurrentProtectionInA(final float maxCurrentInA) {
    writeBytes(SetCommand.overCurrentProtectionInA(maxCurrentInA));
  }

  /**
   * Sends a command to set the over power protection.
   *
   * @param maxPowerInW The maximum power in W.
   */
  public void setOverPowerProtectionInW(final float maxPowerInW) {
    writeBytes(SetCommand.overPowerProtectionInW(maxPowerInW));
  }

  /**
   * Sends a command to set the over temperature protection.
   *
   * @param maxTemperatureInC The maximum temperature in °C.
   */
  public void setOverTemperatureProtectionInC(final float maxTemperatureInC) {
    writeBytes(SetCommand.overTemperatureProtectionInC(maxTemperatureInC));
  }

  /**
   * Sends a command to set the under input voltage protection.
   *
   * @param minVoltageInV The minimum input voltage in V.
   */
  public void setUnderVoltageProtectionInV(final float minVoltageInV) {
    writeBytes(SetCommand.underVoltageProtectionInV(minVoltageInV));
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

  /**
   * The connected state.
   *
   * @return True, if the serial port is currently connected.
   */
  public boolean isConnected() {
    return serialPort.isOpen();
  }

  private void onShutdown() {
    disconnect();
    removeAllListeners();
  }

}
