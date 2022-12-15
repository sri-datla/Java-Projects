import java.sql.*;

public class BankCustomer implements AccountManagement{
	String custName;
	String cardNum;
	String password;
	double chequeBalance;
	double savingBalance;
	boolean loginStatus = false;
	final String DB_URL = "jdbc:derby:atmdb";
	Connection conn;
	
	public BankCustomer() throws SQLException
	{
		conn = DriverManager.getConnection(DB_URL);
	}
	@Override
	public String getChequeBalance(String cardNum) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery(
                "SELECT ChequeBanlance " +
                "FROM Account " +
                "WHERE CardNum = '" + cardNum + "'");
		if(resultSet.next())
		{
			return resultSet.getString("ChequeBanlance");
		}
		else
			return "0";
		
	}

	@Override
	public String getSavingBalance(String cardNum) throws SQLException{
		// TODO Auto-generated method stub
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery(
                "SELECT SavingBanlance " +
                "FROM Account " +
                "WHERE CardNum = '" + cardNum + "'");
		if(resultSet.next())
		{
			return resultSet.getString("SavingBanlance");
			
		}
		else
		{
			return "0";
		}
	}

	@Override
	public void login(String cardNum, String password) throws SQLException{
		// TODO Auto-generated method stub
		String passwordindb = "";
		Statement stmt = conn.createStatement();
		
		ResultSet resultSet = stmt.executeQuery(
                "SELECT PassWord " +
                "FROM Account " +
                "WHERE CardNum = '"+cardNum+"'");
		
		if(resultSet.next())
		{
			
			passwordindb = resultSet.getString("PassWord");
			passwordindb.strip();
			passwordindb = passwordindb.substring(0,passwordindb.length()-3);
			
		}
		stmt.close();
		System.out.println(passwordindb);
		System.out.println(passwordindb.length());
		if(passwordindb.equals(password))
		{
			this.loginStatus = true;
		}
		
		
		
	}

}
