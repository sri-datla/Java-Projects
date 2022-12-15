import java.sql.SQLException;
import java.util.ArrayList;

public class Login {
	static ArrayList<BankCustomer> bankCustomers = new ArrayList<BankCustomer>();
	static ArrayList<String> lockedAccountNumbers = new ArrayList<String>();

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		CreateDatabase test = new CreateDatabase();
		test.createATMDatabase();
		GUI ATMLogin = new GUI();
		ATMLogin.setVisible(true);

	}

}
