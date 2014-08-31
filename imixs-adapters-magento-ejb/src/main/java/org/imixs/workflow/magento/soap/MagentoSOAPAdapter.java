/*******************************************************************************
 *  Imixs Workflow 
 *  Copyright (C) 2001, 2011, 2012, 2013, 2014 Imixs Software Solutions GmbH,  
 *  http://www.imixs.com
 *  
 *  This program is free software; you can redistribute it and/or 
 *  modify it under the terms of the GNU General Public License 
 *  as published by the Free Software Foundation; either version 2 
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of 
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 *  General Public License for more details.
 *  
 *  You can receive a copy of the GNU General Public
 *  License at http://www.gnu.org/licenses/gpl.html
 *  
 *  Project: 
 *  	http://www.imixs.org
 *  	https://github.com/imixs
 *  
 *  Contributors:  
 *  	Imixs Software Solutions GmbH - initial API and implementation
 *  	Ralph Soika - Software Developer
 *******************************************************************************/

package org.imixs.workflow.magento.soap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.imixs.workflow.ItemCollection;
import org.imixs.workflow.magento.soap.generated.CatalogProductEntity;
import org.imixs.workflow.magento.soap.generated.CatalogProductReturnEntity;
import org.imixs.workflow.magento.soap.generated.CustomerCustomerEntity;
import org.imixs.workflow.magento.soap.generated.SalesOrderAddressEntity;
import org.imixs.workflow.magento.soap.generated.SalesOrderEntity;

/**
 * Adapter Class to convert Magento SOAP Client Object Classes into
 * ItemCollections
 * 
 * @author rsoika
 * 
 */
public class MagentoSOAPAdapter {

	public static ItemCollection adapt(CatalogProductReturnEntity entity) {
		if (entity == null) {
			return null;
		}
		ItemCollection product = new ItemCollection();

		product.replaceItemValue("categories",
				arrayToList(entity.getCategories()));

		product.replaceItemValue("category_ids",
				arrayToList(entity.getCategory_ids()));
		product.replaceItemValue("created_at", entity.getCreated_at());
		product.replaceItemValue("custom_design", entity.getCustom_design());
		product.replaceItemValue("custom_layout_update",
				entity.getCustom_layout_update());
		product.replaceItemValue("description", entity.getDescription());
		product.replaceItemValue("Meta_description",
				entity.getMeta_description());
		product.replaceItemValue("Meta_keyword", entity.getMeta_keyword());
		product.replaceItemValue("Meta_title", entity.getMeta_title());
		product.replaceItemValue("Name", entity.getName());
		product.replaceItemValue("Description", entity.getDescription());
		product.replaceItemValue("Price", entity.getPrice());
		product.replaceItemValue("Product_id", entity.getProduct_id());
		product.replaceItemValue("Short_description",
				entity.getShort_description());
		product.replaceItemValue("sku", entity.getSku());
		product.replaceItemValue("Special_from_date",
				entity.getSpecial_from_date());
		product.replaceItemValue("Special_price", entity.getSpecial_price());
		product.replaceItemValue("Special_to_date", entity.getSpecial_to_date());
		product.replaceItemValue("status", entity.getStatus());
		product.replaceItemValue("Tax_class_id", entity.getTax_class_id());
		product.replaceItemValue("type", entity.getType());
		product.replaceItemValue("type_id", entity.getType_id());
		product.replaceItemValue("Updated_at", entity.getUpdated_at());
		product.replaceItemValue("Url_key", entity.getUrl_key());
		product.replaceItemValue("Url_path", entity.getUrl_path());
		product.replaceItemValue("Visibility", entity.getVisibility());
		product.replaceItemValue("Website_ids",
				arrayToList(entity.getWebsite_ids()));
		product.replaceItemValue("Websites", arrayToList(entity.getWebsites()));
		product.replaceItemValue("Weight", entity.getWeight());

		return product;
	}

	
	
	

