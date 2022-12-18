import javax.swing.JOptionPane;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inventory[] inv = new Inventory[3];
		inv[0] = new Inventory("Egg",10,4.99,1,20);
		inv[1] = new Inventory("Apple",1,2.99,2,20);
		inv[2] = new Inventory("Toothpaste",3,2.99,3,10);
		Customer cust1 = new Customer("Test ABC", 10.5);
		JOptionPane.showMessageDialog(null,cust1.toString());
		Shopping inst1 = new Shopping(inv, cust1);
		inst1.setVisible(true);

	}

}
