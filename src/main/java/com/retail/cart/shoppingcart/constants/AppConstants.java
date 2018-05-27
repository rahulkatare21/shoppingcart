/**
 * 
 */
package com.retail.cart.shoppingcart.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rahulkatare
 *
 */
public interface AppConstants {

	public static final String CREATE_PRODUCT = "/products";
	public static final String CREATE_ORDER_SUMMARY = "/orders";
	public static final String PRODUCT = "product";

	public static final Map<String, String> FIELDERROR = new HashMap<String, String>() {
		private static final long serialVersionUID = 3367012231267626692L;
		{
			put("id", "id");
			put("price","price");
			put("quantity","quantity");
			put("name","name");
		}
	};
}
