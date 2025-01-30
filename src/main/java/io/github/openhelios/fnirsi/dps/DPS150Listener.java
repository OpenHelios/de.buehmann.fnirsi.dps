package io.github.openhelios.fnirsi.dps;

import io.github.openhelios.fnirsi.dps.protocol.response.Response;

/**
 * Listener for the values received from a DPS-150.
 */
public interface DPS150Listener {

  /**
   * A response from the DPS-150 has been received.
   *
   * @param response There are several specific sub types to represent the values in a type safe way.
   */
  void onMessage(Response response);

}
