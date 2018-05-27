package com.retail.cart.shoppingcart.domain;

import java.io.Serializable;

/**
 * @author rahulkatare
 *
 */
public class Product implements Serializable {

	private static final long serialVersionUID = -7739250476477333580L;

	private Long id;

	private Double price;

	private String name;

	private Integer quantity;

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null) {
			try {
				final Product product = (Product) obj;
				if (product != null) {
					equals = id.equals(product.getId());
				}
			} catch (Exception e) {
				equals = false;
			}
		}
		return equals;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		if (id != null) {
			hash = id.intValue();
		}
		return hash;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
