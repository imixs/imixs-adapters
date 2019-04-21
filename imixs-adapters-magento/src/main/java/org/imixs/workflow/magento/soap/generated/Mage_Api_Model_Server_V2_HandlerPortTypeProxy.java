package org.imixs.workflow.magento.soap.generated;

public class Mage_Api_Model_Server_V2_HandlerPortTypeProxy implements Mage_Api_Model_Server_V2_HandlerPortType {
  private String _endpoint = null;
  private Mage_Api_Model_Server_V2_HandlerPortType mage_Api_Model_Server_V2_HandlerPortType = null;
  
  public Mage_Api_Model_Server_V2_HandlerPortTypeProxy() {
    _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
  }
  
  public Mage_Api_Model_Server_V2_HandlerPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
  }
  
  private void _initMage_Api_Model_Server_V2_HandlerPortTypeProxy() {
    try {
      mage_Api_Model_Server_V2_HandlerPortType = (new MagentoServiceLocator()).getMage_Api_Model_Server_V2_HandlerPort();
      if (mage_Api_Model_Server_V2_HandlerPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mage_Api_Model_Server_V2_HandlerPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mage_Api_Model_Server_V2_HandlerPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mage_Api_Model_Server_V2_HandlerPortType != null)
      ((javax.xml.rpc.Stub)mage_Api_Model_Server_V2_HandlerPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Mage_Api_Model_Server_V2_HandlerPortType getMage_Api_Model_Server_V2_HandlerPortType() {
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType;
  }
  
  public boolean endSession(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.endSession(sessionId);
  }
  
  public java.lang.String login(java.lang.String username, java.lang.String apiKey) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.login(username, apiKey);
  }
  
  public java.lang.String startSession() throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.startSession();
  }
  
  public ApiEntity[] resources(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.resources(sessionId);
  }
  
  public ExistsFaltureEntity[] globalFaults(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.globalFaults(sessionId);
  }
  
  public ExistsFaltureEntity[] resourceFaults(java.lang.String resourceName, java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.resourceFaults(resourceName, sessionId);
  }
  
  public StoreEntity[] storeList(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.storeList(sessionId);
  }
  
  public StoreEntity storeInfo(java.lang.String sessionId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.storeInfo(sessionId, storeId);
  }
  
  public MagentoInfoEntity magentoInfo(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.magentoInfo(sessionId);
  }
  
  public DirectoryCountryEntity[] directoryCountryList(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.directoryCountryList(sessionId);
  }
  
  public DirectoryRegionEntity[] directoryRegionList(java.lang.String sessionId, java.lang.String country) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.directoryRegionList(sessionId, country);
  }
  
  public CustomerCustomerEntity[] customerCustomerList(java.lang.String sessionId, Filters filters) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerCustomerList(sessionId, filters);
  }
  
  public int customerCustomerCreate(java.lang.String sessionId, CustomerCustomerEntityToCreate customerData) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerCustomerCreate(sessionId, customerData);
  }
  
  public CustomerCustomerEntity customerCustomerInfo(java.lang.String sessionId, int customerId, java.lang.String[] attributes) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerCustomerInfo(sessionId, customerId, attributes);
  }
  
  public boolean customerCustomerUpdate(java.lang.String sessionId, int customerId, CustomerCustomerEntityToCreate customerData) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerCustomerUpdate(sessionId, customerId, customerData);
  }
  
  public boolean customerCustomerDelete(java.lang.String sessionId, int customerId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerCustomerDelete(sessionId, customerId);
  }
  
  public CustomerGroupEntity[] customerGroupList(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerGroupList(sessionId);
  }
  
  public CustomerAddressEntityItem[] customerAddressList(java.lang.String sessionId, int customerId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerAddressList(sessionId, customerId);
  }
  
  public int customerAddressCreate(java.lang.String sessionId, int customerId, CustomerAddressEntityCreate addressData) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerAddressCreate(sessionId, customerId, addressData);
  }
  
  public CustomerAddressEntityItem customerAddressInfo(java.lang.String sessionId, int addressId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerAddressInfo(sessionId, addressId);
  }
  
  public boolean customerAddressUpdate(java.lang.String sessionId, int addressId, CustomerAddressEntityCreate addressData) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerAddressUpdate(sessionId, addressId, addressData);
  }
  
  public boolean customerAddressDelete(java.lang.String sessionId, int addressId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.customerAddressDelete(sessionId, addressId);
  }
  
  public int catalogCategoryCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryCurrentStore(sessionId, storeView);
  }
  
  public CatalogCategoryTree catalogCategoryTree(java.lang.String sessionId, java.lang.String parentId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryTree(sessionId, parentId, storeView);
  }
  
  public CatalogCategoryEntityNoChildren[] catalogCategoryLevel(java.lang.String sessionId, java.lang.String website, java.lang.String storeView, java.lang.String parentCategory) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryLevel(sessionId, website, storeView, parentCategory);
  }
  
  public CatalogCategoryInfo catalogCategoryInfo(java.lang.String sessionId, int categoryId, java.lang.String storeView, java.lang.String[] attributes) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryInfo(sessionId, categoryId, storeView, attributes);
  }
  
  public int catalogCategoryCreate(java.lang.String sessionId, int parentId, CatalogCategoryEntityCreate categoryData, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryCreate(sessionId, parentId, categoryData, storeView);
  }
  
  public boolean catalogCategoryUpdate(java.lang.String sessionId, int categoryId, CatalogCategoryEntityCreate categoryData, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryUpdate(sessionId, categoryId, categoryData, storeView);
  }
  
  public boolean catalogCategoryMove(java.lang.String sessionId, int categoryId, int parentId, java.lang.String afterId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryMove(sessionId, categoryId, parentId, afterId);
  }
  
  public boolean catalogCategoryDelete(java.lang.String sessionId, int categoryId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryDelete(sessionId, categoryId);
  }
  
  public CatalogAssignedProduct[] catalogCategoryAssignedProducts(java.lang.String sessionId, int categoryId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryAssignedProducts(sessionId, categoryId);
  }
  
  public boolean catalogCategoryAssignProduct(java.lang.String sessionId, int categoryId, java.lang.String product, java.lang.String position, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryAssignProduct(sessionId, categoryId, product, position, productIdentifierType);
  }
  
  public boolean catalogCategoryUpdateProduct(java.lang.String sessionId, int categoryId, java.lang.String product, java.lang.String position, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryUpdateProduct(sessionId, categoryId, product, position, productIdentifierType);
  }
  
  public boolean catalogCategoryRemoveProduct(java.lang.String sessionId, int categoryId, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryRemoveProduct(sessionId, categoryId, product, productIdentifierType);
  }
  
  public int catalogCategoryAttributeCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryAttributeCurrentStore(sessionId, storeView);
  }
  
  public CatalogAttributeEntity[] catalogCategoryAttributeList(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryAttributeList(sessionId);
  }
  
  public CatalogAttributeOptionEntity[] catalogCategoryAttributeOptions(java.lang.String sessionId, java.lang.String attributeId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogCategoryAttributeOptions(sessionId, attributeId, storeView);
  }
  
  public int catalogProductCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCurrentStore(sessionId, storeView);
  }
  
  public CatalogProductEntity[] catalogProductList(java.lang.String sessionId, Filters filters, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductList(sessionId, filters, storeView);
  }
  
  public CatalogProductReturnEntity catalogProductInfo(java.lang.String sessionId, java.lang.String product, java.lang.String storeView, CatalogProductRequestAttributes attributes, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductInfo(sessionId, product, storeView, attributes, productIdentifierType);
  }
  
  public int catalogProductCreate(java.lang.String sessionId, java.lang.String type, java.lang.String set, java.lang.String sku, CatalogProductCreateEntity productData, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCreate(sessionId, type, set, sku, productData, storeView);
  }
  
  public boolean catalogProductUpdate(java.lang.String sessionId, java.lang.String product, CatalogProductCreateEntity productData, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductUpdate(sessionId, product, productData, storeView, productIdentifierType);
  }
  
  public int catalogProductSetSpecialPrice(java.lang.String sessionId, java.lang.String product, java.lang.String specialPrice, java.lang.String fromDate, java.lang.String toDate, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductSetSpecialPrice(sessionId, product, specialPrice, fromDate, toDate, storeView, productIdentifierType);
  }
  
  public CatalogProductReturnEntity catalogProductGetSpecialPrice(java.lang.String sessionId, java.lang.String product, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductGetSpecialPrice(sessionId, product, storeView, productIdentifierType);
  }
  
  public int catalogProductDelete(java.lang.String sessionId, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductDelete(sessionId, product, productIdentifierType);
  }
  
  public int catalogProductAttributeCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeCurrentStore(sessionId, storeView);
  }
  
  public CatalogAttributeEntity[] catalogProductListOfAdditionalAttributes(java.lang.String sessionId, java.lang.String productType, java.lang.String attributeSetId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductListOfAdditionalAttributes(sessionId, productType, attributeSetId);
  }
  
  public CatalogAttributeEntity[] catalogProductAttributeList(java.lang.String sessionId, int setId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeList(sessionId, setId);
  }
  
  public CatalogAttributeOptionEntity[] catalogProductAttributeOptions(java.lang.String sessionId, java.lang.String attributeId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeOptions(sessionId, attributeId, storeView);
  }
  
  public int catalogProductAttributeSetCreate(java.lang.String sessionId, java.lang.String attributeSetName, java.lang.String skeletonSetId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetCreate(sessionId, attributeSetName, skeletonSetId);
  }
  
  public boolean catalogProductAttributeSetRemove(java.lang.String sessionId, java.lang.String attributeSetId, java.lang.String forceProductsRemove) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetRemove(sessionId, attributeSetId, forceProductsRemove);
  }
  
  public CatalogProductAttributeSetEntity[] catalogProductAttributeSetList(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetList(sessionId);
  }
  
  public boolean catalogProductAttributeSetAttributeAdd(java.lang.String sessionId, java.lang.String attributeId, java.lang.String attributeSetId, java.lang.String attributeGroupId, java.lang.String sortOrder) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetAttributeAdd(sessionId, attributeId, attributeSetId, attributeGroupId, sortOrder);
  }
  
  public boolean catalogProductAttributeSetAttributeRemove(java.lang.String sessionId, java.lang.String attributeId, java.lang.String attributeSetId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetAttributeRemove(sessionId, attributeId, attributeSetId);
  }
  
  public int catalogProductAttributeSetGroupAdd(java.lang.String sessionId, java.lang.String attributeSetId, java.lang.String groupName) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetGroupAdd(sessionId, attributeSetId, groupName);
  }
  
  public boolean catalogProductAttributeSetGroupRename(java.lang.String sessionId, java.lang.String groupId, java.lang.String groupName) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetGroupRename(sessionId, groupId, groupName);
  }
  
  public boolean catalogProductAttributeSetGroupRemove(java.lang.String sessionId, java.lang.String attributeGroupId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeSetGroupRemove(sessionId, attributeGroupId);
  }
  
  public CatalogAttributeOptionEntity[] catalogProductAttributeTypes(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeTypes(sessionId);
  }
  
  public int catalogProductAttributeCreate(java.lang.String sessionId, CatalogProductAttributeEntityToCreate data) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeCreate(sessionId, data);
  }
  
  public boolean catalogProductAttributeRemove(java.lang.String sessionId, java.lang.String attribute) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeRemove(sessionId, attribute);
  }
  
  public CatalogProductAttributeEntity catalogProductAttributeInfo(java.lang.String sessionId, java.lang.String attribute) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeInfo(sessionId, attribute);
  }
  
  public boolean catalogProductAttributeUpdate(java.lang.String sessionId, java.lang.String attribute, CatalogProductAttributeEntityToUpdate data) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeUpdate(sessionId, attribute, data);
  }
  
  public boolean catalogProductAttributeAddOption(java.lang.String sessionId, java.lang.String attribute, CatalogProductAttributeOptionEntityToAdd data) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeAddOption(sessionId, attribute, data);
  }
  
  public boolean catalogProductAttributeRemoveOption(java.lang.String sessionId, java.lang.String attribute, java.lang.String optionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeRemoveOption(sessionId, attribute, optionId);
  }
  
  public CatalogProductTypeEntity[] catalogProductTypeList(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductTypeList(sessionId);
  }
  
  public CatalogProductTierPriceEntity[] catalogProductAttributeTierPriceInfo(java.lang.String sessionId, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeTierPriceInfo(sessionId, product, productIdentifierType);
  }
  
  public int catalogProductAttributeTierPriceUpdate(java.lang.String sessionId, java.lang.String product, CatalogProductTierPriceEntity[] tier_price, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeTierPriceUpdate(sessionId, product, tier_price, productIdentifierType);
  }
  
  public int catalogProductAttributeMediaCurrentStore(java.lang.String sessionId, java.lang.String storeView) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaCurrentStore(sessionId, storeView);
  }
  
  public CatalogProductImageEntity[] catalogProductAttributeMediaList(java.lang.String sessionId, java.lang.String product, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaList(sessionId, product, storeView, productIdentifierType);
  }
  
  public CatalogProductImageEntity catalogProductAttributeMediaInfo(java.lang.String sessionId, java.lang.String product, java.lang.String file, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaInfo(sessionId, product, file, storeView, productIdentifierType);
  }
  
  public CatalogProductAttributeMediaTypeEntity[] catalogProductAttributeMediaTypes(java.lang.String sessionId, java.lang.String setId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaTypes(sessionId, setId);
  }
  
  public java.lang.String catalogProductAttributeMediaCreate(java.lang.String sessionId, java.lang.String product, CatalogProductAttributeMediaCreateEntity data, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaCreate(sessionId, product, data, storeView, productIdentifierType);
  }
  
  public boolean catalogProductAttributeMediaUpdate(java.lang.String sessionId, java.lang.String product, java.lang.String file, CatalogProductAttributeMediaCreateEntity data, java.lang.String storeView, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaUpdate(sessionId, product, file, data, storeView, productIdentifierType);
  }
  
  public boolean catalogProductAttributeMediaRemove(java.lang.String sessionId, java.lang.String product, java.lang.String file, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductAttributeMediaRemove(sessionId, product, file, productIdentifierType);
  }
  
  public CatalogProductLinkEntity[] catalogProductLinkList(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductLinkList(sessionId, type, product, productIdentifierType);
  }
  
  public boolean catalogProductLinkAssign(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String linkedProduct, CatalogProductLinkEntity data, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductLinkAssign(sessionId, type, product, linkedProduct, data, productIdentifierType);
  }
  
  public boolean catalogProductLinkUpdate(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String linkedProduct, CatalogProductLinkEntity data, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductLinkUpdate(sessionId, type, product, linkedProduct, data, productIdentifierType);
  }
  
  public boolean catalogProductLinkRemove(java.lang.String sessionId, java.lang.String type, java.lang.String product, java.lang.String linkedProduct, java.lang.String productIdentifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductLinkRemove(sessionId, type, product, linkedProduct, productIdentifierType);
  }
  
  public java.lang.String[] catalogProductLinkTypes(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductLinkTypes(sessionId);
  }
  
  public CatalogProductLinkAttributeEntity[] catalogProductLinkAttributes(java.lang.String sessionId, java.lang.String type) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductLinkAttributes(sessionId, type);
  }
  
  public boolean catalogProductCustomOptionAdd(java.lang.String sessionId, java.lang.String productId, CatalogProductCustomOptionToAdd data, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionAdd(sessionId, productId, data, store);
  }
  
  public boolean catalogProductCustomOptionUpdate(java.lang.String sessionId, java.lang.String optionId, CatalogProductCustomOptionToUpdate data, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionUpdate(sessionId, optionId, data, store);
  }
  
  public CatalogProductCustomOptionTypesEntity[] catalogProductCustomOptionTypes(java.lang.String sessionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionTypes(sessionId);
  }
  
  public CatalogProductCustomOptionInfoEntity catalogProductCustomOptionInfo(java.lang.String sessionId, java.lang.String optionId, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionInfo(sessionId, optionId, store);
  }
  
  public CatalogProductCustomOptionListEntity[] catalogProductCustomOptionList(java.lang.String sessionId, java.lang.String productId, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionList(sessionId, productId, store);
  }
  
  public boolean catalogProductCustomOptionRemove(java.lang.String sessionId, java.lang.String optionId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionRemove(sessionId, optionId);
  }
  
  public CatalogProductCustomOptionValueInfoEntity catalogProductCustomOptionValueInfo(java.lang.String sessionId, java.lang.String valueId, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionValueInfo(sessionId, valueId, store);
  }
  
  public CatalogProductCustomOptionValueListEntity[] catalogProductCustomOptionValueList(java.lang.String sessionId, java.lang.String optionId, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionValueList(sessionId, optionId, store);
  }
  
  public boolean catalogProductCustomOptionValueAdd(java.lang.String sessionId, java.lang.String optionId, CatalogProductCustomOptionValueAddEntity[] data, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionValueAdd(sessionId, optionId, data, store);
  }
  
  public boolean catalogProductCustomOptionValueUpdate(java.lang.String sessionId, java.lang.String valueId, CatalogProductCustomOptionValueUpdateEntity data, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionValueUpdate(sessionId, valueId, data, storeId);
  }
  
  public boolean catalogProductCustomOptionValueRemove(java.lang.String sessionId, java.lang.String valueId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductCustomOptionValueRemove(sessionId, valueId);
  }
  
  public SalesOrderListEntity[] salesOrderList(java.lang.String sessionId, Filters filters) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderList(sessionId, filters);
  }
  
  public SalesOrderEntity salesOrderInfo(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInfo(sessionId, orderIncrementId);
  }
  
  public boolean salesOrderAddComment(java.lang.String sessionId, java.lang.String orderIncrementId, java.lang.String status, java.lang.String comment, java.lang.String notify) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderAddComment(sessionId, orderIncrementId, status, comment, notify);
  }
  
  public boolean salesOrderHold(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderHold(sessionId, orderIncrementId);
  }
  
  public boolean salesOrderUnhold(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderUnhold(sessionId, orderIncrementId);
  }
  
  public boolean salesOrderCancel(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderCancel(sessionId, orderIncrementId);
  }
  
  public SalesOrderShipmentEntity[] salesOrderShipmentList(java.lang.String sessionId, Filters filters) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentList(sessionId, filters);
  }
  
  public SalesOrderShipmentEntity salesOrderShipmentInfo(java.lang.String sessionId, java.lang.String shipmentIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentInfo(sessionId, shipmentIncrementId);
  }
  
  public java.lang.String salesOrderShipmentCreate(java.lang.String sessionId, java.lang.String orderIncrementId, OrderItemIdQty[] itemsQty, java.lang.String comment, int email, int includeComment) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentCreate(sessionId, orderIncrementId, itemsQty, comment, email, includeComment);
  }
  
  public boolean salesOrderShipmentAddComment(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String comment, java.lang.String email, java.lang.String includeInEmail) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentAddComment(sessionId, shipmentIncrementId, comment, email, includeInEmail);
  }
  
  public int salesOrderShipmentAddTrack(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String carrier, java.lang.String title, java.lang.String trackNumber) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentAddTrack(sessionId, shipmentIncrementId, carrier, title, trackNumber);
  }
  
  public boolean salesOrderShipmentSendInfo(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String comment) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentSendInfo(sessionId, shipmentIncrementId, comment);
  }
  
  public boolean salesOrderShipmentRemoveTrack(java.lang.String sessionId, java.lang.String shipmentIncrementId, java.lang.String trackId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentRemoveTrack(sessionId, shipmentIncrementId, trackId);
  }
  
  public AssociativeEntity[] salesOrderShipmentGetCarriers(java.lang.String sessionId, java.lang.String orderIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderShipmentGetCarriers(sessionId, orderIncrementId);
  }
  
  public SalesOrderInvoiceEntity[] salesOrderInvoiceList(java.lang.String sessionId, Filters filters) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceList(sessionId, filters);
  }
  
  public SalesOrderInvoiceEntity salesOrderInvoiceInfo(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceInfo(sessionId, invoiceIncrementId);
  }
  
  public java.lang.String salesOrderInvoiceCreate(java.lang.String sessionId, java.lang.String invoiceIncrementId, OrderItemIdQty[] itemsQty, java.lang.String comment, java.lang.String email, java.lang.String includeComment) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceCreate(sessionId, invoiceIncrementId, itemsQty, comment, email, includeComment);
  }
  
  public boolean salesOrderInvoiceAddComment(java.lang.String sessionId, java.lang.String invoiceIncrementId, java.lang.String comment, java.lang.String email, java.lang.String includeComment) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceAddComment(sessionId, invoiceIncrementId, comment, email, includeComment);
  }
  
  public boolean salesOrderInvoiceCapture(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceCapture(sessionId, invoiceIncrementId);
  }
  
  public boolean salesOrderInvoiceVoid(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceVoid(sessionId, invoiceIncrementId);
  }
  
  public boolean salesOrderInvoiceCancel(java.lang.String sessionId, java.lang.String invoiceIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderInvoiceCancel(sessionId, invoiceIncrementId);
  }
  
  public SalesOrderCreditmemoEntity[] salesOrderCreditmemoList(java.lang.String sessionId, Filters filters) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderCreditmemoList(sessionId, filters);
  }
  
  public SalesOrderCreditmemoEntity salesOrderCreditmemoInfo(java.lang.String sessionId, java.lang.String creditmemoIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderCreditmemoInfo(sessionId, creditmemoIncrementId);
  }
  
  public java.lang.String salesOrderCreditmemoCreate(java.lang.String sessionId, java.lang.String creditmemoIncrementId, SalesOrderCreditmemoData creditmemoData, java.lang.String comment, int notifyCustomer, int includeComment, java.lang.String refundToStoreCreditAmount) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderCreditmemoCreate(sessionId, creditmemoIncrementId, creditmemoData, comment, notifyCustomer, includeComment, refundToStoreCreditAmount);
  }
  
  public boolean salesOrderCreditmemoAddComment(java.lang.String sessionId, java.lang.String creditmemoIncrementId, java.lang.String comment, int notifyCustomer, int includeComment) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderCreditmemoAddComment(sessionId, creditmemoIncrementId, comment, notifyCustomer, includeComment);
  }
  
  public boolean salesOrderCreditmemoCancel(java.lang.String sessionId, java.lang.String creditmemoIncrementId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.salesOrderCreditmemoCancel(sessionId, creditmemoIncrementId);
  }
  
  public CatalogInventoryStockItemEntity[] catalogInventoryStockItemList(java.lang.String sessionId, java.lang.String[] products) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogInventoryStockItemList(sessionId, products);
  }
  
  public int catalogInventoryStockItemUpdate(java.lang.String sessionId, java.lang.String product, CatalogInventoryStockItemUpdateEntity data) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogInventoryStockItemUpdate(sessionId, product, data);
  }
  
  public int shoppingCartCreate(java.lang.String sessionId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartCreate(sessionId, storeId);
  }
  
  public ShoppingCartInfoEntity shoppingCartInfo(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartInfo(sessionId, quoteId, storeId);
  }
  
  public java.lang.String shoppingCartOrder(java.lang.String sessionId, int quoteId, java.lang.String storeId, java.lang.String[] licenses) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartOrder(sessionId, quoteId, storeId, licenses);
  }
  
  public ShoppingCartTotalsEntity[] shoppingCartTotals(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartTotals(sessionId, quoteId, storeId);
  }
  
  public ShoppingCartLicenseEntity[] shoppingCartLicense(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartLicense(sessionId, quoteId, storeId);
  }
  
  public boolean shoppingCartProductAdd(java.lang.String sessionId, int quoteId, ShoppingCartProductEntity[] products, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartProductAdd(sessionId, quoteId, products, storeId);
  }
  
  public boolean shoppingCartProductUpdate(java.lang.String sessionId, int quoteId, ShoppingCartProductEntity[] products, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartProductUpdate(sessionId, quoteId, products, storeId);
  }
  
  public boolean shoppingCartProductRemove(java.lang.String sessionId, int quoteId, ShoppingCartProductEntity[] products, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartProductRemove(sessionId, quoteId, products, storeId);
  }
  
  public CatalogProductEntity[] shoppingCartProductList(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartProductList(sessionId, quoteId, storeId);
  }
  
  public boolean shoppingCartProductMoveToCustomerQuote(java.lang.String sessionId, int quoteId, ShoppingCartProductEntity[] products, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartProductMoveToCustomerQuote(sessionId, quoteId, products, storeId);
  }
  
  public boolean shoppingCartCustomerSet(java.lang.String sessionId, int quoteId, ShoppingCartCustomerEntity customer, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartCustomerSet(sessionId, quoteId, customer, storeId);
  }
  
  public boolean shoppingCartCustomerAddresses(java.lang.String sessionId, int quoteId, ShoppingCartCustomerAddressEntity[] customer, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartCustomerAddresses(sessionId, quoteId, customer, storeId);
  }
  
  public boolean shoppingCartShippingMethod(java.lang.String sessionId, int quoteId, java.lang.String method, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartShippingMethod(sessionId, quoteId, method, storeId);
  }
  
  public ShoppingCartShippingMethodEntity[] shoppingCartShippingList(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartShippingList(sessionId, quoteId, storeId);
  }
  
  public boolean shoppingCartPaymentMethod(java.lang.String sessionId, int quoteId, ShoppingCartPaymentMethodEntity method, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartPaymentMethod(sessionId, quoteId, method, storeId);
  }
  
  public ShoppingCartPaymentMethodResponseEntityArray shoppingCartPaymentList(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartPaymentList(sessionId, quoteId, storeId);
  }
  
  public boolean shoppingCartCouponAdd(java.lang.String sessionId, int quoteId, java.lang.String couponCode, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartCouponAdd(sessionId, quoteId, couponCode, storeId);
  }
  
  public boolean shoppingCartCouponRemove(java.lang.String sessionId, int quoteId, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.shoppingCartCouponRemove(sessionId, quoteId, storeId);
  }
  
  public CatalogProductTagListEntity[] catalogProductTagList(java.lang.String sessionId, java.lang.String productId, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductTagList(sessionId, productId, store);
  }
  
  public CatalogProductTagInfoEntity catalogProductTagInfo(java.lang.String sessionId, java.lang.String tagId, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductTagInfo(sessionId, tagId, store);
  }
  
  public AssociativeEntity[] catalogProductTagAdd(java.lang.String sessionId, CatalogProductTagAddEntity data) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductTagAdd(sessionId, data);
  }
  
  public boolean catalogProductTagUpdate(java.lang.String sessionId, java.lang.String tagId, CatalogProductTagUpdateEntity data, java.lang.String store) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductTagUpdate(sessionId, tagId, data, store);
  }
  
  public boolean catalogProductTagRemove(java.lang.String sessionId, java.lang.String tagId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductTagRemove(sessionId, tagId);
  }
  
  public GiftMessageResponse giftMessageSetForQuote(java.lang.String sessionId, java.lang.String quoteId, GiftMessageEntity giftMessage, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.giftMessageSetForQuote(sessionId, quoteId, giftMessage, storeId);
  }
  
  public GiftMessageResponse giftMessageSetForQuoteItem(java.lang.String sessionId, java.lang.String quoteItemId, GiftMessageEntity giftMessage, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.giftMessageSetForQuoteItem(sessionId, quoteItemId, giftMessage, storeId);
  }
  
  public GiftMessageResponse[] giftMessageSetForQuoteProduct(java.lang.String sessionId, java.lang.String quoteId, GiftMessageAssociativeProductsEntity[] productsAndMessages, java.lang.String storeId) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.giftMessageSetForQuoteProduct(sessionId, quoteId, productsAndMessages, storeId);
  }
  
  public int catalogProductDownloadableLinkAdd(java.lang.String sessionId, java.lang.String productId, CatalogProductDownloadableLinkAddEntity resource, java.lang.String resourceType, java.lang.String store, java.lang.String identifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductDownloadableLinkAdd(sessionId, productId, resource, resourceType, store, identifierType);
  }
  
  public CatalogProductDownloadableLinkInfoEntity catalogProductDownloadableLinkList(java.lang.String sessionId, java.lang.String productId, java.lang.String store, java.lang.String identifierType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductDownloadableLinkList(sessionId, productId, store, identifierType);
  }
  
  public boolean catalogProductDownloadableLinkRemove(java.lang.String sessionId, java.lang.String linkId, java.lang.String resourceType) throws java.rmi.RemoteException{
    if (mage_Api_Model_Server_V2_HandlerPortType == null)
      _initMage_Api_Model_Server_V2_HandlerPortTypeProxy();
    return mage_Api_Model_Server_V2_HandlerPortType.catalogProductDownloadableLinkRemove(sessionId, linkId, resourceType);
  }
  
  
}