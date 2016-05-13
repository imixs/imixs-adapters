package org.imixs.workflow.datev;

import org.imixs.workflow.exceptions.PluginException;

public class DatevException extends PluginException{

	
	private static final long serialVersionUID = 1L;

	public DatevException(String aErrorContext, String aErrorCode,
			String message) {
		super(aErrorContext, aErrorCode, message);
		
	}

	public DatevException(String aErrorContext, String aErrorCode,
			String message, Exception e) {
		super(aErrorContext, aErrorCode, message, e);
	}

	public DatevException(String aErrorContext, String aErrorCode,
			String message, Object[] params) {
		super(aErrorContext, aErrorCode, message, params);
	}

}
