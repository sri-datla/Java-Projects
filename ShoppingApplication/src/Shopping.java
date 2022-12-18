import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Shopping extends JFrame implements ActionListener{
	String[] menu = new String[5];
	JButton[] buttons = new JButton[5];
	Inventory[] inv;
	Customer cust;
	JPanel panel1 = new JPanel(new GridLayout(5,1));
	JPanel panel2 = new JPanel(new FlowLayout());
	JLabel label1 = new JLabel("Click to select from the options below: ");
	ArrayList<String> removedItemsFromTheCart= new ArrayList<String>();
	public Shopping(Inventory[] inv, Customer cust)
	{
		super("Shopping Application");
		menu[0] = "(1)	Display the inventory";
		menu[1] = "(2)	Add item to your shopping list";
		menu[2] = "(3)	Delete item from your shopping list";
		menu[3] = "(4)	Checkout ";
		menu[4] = "(5)	Quit ";
		this.inv = inv;
		this.cust = cust;
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		con.add(panel1, BorderLayout.CENTER);
		con.add(panel2,BorderLayout.NORTH);
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i] = new JButton();
			buttons[i].setText(menu[i]);
			buttons[i].setActionCommand(menu[i]);
			buttons[i].addActionListener(this);
			panel1.add(buttons[i]);
		}
		panel2.add(label1);
		setVisible(true);
	}

	
	public void DisplayInventory()
	{
		
		String temp = "Items available to purchase: \n";
		temp += "(1)"+inv[0].toString()+"\n";
		temp +=  "(2)"+inv[1].toString()+"\n";
		temp += "(3)"+inv[2].toString() + "\n";
		JOptionPane.showMessageDialog(null, temp);
		
	}
	
	public void AddItem()
	{
		
		String temp = "Items available to purchase: \n";
		temp += "(1)"+inv[0].toString()+"\n";
		temp +=  "(2)"+inv[1].toString()+"\n";
		temp += "(3)"+inv[2].toString() + "\n";
		int userInput = Integer.parseInt((JOptionPane.showInputDialog(null, temp)));
		switch(userInput)
		{
		case 1: 
			AddItemInv(inv[0]);
			break;
		case 2:
			AddItemInv(inv[1]);
			break;
		case 3:
			AddItemInv(inv[2]);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Invalid selection");
			break;
		
		}
	}
	
	public void AddItemInv(Inventory inv)
	{
		String temp = "";
		if(inv.productStock < 1)
		{
			JOptionPane.showMessageDialog(null, "Sorry! the product went out of stock. Please try a different product");
		}
		else
		{
			cust.shoppingList.add(inv.product);
			temp += "You selected "+ inv.product.productName + " and "+ inv.product.productName +" will be added to your shopping list.\n"
					;
		;
			inv.productStock -= 1;
		}
		temp += "Your updated shopping list is: \n";
		for(int i=0;i<cust.shoppingList.size();i++)
		{
			temp += cust.shoppingList.get(i).productName + " , " + cust.shoppingList.get(i).price+"\n";
			
		}
		JOptionPane.showMessageDialog(null,temp);	
	}
	
	
	
	public void DeleteItem()
	{
		if(cust.shoppingList.size()>0)
		{
			String temp = ("Here is your shopping list. Which one do you want to delete?\n");
			for(int i=0;i<cust.shoppingList.size();i++)
			{
				temp += ((i+1) + ")" + cust.shoppingList.get(i).productName + " , " + cust.shoppingList.get(i).price)+"\n";
			}
			int selection = Integer.parseInt(JOptionPane.showInputDialog(temp));
			if(selection > cust.shoppingList.size() || selection < 0)
			{
				JOptionPane.showMessageDialog(null,"You entered incorrect input! Please try again.");
			}
			else
			{

				inv[cust.shoppingList.get(selection-1).productId-1].productStock +=1;
				cust.shoppingList.remove(selection-1);
				temp = ("OK, item "+selection+ " is deleted.\n");
				temp += "Click Ok to see the updated shopping list";
				JOptionPane.showMessageDialog(null, temp);
				if(cust.shoppingList.size() == 0)
				{
					JOptionPane.showMessageDialog(null,"Sorry, there are no items in the shopping list");
				}
				else
				{
					temp = "Items in the cart: \n";
					for(int i=0;i<cust.shoppingList.size();i++)
					{
						temp += (cust.shoppingList.get(i).productName + " , " + cust.shoppingList.get(i).price+"\n");
					}
					JOptionPane.showMessageDialog(null,temp);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You do not have any items in the cart to remove");
		}
		
		
	}
	
	public void Checkout()
	{
		double total = 0.0;
		double discountedPrice = 0.0;
		String temp = "";
		if(cust.shoppingList.size() > 0)
		{
			for(int i=0;i<cust.shoppingList.size();i++)
			{
				total += cust.shoppingList.get(i).price;
				discountedPrice += cust.shoppingList.get(i).price - (cust.shoppingList.get(i).price * cust.shoppingList.get(i).discount);
			}
			
			if(discountedPrice <= cust.moneyLeft )
			{
				
				cust.moneyLeft -= discountedPrice;
				temp = ("These are the items you have selected: \n");
				for(int i=0;i<cust.shoppingList.size();i++)
				{
					temp+=(cust.shoppingList.get(i).productName + " , " + cust.shoppingList.get(i).price+"\n");
				}
				temp += (String.format("\nYour total price is $%.2f",total)+"\n"+
				(String.format("Amount to pay after applying discount is: $%.2f",discountedPrice))+
				String.format(" and your new balance is $%.2f",(cust.moneyLeft)));
				temp+=("\nThank you for shopping with us!");
				writeToFile(temp);
				JOptionPane.showMessageDialog(null, temp);
				System.exit(0);
			}
			else
			{
				temp = String.format("Your total is: $%.2f",discountedPrice) + ". Sorry, you do not have enough balance on your card. "
						+ "We will try deleting the item with highest price. Press Ok to continue.";
				JOptionPane.showMessageDialog(null,
						temp);	
				removeHighestPriceOrder();
				Checkout();
			
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "You do not have any items in the cart");
		}
		
	}
	
	public void Quit()
	{
		String temp = ("You didn't buy anything and your balance is: $"+cust.moneyLeft+"\nSee you next time!");
		for(int i=0;i<cust.shoppingList.size();i++)
		{
			
			inv[cust.shoppingList.get(i).productId-1].productStock +=1;
		}
		JOptionPane.showMessageDialog(null, temp);
		System.exit(0);
	}
	
	public void removeHighestPriceOrder()
	{
		int index = 0;
		double max = 0;
		for(int i=0;i<cust.shoppingList.size();i++)
		{
			if(max < cust.shoppingList.get(i).price)
			{
				max = cust.shoppingList.get(i).price;
				index = i;
			}
		}
		inv[cust.shoppingList.get(index).productId-1].productStock++;
		System.out.println("Product Name : "+cust.shoppingList.get(index).productName+" Price: "+cust.shoppingList.get(index).price);
		cust.shoppingList.remove(index);
	
	}
	public void writeToFile(String temp)
	{
		
		 try {
		      File myObj = new File("300359545.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		 try {
		      FileWriter myWriter = new FileWriter("300359545.txt");
		      myWriter.write(temp);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals(menu[0]))
		{
			DisplayInventory();
		}
		else if(e.getActionCommand().equals(menu[1]))
		{
			AddItem();
		}
		else if(e.getActionCommand().equals(menu[2]))
		{
			DeleteItem();
		}
		else if(e.getActionCommand().equals(menu[3]))
		{
			Checkout();
		}
		else
		{
			Quit();
		}
		
	}

}
