/**
 * 
 */
package com.retail.cart.shoppingcart.domain;

import java.io.Serializable;

/**
 * @author rahulkatare
 *
 */
public class FieldError implements Serializable {

	private static final long serialVersionUID = 8671686787763174179L;

	private String field;

	private String message;

	private Object[] value;

	/**
	 * Default Constructor
	 */
	public FieldError() {
	}

	/**
	 * Field errors for RestResponse
	 * 
	 * @param field
	 * @param value
	 * @param message
	 */
	public FieldError(String field, Object[] value, String message) {
		this.field = field;
		this.message = message;
		this.value = value;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the value
	 */
	public Object[] getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object[] value) {
		this.value = value;
	}

}
