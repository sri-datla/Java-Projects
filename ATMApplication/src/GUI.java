import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener{
	JButton[][] buttons = new JButton[4][3];
	JPanel panel1 = new JPanel(new FlowLayout());
	JPanel panel2 = new JPanel(new GridLayout(4,3));
	JLabel label1 = new JLabel("Enter your card number: ");
	JTextField textfield1 = new JTextField(12);
	String userInputCardNumber = "";
	String userInputPinNumber = "";
	int enterCounter = 0;
	int tryLogin = 0;
	int isAccountLocked = 0;
	String UserName;
	String DB_URL = "jdbc:derby:atmdb";
	Connection conn;
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	public GUI() throws SQLException
	{
		super("ATM");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		con.add(panel1, BorderLayout.NORTH);
		con.add(panel2, BorderLayout.CENTER);
		panel1.add(label1);
		panel1.add(textfield1);
		int count=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				count++;
				buttons[i][j] = new JButton();
				buttons[i][j].setText(Integer.toString(count));
				panel2.add(buttons[i][j]);
				buttons[i][j].setActionCommand(Integer.toString(count));
				buttons[i][j].addActionListener(this);
			}
		}
		for(int i=3;i<4;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(j==0)
				{
					buttons[i][j] = new JButton("Clear");
					panel2.add(buttons[i][j]);
					buttons[i][j].setActionCommand("Clear");
					buttons[i][j].addActionListener(this);
				}
				else if(j==1)
				{
					buttons[i][j] = new JButton("0");
					panel2.add(buttons[i][j]);
					buttons[i][j].setActionCommand("0");
					buttons[i][j].addActionListener(this);
				}
				else
				{
					buttons[i][j] = new JButton("Enter");
					panel2.add(buttons[i][j]);
					buttons[i][j].setActionCommand("Enter");
					buttons[i][j].addActionListener(this);
				}
			}
		}
		conn = DriverManager.getConnection(DB_URL);

	}
	public void checkValidity(String userInputCardNumber) throws SQLException
	{
		if(userInputCardNumber.length() == 10)
		{
			checkAccountNumber(userInputCardNumber);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Account number should be 10 digits. Please try again!");
			textfield1.setText("");
			enterCounter = 0;
		}	
	}
	public void checkPassword(String userInputPinNumber)
	{
		try {
			BankCustomer cust1 = new BankCustomer();
			cust1.login(userInputCardNumber, userInputPinNumber);
			
			if(cust1.loginStatus == true)
			{
				JOptionPane.showMessageDialog(null,"Login Successful");
				Login.bankCustomers.add(cust1);
				this.setVisible(false);
				writeToFile(cust1);
				
				
			}
			else
			{
				tryLogin++;
				JOptionPane.showMessageDialog(null, "Password Incorrect! Please try again!");
				textfield1.setText("");
				
				
			}
				
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void checkAccountNumber(String userInputCardNumber) throws SQLException
	{
		for(int i=0;i<Login.lockedAccountNumbers.size();i++)
		{
			if(userInputCardNumber.equals(Login.lockedAccountNumbers.get(i)))
			{
				isAccountLocked = 1;
				JOptionPane.showMessageDialog(null, userInputCardNumber+"has been blocked. Please visit branch!");
			}
		}
			
			
			
		if(isAccountLocked == 0)
		{
			Statement stmt = conn.createStatement();
			String query = "SELECT UserName " +
	                "FROM Account " +
	                "WHERE CardNum = '" + userInputCardNumber + "'";
			ResultSet resultSet = stmt.executeQuery(query);
			if(resultSet.next())
			{
				UserName = resultSet.getString("UserName");
				stmt.close();
				userFound(userInputCardNumber);	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Card number is invalid, Please input again!");
				textfield1.setText("");
				enterCounter = 0;
			}
		
		}
		else
		{
			this.setVisible(false);
		}
		
	}
	
	public void userFound(String userInputCardNumber)
	{
		
		label1.setText("Enter your pin number:");
		textfield1.setText("");	
	}

	
	public void writeToFile(BankCustomer cust1) throws SQLException
	{
		
		 try {
		      File myObj = new File("log.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 
		 String checkbal = cust1.getChequeBalance(userInputCardNumber);
		 String savingsbal = cust1.getSavingBalance(userInputCardNumber);
		
		 try {
			 String temp = "";
			 temp += "Card Number:" + userInputCardNumber;
			 temp += "\nUser Name:" + UserName;
			 temp += "\nCheck balance:" + checkbal;
			 temp += "\nsavingsbal:" + savingsbal;
			 
		      FileWriter myWriter = new FileWriter("log.txt");
		      myWriter.write(temp);

		      myWriter.close();
		      JOptionPane.showMessageDialog(null,"Successfully wrote information to the file.");
		      System.exit(0);
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Clear")
		{
			textfield1.setText("");
		}
	
		for(int i=0;i<=9;i++)
		{
			
			if(e.getActionCommand().equals(Integer.toString(i)))
			{
				textfield1.setText(textfield1.getText()+Integer.toString(i));
			}
			
			
		}
		if(e.getActionCommand()=="Enter")
		{
			if(enterCounter == 0)
			{
				userInputCardNumber = textfield1.getText();
				enterCounter++;
			}
			else
			{
				if(tryLogin < 2)
				{
					userInputPinNumber = textfield1.getText();
					checkPassword(userInputPinNumber);
					
					
				}
				else if(tryLogin >= 2)
				{
					JOptionPane.showMessageDialog(null,"Your account with "+userInputCardNumber+" has been locked");
					Login.lockedAccountNumbers.add(userInputCardNumber);
					System.exit(0);
				}
				else
				{
					
				}
	
			}
			
			try {
				checkValidity(userInputCardNumber);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
	

}
