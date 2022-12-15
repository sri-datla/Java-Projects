import java.sql.SQLException;

public interface AccountManagement {
	public String getChequeBalance(String cardNum) throws SQLException;
	public String getSavingBalance(String cardNum) throws SQLException;
	public void login(String cardNum, String password) throws SQLException;

}
