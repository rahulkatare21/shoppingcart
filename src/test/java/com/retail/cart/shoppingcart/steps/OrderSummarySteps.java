/**
 * 
 */
package com.retail.cart.shoppingcart.steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.retail.cart.shoppingcart.domain.OrderSummary;
import com.retail.cart.shoppingcart.domain.Product;
import com.retail.cart.shoppingcart.domain.RestResponse;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.deps.com.google.gson.Gson;

/**
 * @author rahulkatare
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class OrderSummarySteps {

	@Autowired
	TestRestTemplate restTemplate;

	ResponseEntity<RestResponse> response = null;

	ResponseEntity<OrderSummary> orderSummaryResponse = null;

	@Given("^One product is already added into the product list$")
	public void one_product_is_already_added_into_the_product_list() throws Throwable {
		Product product = new Product();
		product.setId(1l);
		product.setName("apple");
		product.setPrice(10d);
		product.setQuantity(2);
		response = restTemplate.postForEntity("/products", product, RestResponse.class);
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@When("^Order summary api is called$")
	public void order_summary_api_is_called() throws Throwable {
		orderSummaryResponse = restTemplate.getForEntity("/orders", OrderSummary.class);
		assertThat(orderSummaryResponse).isNotNull();
	}

	@Then("^return api response code is (\\d+)$")
	public void return_api_response_code_is(int statusCode) throws Throwable {
		assertThat(orderSummaryResponse.getStatusCodeValue()).isEqualTo(statusCode);
	}

	@Then("^total price of the products are \"([^\"]*)\"$")
	public void total_price_of_the_products_are(String totalPrice) throws Throwable {
		assertThat(orderSummaryResponse.getBody().getTotalPrice()).isEqualTo(new BigDecimal(totalPrice));
	}

	@Then("^price of the product is \"([^\"]*)\"$")
	public void price_of_the_product_is(String price) throws Throwable {
		Double applePrice = orderSummaryResponse.getBody().getProducts().get(0).getPrice()
				* orderSummaryResponse.getBody().getProducts().get(0).getQuantity();
		assertThat(applePrice).isEqualTo(Double.valueOf(price));

	}

	@Then("^total price after tax should be \"([^\"]*)\"$")
	public void total_price_after_tax_should_be(String afterTaxTotalPrice) throws Throwable {
		assertThat(orderSummaryResponse.getBody().getTotalPriceAfterTax())
				.isEqualTo(new BigDecimal(afterTaxTotalPrice));
	}

	@Given("^Two Products are already added into the product list$")
	public void two_Products_are_already_added_into_the_product_list() throws Throwable {
		ResponseEntity<RestResponse> productsResponse = null;
		Product orangeProduct = new Product();
		orangeProduct.setId(2l);
		orangeProduct.setName("orange");
		orangeProduct.setPrice(10d);
		orangeProduct.setQuantity(1);
		productsResponse = restTemplate.postForEntity("/products", orangeProduct, RestResponse.class);

		System.out.println("response after adding 2 apple and 1 orange-->>" + new Gson().toJson(productsResponse));
		assertThat(productsResponse).isNotNull();
		assertThat(productsResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Then("^price of the apple are \"([^\"]*)\"$")
	public void price_of_the_apple_are(String applePrice) throws Throwable {
		assertThat(orderSummaryResponse.getBody().getProducts().get(0).getPrice()
				* orderSummaryResponse.getBody().getProducts().get(0).getQuantity())
						.isEqualTo(Double.valueOf(applePrice));

	}

	@Then("^price of the orange is \"([^\"]*)\"$")
	public void price_of_the_orange_is(String orangePrice) throws Throwable {
		assertThat(orderSummaryResponse.getBody().getProducts().get(1).getPrice()
				* orderSummaryResponse.getBody().getProducts().get(1).getQuantity())
						.isEqualTo(Double.valueOf(orangePrice));
	}
}