	public static ItemCollection adapt(CatalogProductEntity entity) {
		if (entity == null) {
			return null;
		}
		ItemCollection product = new ItemCollection();


		product.replaceItemValue("category_ids",
				arrayToList(entity.getCategory_ids()));
		product.replaceItemValue("Name", entity.getName());
		product.replaceItemValue("Product_id", entity.getProduct_id());
		product.replaceItemValue("sku", entity.getSku());
		product.replaceItemValue("type", entity.getType());
		product.replaceItemValue("Website_ids",
				arrayToList(entity.getWebsite_ids()));

		return product;
	}

	

	public static ItemCollection adapt(CustomerCustomerEntity entity) {
		if (entity == null) {
			return null;
		}
		ItemCollection customer = new ItemCollection();


		customer.replaceItemValue("Created_at", entity.getCreated_at());
		customer.replaceItemValue("Created_in", entity.getCreated_in());
		customer.replaceItemValue("customer_id", entity.getCustomer_id());
		customer.replaceItemValue("Email", entity.getEmail());
		customer.replaceItemValue("FirstName", entity.getFirstname());
		customer.replaceItemValue("Group_ID", entity.getGroup_id());
		customer.replaceItemValue("Increment_id", entity.getIncrement_id());
		customer.replaceItemValue("LastName", entity.getLastname());
		customer.replaceItemValue("MiddleName", entity.getMiddlename());
		customer.replaceItemValue("Store_id", entity.getStore_id());
		customer.replaceItemValue("taxvat", entity.getTaxvat());
		customer.replaceItemValue("updated_at", entity.getUpdated_at());
		customer.replaceItemValue("Website_id", entity.getWebsite_id());

		return customer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ItemCollection adapt(SalesOrderEntity entity) {
		if (entity == null) {
			return null;
		}
		ItemCollection customer = new ItemCollection();


		customer.replaceItemValue("Created_at", entity.getCreated_at());
		customer.replaceItemValue("customer_id", entity.getCustomer_id());
		customer.replaceItemValue("Increment_id", entity.getIncrement_id());
		customer.replaceItemValue("Store_id", entity.getStore_id());
		customer.replaceItemValue("updated_at", entity.getUpdated_at());

		customer.replaceItemValue("order_id", entity.getOrder_id());
		customer.replaceItemValue("state", entity.getState());
		customer.replaceItemValue("status", entity.getStatus());
		
		
		
		Vector v=new Vector();
		
		SalesOrderAddressEntity addressEntity = entity.getBilling_address();
		ItemCollection address=MagentoSOAPAdapter.adapt(addressEntity);
		address.replaceItemValue("billing", address);

		v.add(address);

		 addressEntity = entity.getShipping_address();
		 address=MagentoSOAPAdapter.adapt(addressEntity);
		address.replaceItemValue("shipping", address);
		v.add(address);

		customer.replaceItemValue("addresses",v);
		
		
		return customer;
	}
	
	
	public static ItemCollection adapt(SalesOrderAddressEntity entity) {
		if (entity == null) {
			return null;
		}
		ItemCollection customer = new ItemCollection();


		customer.replaceItemValue("Created_at", entity.getCreated_at());
		customer.replaceItemValue("FirstName", entity.getFirstname());
		customer.replaceItemValue("Increment_id", entity.getIncrement_id());
		customer.replaceItemValue("LastName", entity.getLastname());
		customer.replaceItemValue("updated_at", entity.getUpdated_at());


		customer.replaceItemValue("Company", entity.getCompany());
		customer.replaceItemValue("city", entity.getCity());
		customer.replaceItemValue("Country_id", entity.getCountry_id());
		customer.replaceItemValue("Postcode", entity.getPostcode());
		customer.replaceItemValue("region", entity.getRegion());
		customer.replaceItemValue("street", entity.getStreet());
		customer.replaceItemValue("Telephone", entity.getTelephone());
		

		return customer;
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List arrayToList(Object[] o) {
		if (o == null)
			return null;
		else
			return new ArrayList(Arrays.asList(o));
	}
}
