Feature: Order Summary Features 

Scenario: Products (2 apple ) are added than show individual price,total price   
	Given One product is already added into the product list 
	When Order summary api is called 
	Then return api response code is 200 
	And total price of the products are "20.0" 
	And price of the product is "20.0" 
	And total price after tax should be "22.5" 
	
	
Scenario: Products (2 apple and 1 orange) is added than show individual price,total price 
	Given Two Products are already added into the product list 
	When Order summary api is called 
	Then return api response code is 200 
	And total price of the products are "30.0" 
	And price of the apple are "20.0" 
	And price of the orange is "10.0" 
	And total price after tax should be "33.75"