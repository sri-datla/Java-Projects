
public class Product {
	
	String productName;
	double price;
	int productId;
	double discount;
	
	public Product(String productName, double price, int productId, double discount)
	{
		this.productName = productName;
		this.price = price;
		this.productId = productId;
		this.discount = (discount/100.0);
	}


}
