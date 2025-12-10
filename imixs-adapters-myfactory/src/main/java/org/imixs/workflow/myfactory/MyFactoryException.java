package org.imixs.workflow.myfactory;

/**
 * Default Exception
 */
public class MyFactoryException extends Exception {
    public MyFactoryException(String message) {
        super(message);
    }

    public MyFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
