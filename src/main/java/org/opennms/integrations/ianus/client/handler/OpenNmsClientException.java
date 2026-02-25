package org.opennms.integrations.ianus.client.handler;

public class OpenNmsClientException extends RuntimeException {

    public OpenNmsClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
