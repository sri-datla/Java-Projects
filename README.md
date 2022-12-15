ATM application.  

1.	GUI based Java application ATMLogin using Derby DB.
2.	Created database Bank and built Account table, using the following statements
Statement stmt = conn.createStatement();   
// Create the table.
stmt.execute("CREATE  TABLE Account(" + 
"CardNum	Char(10)	NOT NULL, " + 
"PassWord	Char(25)	NOT NULL, " +
"UserName	Char(25)	NOT NULL, " + 
"ChequeBalance	Numeric(15,2)    NOT NULL, " + 
"SavingBalance	Numeric(15,2) " + 
")");

3.	Then inserted data into the table using SQL statements from the sql.txt file. 
4.	Designed display keypad using GUI to allow user input the card number and password
5.	If user click the clear button, all content input will be cleaned. 
6.	Once user finish card number input by clicking the “Enter” key, application is checking the card number validity, which must be 10 digits and should be contained in the Account table. 
Used this query to fetch the rows from the database - 
ResultSet resultSet = stmt.executeQuery(
a.	"SELECT UserName " +
b.	"FROM Account " +
c.	"WHERE CardNum = '" + cardNum + "'");

7.	If the card number user input is not valid or not contained in the Account table we are displaying a Message dialog (“Card number is invalid, please input again”) to ask user input again. 
8.	If the card number is valid, application asks user to enter pin number.
9.	After user click the Enter button after entering pin, we use the card number and password input to login the account.
10.	Then used interface Account Management with the following methods:
GetChequeBalance():  get the balance of the chequing account.
GetSavingBalance():   get the balance of the saving account. 
Login(): log in the account using card number and password given. 
11.	Defined Bankcustomer account which implements the AccountManagement  interface and has the following attributes 
a.	Name: customer’s name ;
b.	CardNum: card number;
c.	PassWord: password;
d.	ChequeBalance: chequing account balance;
e.	SavingBalance: saving account balance;
f.	LoginStatus: whether or not user has been successfully logged in. 
It will implements those methods: 
GetChequeBalance():  get the balance of the chequing account from the database.
GetSavingBalance():   get the balance of the saving account from the database.
Login(): log in the account using card number and password input, where we are using the card number to query the password from Account table first and then compare the password with the one input. 
12.	However, to improve the security level, the password in database was encrypted, therefore, we need to decrypt the queried password first.  We used a straight forward encryption approach:
The password was post-fixed by “999” at the end of the password string in database. 
13.	We defined Login class which contains the main method. 
14.	In the main method, we are displaying the GUI, creating Bankcustomer ArrayList. Using the card number input to login and obtain the customer name create Bankcustomer object and adding to the Bankcustomer ArrayList.   
15.	We are allowing 3 times attempt for login, if the password input is failed 3 times, the corresponding card number will be locked and will not allow user to login and display Message dialog (“Card is locked!”).
16.	We are saving the customer’s name, card number, chequing account balance and saving account balance to Log.txt file and ending the application.
