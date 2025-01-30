package io.github.openhelios.fnirsi.dps.protocol.response;

/**
 * Interface for a version response.
 */
public interface VersionResponse extends Response {

  /**
   * The version, e.g. V1.0
   *
   * @return The version.
   */
  String version();

}
