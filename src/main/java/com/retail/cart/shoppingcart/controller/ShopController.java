/**
 * 
 */
package com.retail.cart.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.cart.shoppingcart.constants.AppConstants;
import com.retail.cart.shoppingcart.domain.OrderSummary;
import com.retail.cart.shoppingcart.domain.Product;
import com.retail.cart.shoppingcart.domain.RestResponse;
import com.retail.cart.shoppingcart.service.ShopService;
import com.retail.cart.shoppingcart.validator.ProductValidator;

/**
 * @author rahulkatare
 *
 */
@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductValidator productValidator;

	private List<Product> products = new ArrayList<Product>();

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(productValidator);
	}

	/**
	 * 
	 * @param product
	 * @param errors
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = AppConstants.CREATE_PRODUCT, method = RequestMethod.POST)
	public ResponseEntity<RestResponse> addProduct(@Valid @RequestBody Product product, BindingResult errors)
			throws Exception {
		ResponseEntity<RestResponse> response = null;
		// Validating the User request in ProductValidator class using @Valid. Errors
		// are added into bindingResults
		RestResponse restResponse = createRestResponseFromErrors(errors);

		if (restResponse != null) {
			restResponse.setObject(product.getId());
			response = new ResponseEntity<RestResponse>(restResponse, HttpStatus.BAD_REQUEST);
		} else if (product != null) {
			restResponse = new RestResponse();
			boolean productCreated = shopService.addProducts(product, products);
			if (productCreated) {
				restResponse.setObject(products);
				restResponse.setStatusCode(HttpStatus.CREATED.toString());
			} else {
				restResponse.setObject(product);
				restResponse.setStatusCode(HttpStatus.CONFLICT.toString());
				restResponse.setStatusMessage("This product is already exists in product list");
			}
			response = new ResponseEntity<RestResponse>(restResponse, HttpStatus.CREATED);
		}
		return response;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = AppConstants.CREATE_ORDER_SUMMARY, method = RequestMethod.GET)
	public ResponseEntity<OrderSummary> getOrderSummary() throws Exception {
		ResponseEntity<OrderSummary> response = null;
		if (!CollectionUtils.isEmpty(products)) {
			OrderSummary orderSummary = shopService.getOrderSummary(products);
			response = new ResponseEntity<OrderSummary>(orderSummary, HttpStatus.OK);
		}
		return response;
	}

	/**
	 * If any errors are available than retrive validation message
	 * 
	 * @param errors
	 * @return
	 */
	private RestResponse createRestResponseFromErrors(BindingResult errors) {
		RestResponse errorResponse = null;
		if (errors.hasErrors()) {
			errorResponse = new RestResponse();
			List<ObjectError> fieldErrors = errors.getAllErrors();
			for (ObjectError fieldError : fieldErrors) {
				String localizedErrorMessage = AppConstants.FIELDERROR.get(fieldError.getCodes()[1]);
				String message = fieldError.getDefaultMessage();
				Object[] value = fieldError.getArguments();
				errorResponse.addRejectedFields(localizedErrorMessage, value, message);
			}
		}
		return errorResponse;
	}
}
