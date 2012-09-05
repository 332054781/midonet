/*
 * Copyright 2011 Midokura KK
 * Copyright 2012 Midokura PTE LTD.
 */
package com.midokura.midolman.mgmt.network;

import java.net.URI;
import java.util.UUID;

/**
 * Logical port interface
 *
 */
public interface LogicalPort {

    /**
     * @return　Peer port ID
     */
    UUID getPeerId();

    /**
     * @param peerId
     *            peer ID to set
     */
    void setPeerId(UUID peerId);

    /**
     * @return URI of the peer
     */
    URI getPeer();

    /**
     * @return URI to link
     */
    URI getLink();

}
