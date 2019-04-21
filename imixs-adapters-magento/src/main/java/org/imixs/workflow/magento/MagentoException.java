package org.imixs.workflow.magento;

import org.imixs.workflow.exceptions.PluginException;

public class MagentoException extends PluginException{

	
	private static final long serialVersionUID = 1L;

	public MagentoException(String aErrorContext, String aErrorCode,
			String message) {
		super(aErrorContext, aErrorCode, message);
		
	}

	public MagentoException(String aErrorContext, String aErrorCode,
			String message, Exception e) {
		super(aErrorContext, aErrorCode, message, e);
	}

	public MagentoException(String aErrorContext, String aErrorCode,
			String message, Object[] params) {
		super(aErrorContext, aErrorCode, message, params);
	}

}
