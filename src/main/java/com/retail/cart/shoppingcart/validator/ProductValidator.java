package com.retail.cart.shoppingcart.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.retail.cart.shoppingcart.domain.Product;

/**
 * 
 * @author rahulkatare
 *
 */
@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		try {
			Product product = (Product) target;

			if (StringUtils.isEmpty(product.getName())) {
				errors.reject("name", new Object[] { product.getName() },
						"name of the product can not be blank or null");
			}

			if (product.getId() == null || product.getId() == 0) {
				errors.reject("id", new Object[] { product.getId() }, "Id of the product can not be 0");
			}

			if (product.getQuantity() == null || product.getQuantity() == 0) {
				errors.reject("quantity", new Object[] { product.getId() }, "Quantity of the product can not be 0");
			}

			if (product.getPrice() == null || product.getPrice() == 0) {
				errors.reject("price", new Object[] { product.getId() }, "Price of the product can not be 0");
			}

		} catch (Exception e) {
			errors.reject(null, new Object[] {}, "error while validating products");
		}
	}

}
