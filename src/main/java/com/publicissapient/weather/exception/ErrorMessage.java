package com.publicissapient.weather.exception;

import java.util.Date;

/**
 * The Class ErrorMessage.
 * 
 * @author Vinit Rathi
 * @version 1.0
 * @since 2022-09-05
 */
public class ErrorMessage {

    /** The status code. */
    private int statusCode;

    /** The timestamp. */
    private Date timestamp;

    /** The message. */
    private String message;

    /** The description. */
    private String description;

    /**
     * Instantiates a new error message.
     *
     * @param statusCode  the status code
     * @param timestamp   the timestamp
     * @param message     the message
     * @param description the description
     */
    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    /**
     * Gets the status code.
     *
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
