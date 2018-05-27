package com.retail.cart.shoppingcart.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.cart.shoppingcart.domain.OrderSummary;
import com.retail.cart.shoppingcart.domain.Product;
import com.retail.cart.shoppingcart.service.ShopService;

/**
 * Class responsible for implementation details of different shopping methods
 * ie. addProduct, removeProduct, orderSummary
 * 
 * @author rahulkatare
 *
 */
@Service
public class ShopServiceImpl implements ShopService {

	@Override
	public boolean addProducts(Product product, List<Product> products) throws Exception {

		boolean productCreated = false;
		if (products.indexOf(product) == -1) {
			products.add(product);
			productCreated = true;
		}
		return productCreated;
	}

	@Override
	public OrderSummary getOrderSummary(List<Product> products) throws Exception {
		Double taxRate = 12.5d;
		Double totalPrice = 0d;

		for (Product product : products) {
			totalPrice += product.getPrice() * product.getQuantity();
		}
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setProducts(products);
		orderSummary.setTotalPrice(BigDecimal.valueOf(totalPrice));
		orderSummary.setTaxRate(taxRate);
		BigDecimal totalPriceAfterTax = BigDecimal.valueOf(((taxRate * totalPrice) / 100));
		orderSummary.setTotalPriceAfterTax(BigDecimal.valueOf(totalPrice).add(totalPriceAfterTax));

		return orderSummary;
	}
}
