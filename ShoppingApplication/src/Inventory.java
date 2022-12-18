
public class Inventory {
	
	public Product product;
	public int productStock;
	public Inventory(String productName, int productStock, double price, int productId, double discount)
	{
		this.product = new Product(productName, price, productId, discount);
		this.productStock = productStock;
	}
	
	
	public String toString()
	{
		return "Product: "+this.product.productName + " , Available Stock: " + this.productStock  + " , Price: " +
	this.product.price;
}
}
