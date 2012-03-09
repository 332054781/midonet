/*
 * Copyright 2012 Midokura Europe SARL
 */
package com.midokura.midolman.mgmt.data.dao;

import java.util.List;
import java.util.UUID;

import com.midokura.midolman.mgmt.data.dto.Host;
import com.midokura.midolman.mgmt.data.dto.HostCommand;
import com.midokura.midolman.mgmt.data.dto.Interface;
import com.midokura.midolman.state.StateAccessException;

/**
 * @author Mihai Claudiu Toader <mtoader@midokura.com>
 *         Date: 1/30/12
 */
public interface HostDao {

    /**
     * Delete a host.
     *
     * @param id ID of the host we want to delete.
     * @throws StateAccessException if a data access error occurs or the host
     *                              entry can't be deleted because it's alive.
     */
    void delete(UUID id) throws StateAccessException;

    /**
     * Get a host information.
     *
     * @param id ID of the host we want to get.
     * @return Host object.
     * @throws StateAccessException if a data access error occurs.
     */
    Host get(UUID id) throws StateAccessException;

    /**
     * List hosts.
     *
     * @return A list of Host objects (that can be modified).
     * @throws StateAccessException if a data access error occurs.
     */
    List<Host> list() throws StateAccessException;

    /**
     * Removes an interface description from a host.
     *
     * @param hostId      the host uuid
     * @param interfaceId the interface uuid
     * @throws StateAccessException if the deletion fails
     */
    void deleteInterface(UUID hostId, UUID interfaceId)
        throws StateAccessException;

    /**
     * Lists all the interface information available on a host.
     *
     * @param hostId the host uuid
     * @return the list of interface data available on a host (that can be modified).
     * @throws StateAccessException if the operation fails
     */
    List<Interface> listInterfaces(UUID hostId) throws StateAccessException;

    /**
     * Returns the information about an interface available on a host.
     *
     * @param hostId      the host uuid
     * @param interfaceId the interface uuid
     * @return an object encapsulating interface data
     * @throws StateAccessException if the operation fails
     */
    Interface getInterface(UUID hostId, UUID interfaceId)
        throws StateAccessException;

    /**
     * It will convert the target interface description into a set of commands
     * that are to be executed by the client.
     *
     * @param hostId    is the if of the host on which the commands are
     *                  to be executed
     * @param interfaceId   interfaceId that we want to change (may be null)
     * @param interfaceData is the interface description that we want created
     *
     * @return the command abstraction of this interface
     * @throws StateAccessException if the operation fails
     */
    HostCommand createCommandForInterfaceUpdate(UUID hostId, UUID interfaceId,
                                                Interface interfaceData)
        throws StateAccessException;

    /**
     * It will list all the network interface commands registered for this host.
     *
     * @param hostId is the host id for which we want to commands to be retrieved.
     *
     * @return the list of currently known command objects.
     */
    List<HostCommand> listCommands(UUID hostId) throws StateAccessException;

    /**
     * Returns the information about a command that was assigned to be executed
     * on a host.
     *
     * @param hostId host host uuid
     * @param id     the command id
     * @return       the object representation
     *
     * @throws StateAccessException if the datastore access fails
     */
    HostCommand getCommand(UUID hostId, Integer id) throws StateAccessException;

    /**
     * Removes a command from the list of commands associated with a host.
     *
     * @param hostId is the host uuid
     * @param id     is the id of the command that we want to have removed
     */
    void deleteCommand(UUID hostId, Integer id) throws StateAccessException;
}
