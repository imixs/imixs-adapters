package org.imixs.workflow.datev;

import org.imixs.workflow.exceptions.PluginException;

public class DatevException extends PluginException {

	public static final String DATEV_EXPORT_ERROR = "DATEV_EXPORT_ERROR";
	public static final String DATEV_IMPORT_ERROR = "DATEV_IMPORT_ERROR";
	public static final String DATEV_CONFIG_ERROR = "DATEV_CONFIG_ERROR";
	public static final String DATEV_REPORT_ERROR = "DATEV_REPORT_ERROR";
	public static final String DATEV_MODEL_ERROR = "DATEV_MODEL_ERROR";
	public static final String DATEV_DATA_ERROR = "DATEV_DATA_ERROR";

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
