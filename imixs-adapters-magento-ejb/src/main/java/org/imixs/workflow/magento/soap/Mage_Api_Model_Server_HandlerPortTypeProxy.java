package org.imixs.workflow.magento.soap;

public class Mage_Api_Model_Server_HandlerPortTypeProxy implements Mage_Api_Model_Server_HandlerPortType {
  private String _endpoint = null;
  private Mage_Api_Model_Server_HandlerPortType mage_Api_Model_Server_HandlerPortType = null;
  
  public Mage_Api_Model_Server_HandlerPortTypeProxy() {
    _initMage_Api_Model_Server_HandlerPortTypeProxy();
  }
  
  public Mage_Api_Model_Server_HandlerPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMage_Api_Model_Server_HandlerPortTypeProxy();
  }
  
  private void _initMage_Api_Model_Server_HandlerPortTypeProxy() {
    try {
      mage_Api_Model_Server_HandlerPortType = (new MagentoSOAPServiceLocator()).getMage_Api_Model_Server_HandlerPort();
      if (mage_Api_Model_Server_HandlerPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mage_Api_Model_Server_HandlerPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mage_Api_Model_Server_HandlerPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mage_Api_Model_Server_HandlerPortType != null)
      ((javax.xml.rpc.Stub)mage_Api_Model_Server_HandlerPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Mage_Api_Model_Server_HandlerPortType getMage_Api_Model_Server_HandlerPortType() {
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType;
  }
  
  public java.lang.Object call(java.lang.String sessionId, java.lang.String resourcePath, java.lang.Object args) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.call(sessionId, resourcePath, args);
  }
  
  public java.lang.Object[] multiCall(java.lang.String sessionId, java.lang.Object[] calls, java.lang.Object options) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.multiCall(sessionId, calls, options);
  }
  
  public boolean endSession(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.endSession(sessionId);
  }
  
  public java.lang.String login(java.lang.String username, java.lang.String apiKey) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.login(username, apiKey);
  }
  
  public java.lang.String startSession() throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.startSession();
  }
  
  public java.lang.Object[] resources(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.resources(sessionId);
  }
  
  public java.lang.Object[] globalFaults(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.globalFaults(sessionId);
  }
  
  public java.lang.Object[] resourceFaults(java.lang.String resourceName, java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_HandlerPortType == null)
      _initMage_Api_Model_Server_HandlerPortTypeProxy();
    return mage_Api_Model_Server_HandlerPortType.resourceFaults(resourceName, sessionId);
  }
  
  
}