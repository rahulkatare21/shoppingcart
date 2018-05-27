/**
 * 
 */
package com.retail.cart.shoppingcart.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author rahulkatare
 *
 */
public class OrderSummary implements Serializable{
	
	
	private static final long serialVersionUID = 8702968042931935185L;
	
	private List<Product> products;
	
	private BigDecimal totalPrice;
	
	private BigDecimal totalPriceAfterTax;
	
	private Double taxRate;

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * 
	 * @param totalPrice
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getTotalPriceAfterTax() {
		return totalPriceAfterTax;
	}

	/**
	 * 
	 * @param totalPriceAfterTax
	 */
	public void setTotalPriceAfterTax(BigDecimal totalPriceAfterTax) {
		this.totalPriceAfterTax = totalPriceAfterTax;
	}

	/**
	 * @return the taxRate
	 */
	public Double getTaxRate() {
		return taxRate;
	}

	/**
	 * @param taxRate the taxRate to set
	 */
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

}
