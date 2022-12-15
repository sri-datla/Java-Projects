import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
public class CreateDatabase {
	
	public void createATMDatabase()
	{
		final String DB_URL = "jdbc:derby:atmdb;create=true";
		try
		{
			Connection conn = DriverManager.getConnection(DB_URL);
			dropTable(conn);
			createTable(conn);
			insertData(conn);
			showData(conn);
			conn.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error is :"+ex.getMessage());
		}
	
	}
	
	public void dropTable(Connection conn) throws SQLException
	{
		System.out.println("Checking for existing tables");
		Statement stmt  = conn.createStatement();
		stmt.execute("DROP TABLE Account");
		System.out.println("Dropped table");
		stmt.close();
	}
	
	public void createTable(Connection conn) throws SQLException
	{
		Statement stmt = conn.createStatement();   
		stmt.execute("CREATE  TABLE Account(" + 
			"CardNum	Char(10)	NOT NULL, " + 
			"PassWord	Char(9)	NOT NULL, " +
			"UserName	Char(25)	NOT NULL, " + 
	"ChequeBanlance	Numeric(15,2)    NOT NULL, " + 
			"SavingBanlance	Numeric(15,2) " + 
			")");
		System.out.println("Table created");
		stmt.close();

	}
	
	public void insertData(Connection conn) throws SQLException
	{
		String filename = "sql.txt";
		File inputfile = new File(filename);
		Scanner input;
		Statement stmt = conn.createStatement();
		try
		{
			input = new Scanner(inputfile);
			while(input.hasNext())
			{
				String query = input.nextLine();
				stmt.execute(query);
			}
			System.out.println("All values inserted");
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void showData(Connection conn) throws SQLException
	{
		Statement stmt = conn.createStatement();
		String sqlStatement = "SELECT * FROM Account";
		System.out.println("/* Showing data for testing */");
		ResultSet result = stmt.executeQuery(sqlStatement);
		while(result.next())
		{
			String temp = "";
			System.out.println("--------------------------");
			System.out.println(result.getString("CardNum"));
			temp = result.getString("PassWord");
			temp = temp.substring(0, temp.length()-3);
			System.out.println(temp);
			
		} 
		
	}
	
	
	

}
