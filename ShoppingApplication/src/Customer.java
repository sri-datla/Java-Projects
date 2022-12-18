import java.util.ArrayList;
public class Customer {
	String custName;
	ArrayList<Product> shoppingList = new ArrayList<Product>();
	double moneyLeft;
	public Customer(String custName, double moneyLeft)
	{
		this.custName = custName;
		this.moneyLeft = moneyLeft;
	}
	@Override
	public String toString()
	{
		return "Welcome "+this.custName+"!!" + "\n"
				+ "Balance on your card: $"+this.moneyLeft;
	}
}
