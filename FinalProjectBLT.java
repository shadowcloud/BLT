/*this program is purpose is to connect to a database that we will be using for our banking information.
 the name of the database is bankingdb and the password is just password
 The database includes table customers, checking_account, loan, savings_account.
 */

import java.sql.*;


public class FinalProjectBLT {

	public static void main(String[] args) {
		
		try(	// allocating a database Connection object
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingdb","myuser", "password");
				
				// allocating a statement object in the connection
				
				Statement statement = conn.createStatement();
				){
			
			/********************* executing a SQL select query, the query result is returned in a ResultSet object ****************************/
			
			String strSelect = "select customer_id, ssn, street_address, city, state, zip, first_name, last_name from customers ";
			
			// print for debbuging
			System.out.println("The SQL query is: " +strSelect);
			System.out.println();
			
			
			ResultSet resultSet = statement.executeQuery(strSelect);
			
			// proccessing the result set by scrolling the cursor foward via next()
			// for each row retrive the contents of the cell with the getXxx(columnName)
			
			System.out.println("Customer ID,  SSN,  Address,    City,    State,  ZipCode,   First Name,  Last Name");
			System.out.println("The records selected are: ");
			int rowCount = 0;
			
			while(resultSet.next()){
				
				String customerId = resultSet.getString("customer_id");
				String ssn = resultSet.getString("ssn");
				String streetAddress = resultSet.getString("street_address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String zip = resultSet.getString("zip");
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				
				System.out.println(customerId+", " + ssn + ", " + streetAddress + ", " + city + ", " + state + ", " + zip + ", " + firstName + ", " + lastName + ".");
				
				++rowCount;
			}
			
			System.out.println("Total number of records in customer table is " + rowCount);
			
			/**************************************************** Simple updating of the database *************************************************************/
			
			System.out.println("***************************** Start of update table*******************************");
			
			// calling update mysql command and changing customer 0001 first name from Ronald to Ronnie.  Thats what the ladies call him.
		
			String strUpdate = "update customers set first_name = 'Ronnie' where customer_id = '0001'";
			
			// debugging call
			System.out.println("The sql update query is: " + strUpdate);
			
			int countUpdated = statement.executeUpdate(strUpdate);
			
			System.out.println("First name updated" + countUpdated);
			
			
			ResultSet resultSetUpdate = statement.executeQuery(strSelect);
			// reprinting select statement of the customer table to show that the change took affect
			
			System.out.println();
			System.out.println("***************************** Updated table with Customer 0001 name changed from Ronald to Ronnie*******************************");
			System.out.println("Customer ID,  SSN,  Address,    City,    State,  ZipCode,   First Name,  Last Name");
			System.out.println("The records selected are: ");
			
			rowCount = 0;
			while(resultSetUpdate.next()){
				
				String customerId = resultSetUpdate.getString("customer_id");
				String ssn = resultSetUpdate.getString("ssn");
				String streetAddress = resultSetUpdate.getString("street_address");
				String city = resultSetUpdate.getString("city");
				String state = resultSetUpdate.getString("state");
				String zip = resultSetUpdate.getString("zip");
				String firstName = resultSetUpdate.getString("first_name");
				String lastName = resultSetUpdate.getString("last_name");
				
				System.out.println(customerId+", " + ssn + ", " + streetAddress + ", " + city + ", " + state + ", " + zip + ", " + firstName + ", " + lastName + ".");
				
				++rowCount;
			}
			
			System.out.println("Total number of records in customer table is " + rowCount);
			
			
			/***************************************** Start of deleting records in the customer table ***************************************************/
			System.out.println();
			System.out.println();
			System.out.println("***************************** Start of Delete *******************************");
			
			// deleteing records of customers 0010-0013
			
			String strDelete = "delete from customers where customer_id>=0010 and customer_id<=0013";
			
			// print for debugging purposes
			
			System.out.println("The delete query is: " +strDelete);
			int countDeleted = statement.executeUpdate(strDelete);
			System.out.println("Records deleted " +countDeleted);
			
			ResultSet resultSetDelete = statement.executeQuery(strSelect);
			
			System.out.println("***************************** Updated table with Customer 0010-0013 deleted *******************************");
			System.out.println("Customer ID,  SSN,  Address,    City,    State,  ZipCode,   First Name,  Last Name");
			System.out.println("The records selected are: ");
			
			rowCount = 0;
			
			while(resultSetDelete.next()){
				
				String customerId = resultSetDelete.getString("customer_id");
				String ssn = resultSetDelete.getString("ssn");
				String streetAddress = resultSetDelete.getString("street_address");
				String city = resultSetDelete.getString("city");
				String state = resultSetDelete.getString("state");
				String zip = resultSetDelete.getString("zip");
				String firstName = resultSetDelete.getString("first_name");
				String lastName = resultSetDelete.getString("last_name");
				
				System.out.println(customerId+", " + ssn + ", " + streetAddress + ", " + city + ", " + state + ", " + zip + ", " + firstName + ", " + lastName + ".");
				
				++rowCount;
				
			}
			
			/***************************************** Start of inserting records in the customer table ***************************************************/
			System.out.println();
			System.out.println();
			System.out.println("***************************** Start of Insert *******************************");
			
			
			//inserting records back into customer table.  This query will put back customers 0010-0013 that were deleted in the last delete query
			
			String strInsert = "insert into customers "+"values ('0010', '235445789', '2508 StoneCrest', 'St. Joseph', 'MO', '64501', 'Lana', 'McGlynn')," 
			+ "('0011', '436789213', '4156 N. Mulbury', 'St. Joseph', 'MO', '64502', 'John', 'Munzer')," + "('0012', '233435656', '1525 Kioa', 'Ellwood', 'KS', '56708', 'Mary', 'White'),"
					+"('0013', '435678787', '8902 Martha LN ', 'Gower', 'MO', '63301', 'Jerry', 'Gronius')";
			
			// print for debugging
			System.out.println("The SQL query: " + strInsert);
			
			int countInserted = statement.executeUpdate(strInsert);
			
			System.out.println("The number of records inserted is " + countInserted);
			
			ResultSet resultSetInserted = statement.executeQuery(strSelect);
			
			while(resultSetInserted.next())
			{
				String customerId = resultSetInserted.getString("customer_id");
				String ssn = resultSetInserted.getString("ssn");
				String streetAddress = resultSetInserted.getString("street_address");
				String city = resultSetInserted.getString("city");
				String state = resultSetInserted.getString("state");
				String zip = resultSetInserted.getString("zip");
				String firstName = resultSetInserted.getString("first_name");
				String lastName = resultSetInserted.getString("last_name");
				
				System.out.println(customerId+", " + ssn + ", " + streetAddress + ", " + city + ", " + state + ", " + zip + ", " + firstName + ", " + lastName + ".");
				
				++rowCount;
				
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

}
