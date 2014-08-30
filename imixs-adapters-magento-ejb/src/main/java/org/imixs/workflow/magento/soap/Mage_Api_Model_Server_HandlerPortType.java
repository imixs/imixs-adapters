/**
 * Mage_Api_Model_Server_HandlerPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.imixs.workflow.magento.soap;

public interface Mage_Api_Model_Server_HandlerPortType extends java.rmi.Remote {

    /**
     * Call api functionality
     */
    public java.lang.Object call(java.lang.String sessionId, java.lang.String resourcePath, java.lang.Object args) throws java.rmi.RemoteException;

    /**
     * Multiple calls of resource functionality
     */
    public java.lang.Object[] multiCall(java.lang.String sessionId, java.lang.Object[] calls, java.lang.Object options) throws java.rmi.RemoteException;

    /**
     * End web service session
     */
    public boolean endSession(java.lang.String sessionId) throws java.rmi.RemoteException;

    /**
     * Login user and retrive session id
     */
    public java.lang.String login(java.lang.String username, java.lang.String apiKey) throws java.rmi.RemoteException;

    /**
     * Start web service session
     */
    public java.lang.String startSession() throws java.rmi.RemoteException;

    /**
     * List of available resources
     */
    public java.lang.Object[] resources(java.lang.String sessionId) throws java.rmi.RemoteException;

    /**
     * List of resource faults
     */
    public java.lang.Object[] globalFaults(java.lang.String sessionId) throws java.rmi.RemoteException;

    /**
     * List of global faults
     */
    public java.lang.Object[] resourceFaults(java.lang.String resourceName, java.lang.String sessionId) throws java.rmi.RemoteException;
}
