/**
 * 
 */
package com.retail.cart.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.retail.cart.shoppingcart.domain.OrderSummary;
import com.retail.cart.shoppingcart.domain.Product;

/**
 * @author rahulkatare
 *
 */
@Component
public interface ShopService {

	/**
	 * Add product into inventory, cache
	 * 
	 * @param product
	 * @param products
	 * @throws Exception
	 */
	public boolean addProducts(Product product, List<Product> products) throws Exception;

	/**
	 * Get order summary for list of products
	 * 
	 * @param products
	 */
	public OrderSummary getOrderSummary(List<Product> products) throws Exception;

}
