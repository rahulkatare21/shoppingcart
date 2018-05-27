/**
 * 
 */
package com.retail.cart.shoppingcart.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rahulkatare
 *
 */
public class RestResponse implements Serializable {

	private static final long serialVersionUID = 3683852550801842920L;

	private Object object;

	private String statusCode;

	private String statusMessage;

	private List<FieldError> rejectedFields;

	/**
	 * Default constructor to initialize the rejected fields list.
	 */
	public RestResponse() {
		this.rejectedFields = new ArrayList<>();
	}

	/**
	 * Adds Field error
	 * 
	 * @param path
	 * @param message
	 * @param domain
	 */
	public void addRejectedFields(String field, Object[] value, String message) {
		FieldError error = new FieldError(field, value, message);
		rejectedFields.add(error);
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage
	 *            the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the rejectedFields
	 */
	public List<FieldError> getRejectedFields() {
		return rejectedFields;
	}

	/**
	 * @param rejectedFields
	 *            the rejectedFields to set
	 */
	public void setRejectedFields(List<FieldError> rejectedFields) {
		this.rejectedFields = rejectedFields;
	}

}
