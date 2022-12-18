1.	Created an application named Shopping Application. 

2.	Created a Product class, which has attributes:  
a.	Product ID: Unique ID of the product;
b.	Product name: the name of the product; 
c.	Price: the price of the product; 
d.	Discount: Discount percentage on each product;

3.	Created an Inventory class, which has these attributes:  
a.	Product:  the product (using the Product class); 
b.	Stock: the number of the product in stock; 
c.	Overridden ToString() method to print custom output;

4.	Created Customer class, which has these attributes:  
a.	name: customer name; 
b.	Shopping list: Adds the product to cart each time customer adds a product;
c.	Balance: money left; 
d.	Overridden ToString() method to print custom output;

5.	Created the Main Class, In this class, created an Inventory array and initialized using the following data   
Name 	Stock 	Price 	Discount 
Egg 	10 	4.99		20% 
Apple 	1	2.99		20% 
Toothpaste 3	1.99		10% 

6.	Also created a Customer instance using the following data 
Name 	Balance 
Test ABC 	10.5 


7.	Initialized a shopping instance where GUI is built and application starts executing. 

8.	Created the Shopping class, has 6 methods

9.	Application will take user’s input as integer from 1 to 5. If user’s input, is not valid, displaying the prompt again. If user’s input is valid, then defined different methods to respond to user’s requests, 

10.	DisplayInventory():  displayed the current inventory including name, stock, price and discount; 

11.	AddItem(): display prompt to ask user input the product they like and add it to the shopping list and the application also update the stock accordingly i.e., reduce it by one.  

12.	DeleteItem(): delete the item user select. Displaying the shopping list first and then ask user which item to be removed, and we update the inventory stock, i.e., add one.  

13.	Checkout(): checkout the shopping list by add the price of the items up and apply the discount. If users’ balance is sufficient for checking out, deduct it from the balance.

If the user’s balance is not sufficient, then delete the item with highest price  and tell user that one item is deleted and update the stock of the corresponding items. Then try again until user’s balance is sufficient.  

14.	Quit(): user quit, remove all the items in the shopping list, and update the stock level and closes the application.

15.	writeToFile(): Writes the final items list with price customer has paid to the file like a receipt.
