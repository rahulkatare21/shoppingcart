package com.retail.cart.shoppingcart.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.retail.cart.shoppingcart.domain.Product;
import com.retail.cart.shoppingcart.domain.RestResponse;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class ShoppingCartSteps {

	@Autowired
	TestRestTemplate restTemplate;

	ResponseEntity<RestResponse> response = null;

	Product product = new Product();

	@Given("^Add product name \"([^\"]*)\" quantity (\\d+), price \"([^\"]*)\" and id (\\d+)$")
	public void add_product_name_quantity_price_and_id(String name, Integer quantity, String price, Long id)
			throws Throwable {

		product.setId(id);
		product.setName(name);
		product.setPrice(Double.valueOf(price));
		product.setQuantity(quantity);

		assertThat(product.getId()).isEqualTo(id);
		assertThat(product.getName()).isEqualTo(name);
		assertThat(product.getPrice()).isEqualTo(Double.valueOf(price));
		assertThat(product.getQuantity()).isEqualTo(quantity);
	}

	@When("^product are added into the cart$")
	public void product_are_added_into_the_cart() throws Throwable {
		response = restTemplate.postForEntity("/products", product, RestResponse.class);
		assertThat(response).isNotNull();
	}

	@Then("^return (\\d+) status code$")
	public void return_status_code(int statusCode) throws Throwable {
		assertThat(response.getStatusCodeValue()).isEqualTo(statusCode);
	}

	@Then("^should show error message \"([^\"]*)\"$")
	public void should_show_error_message(String errorMeesage) throws Throwable {
		assertThat(response.getBody().getRejectedFields().get(0).getMessage()).isEqualTo(errorMeesage);
	}

}
