Feature: Add products Features

Scenario: Add products into the cart 
	Given Add product name "apple" quantity 1, price "10.0" and id 1 
	When product are added into the cart 
	Then return 201 status code 
	
	
Scenario: Add another products into the cart 
	Given Add product name "orange" quantity 1, price "5.0" and id 2 
	When product are added into the cart 
	Then return 201 status code 
	
	
Scenario: Add another products which does not have a price 
	Given Add product name "orange" quantity 1, price "0.0" and id 2 
	When product are added into the cart 
	Then return 400 status code
	And should show error message "Price of the product can not be 0" 
	
	
Scenario: Add another products which does not have quantity 
	Given Add product name "orange" quantity 0, price "2.0" and id 2 
	When product are added into the cart 
	Then return 400 status code 
	And should show error message "Quantity of the product can not be 0" 
	
	
Scenario: Add another products which does not have name 
	Given Add product name "" quantity 1, price "2.0" and id 1 
	When product are added into the cart 
	Then return 400 status code 
	And should show error message "name of the product can not be blank or null" 
	
Scenario: Add another products which does not have id 
	Given Add product name "apple" quantity 1, price "2.0" and id 0 
	When product are added into the cart 
	Then return 400 status code 
	And should show error message "Id of the product can not be 0"