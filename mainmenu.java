import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class mainmenu {
    // Database credentials
    final static String HOSTNAME = "tofi0000-sql-server.database.windows.net";
    final static String DBNAME = "cs-dsa-4513-sql-db";
    final static String USERNAME = "tofi0000";
    final static String PASSWORD = "Tcc346000$$$";
    // Database connection string
    final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            HOSTNAME, DBNAME, USERNAME, PASSWORD);
    // Query templates
    final static String QUERY_TEMPLATE_1 = "INSERT INTO Student " + 
                                           "VALUES (?, ?, ?, ?, ?, ?);";
    final static String QUERY_TEMPLATE_2 = "SELECT * FROM Student;";
    // User input prompt//
    final static String PROMPT = "\nPlease select one of the options below: (Only 1, 2 and 3 (quit) work for now): \n" + "1) Enter a new customer \n"
			+ "2) test- View Customer table (Enter a new department) \n" + 
			"3) Enter a new process-id" + "4) Enter a new assembly \n" + "5) Create a new account \n" + 
			"6) Enter a new job \n" + "7) At the completion of a job, enter the date it completed and the information relevant to the type\n" +
			"of job \n" + 
			"8) Enter a transaction-no and its sup-cost and update all the costs \n" + "9) Retrieve the total cost incurred on an assembly-id \n" + 
			"10) Retrieve the total labor time within a department for jobs completed in the department during a\n given date \n" + 
			"11) Retrieve the processes through which a given assembly-id has passed so far and the department responsible for each process \n" + 
			"12) Retrieve the jobs (together with their type information and assembly-id) completed during a given date in a given department \n" + 
			"13) Retrieve the customers (in name order) whose category is in a given range \n" + 
			"14) Delete all cut-jobs whose job-no is in a given range \n" + "15) Change the color of a given paint job \n" + 
			"16) Import: enter new customers from a data file \n" +
			"17) Export: Retrieve the customers (in name order) whose category is in a given range and output them to a data file \n" +
			"18) Quit \n";
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to A JOB-SHOP ACCOUNTING SYSTEM!");
        final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
        BufferedReader brr = new BufferedReader(new InputStreamReader(System. in ));
        String option = ""; // Initialize user option selection as nothing
        while (!option.equals("18")) { // As user for options until option 18 is selected
            System.out.println(PROMPT); // Print the available options
            option = sc.next(); // Read in the user option selection
            switch (option) { // Switch between different options
                
                case "1": // Create new Customer option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the customer name: \n");
    				final String name = sc.nextLine(); // Read in the user input of customer name
    				System.out.println("Please enter customer address: \n");
    				final String address = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed).
    				System.out.println("Please enter the category (must be an integeer between 1 and 10): \n");
    				//sc.nextLine(); // Consuming the trailing new line character left after nextFloat
    				final int category = sc.nextInt(); // Read in user input of student Major
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Customer_Insert @customer_name=?, @customer_address=?, @category =? ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setString(1, name);
    						statement.setString(2, address);
    						statement.setInt(3, category);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}
    				break;
                    
                case "2": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Department Number: \n");
    				final int d_num = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter Department Deatils: \n");
    				final String details = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed).
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Department_Insert @department_num= ?,@details=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, d_num);
    						statement.setString(2, details);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}
    				break;    
                case "3": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Process ID: \n");
    				final int pid = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the Department Number: \n");
    				final int d_num1 = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please Choose Type of Process: \n1)Fit\n2)Paint\n3)Cut");
    				final int option1 = sc.nextInt(); // Read in user input of student First Name (white-spaces allowed).
    				
    				switch (option1) {
    				case 1:
    					System.out.println("Please enter Fit type: \n");
        				final String fit_type = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("Fit_Process_Department_Insert @p_id=?, @department_num=?, @fit_type=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, pid);
    						statement.setInt(2, d_num1);
    						statement.setString(3, fit_type);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 2:
    					System.out.println("Please enter Paint type: \n");
        				final String paint_type = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				System.out.println("Please enter Method: \n");
        				final String method = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("Paint_Process_Department_Insert @p_id=?, @department_num=?, @paint_type=?, @method=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, pid);
    						statement.setInt(2, d_num1);
    						statement.setString(3, paint_type);
    						statement.setString(4, method);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 3:
    					System.out.println("Please enter Cut type: \n");
        				final String cut_type = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				System.out.println("Please enter Machine Type: \n");
        				final String machine_type = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("Cut_Process_Department_Insert @p_id=?, @department_num=?, @cut_type=?, @machine_type=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, pid);
    						statement.setInt(2, d_num1);
    						statement.setString(3, cut_type);
    						statement.setString(4, machine_type);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break;
    				default: // Unrecognized option, re-prompt the user for the correct one
    					System.out.println(String.format("Unrecognized option: %s\n" + "Please try again!", option1));
    					break;

    				}
    				
    				break;
                case "4": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Assembly ID: \n");
    				final int ass_id = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the date ordered (YYYY-mm-dd)"); // ask user for department data
    				sc.nextLine();
    				final String date1 = sc.nextLine();
    				System.out.println("Please enter Deatils: \n");
    				final String details1 = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
    				System.out.println("Please enter the customer name: \n");
    				final String name1 = sc.nextLine(); // Read in the user input of customer name
    				System.out.println("Please enter the Process ID: \n");
    				final int pid1 = sc.nextInt(); // Read in the user input of customer name
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Assembly_Insert @ass_id=?, @date1=? ,@details1=? ,@name1=?, @pid1=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, ass_id);
    						statement.setString(2, date1);
    						statement.setString(3, details1);
    						statement.setString(4, name1);
    						statement.setInt(5, pid1);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}
    				break; 	
    				
    				
                case "5": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Account No.: \n");
    				final int acc_no = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the date ordered (YYYY-mm-dd)"); // ask user for department data
    				sc.nextLine();
    				final String date2 = sc.nextLine();
    				System.out.println("Please enter Deatils: \n");
    				final String details3 = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed).
    				System.out.println("Please enter the cost \n");
    				final int cost = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please Choose Type of Account: \n1)Department\n2)Process\n3)Assembly");
    				final int option2 = sc.nextInt(); // Read in user input of student First Name (white-spaces allowed).
    				
    				switch (option2) {
    				case 1:
    					System.out.println("Please enter the Department Number: \n");
        				final int d_num2 = sc.nextInt(); // Read in the user input of customer name
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Account_Insert @acc_no=?,@date2=?,@details3=?,@cost=?,@d_num2=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, acc_no);
    						statement.setString(2, date2);
    						statement.setString(3, details3);
    						statement.setInt(4, cost);
    						statement.setInt(5, d_num2);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 2:
    					System.out.println("Please enter the Process ID: \n");
        				final int pid3 = sc.nextInt(); // Read in the user input of customer name
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Process_Account_Insert @acc_no=?,@date2=?,@details3=?,@cost=?,@pid3=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, acc_no);
    						statement.setString(2, date2);
    						statement.setString(3, details3);
    						statement.setInt(4, cost);
    						statement.setInt(5, pid3);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 3:
    					System.out.println("Please enter the Assembly ID: \n");
        				final int ass_id3 = sc.nextInt(); // Read in the user input of customer name
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Assembly_Account_Insert @acc_no=?,@date2=?,@details3=?,@cost=?,@ass_id3=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, acc_no);
    						statement.setString(2, date2);
    						statement.setString(3, details3);
    						statement.setInt(4, cost);
    						statement.setInt(5, ass_id3);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break;
    				default: // Unrecognized option, re-prompt the user for the correct one
    					System.out.println(String.format("Unrecognized option: %s\n" + "Please try again!", option2));
    					break;

    				}
    				
    				break;
                case "6": // Create new Customer option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Job No.: \n");
    				final int job_no = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the Start date ordered (YYYY-mm-dd)"); // ask user for department data
    				sc.nextLine();
    				final String s_date = sc.nextLine();
    				System.out.println("Please enter the Process ID: \n");
    				final int pid4 = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the Assembly ID: \n");
    				final int ass_id4 = sc.nextInt(); // Read in the user input of customer name
    				
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Job_Insert @job_no=?, @s_date=?,@pid4=?, @ass_id4=?  ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, job_no);
    						statement.setString(2, s_date);
    						statement.setInt(3, pid4);
    						statement.setInt(4, ass_id4);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}
    				break;
    				
                case "7": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Job No.: \n");
    				final int job_no1 = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the End date ordered (YYYY-mm-dd)"); // ask user for department data
    				sc.nextLine();
    				final String e_date = sc.nextLine();
    				System.out.println("Please enter the Labor Time: \n");
    				final int labor_time = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please Choose Type of Process: \n1)Cut\n2)Paint\n3)Fit");
    				final int option3 = sc.nextInt(); // Read in user input of student First Name (white-spaces allowed).
    				
    				switch (option3) {
    				case 1:
    					System.out.println("Please enter Cut type: \n");
        				final String cut_type1 = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				System.out.println("Please enter the Amount of time.: \n");
        				final int amount_of_time = sc.nextInt(); // Read in the user input of customer name
        				System.out.println("Please enter Material: \n");
        				final String material = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Cut_Job_close @job_no1=?,@e_date=?, @cut_type1=?, @amount_of_time=?, @material=?, @labor_time=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, job_no1);
    						statement.setString(2, e_date);
    						statement.setString(3, cut_type1);
    						statement.setInt(4, amount_of_time);
    						statement.setString(5, material);
    						statement.setInt(6, labor_time);
    						
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 2:
        				System.out.println("Please enter the Volume.: \n");
        				final int volume = sc.nextInt(); // Read in the user input of customer name
        				System.out.println("Please enter Colour: \n");
        				final String colour = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed)
        				sc.nextLine();
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Paint_Job_close @job_no1=?,@e_date=?, @labor_time=?, @volume=?, @colour=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, job_no1);
    						statement.setString(2, e_date);
    						statement.setInt(3, labor_time);
    						statement.setInt(4, volume);
    						statement.setString(5, colour);
    						
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 3:
    					    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Fit_Job_close @job_no1=?,@e_date=?, @labor_time=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, job_no1);
    						statement.setString(2, e_date);
    						statement.setInt(3, labor_time);
    						
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break;
    				default: // Unrecognized option, re-prompt the user for the correct one
    					System.out.println(String.format("Unrecognized option: %s\n" + "Please try again!", option3));
    					break;

    				}
    				
    				break;
    				
                case "8": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Transaction no.: \n");
    				final int transaction_no = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the Amount: \n");
    				final int amount = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please enter the Account no.: \n");
    				final int Account_no = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please Choose Type of Process: \n1)Assembly Account\n2)Department Account\n3)Process Account");
    				final int option4 = sc.nextInt(); // Read in user input of student First Name (white-spaces allowed).
    				
    				switch (option4) {
    				case 1:
    					    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Assembly_add @transaction_no=?,@amount=?, @Account_no=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, transaction_no);
    						statement.setInt(2, amount);
    						statement.setInt(3, Account_no);
    						
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 2:
    					System.out.println("Connecting to the database...");
        				// Get a database connection and prepare a query statement
        				try (final Connection connection = DriverManager.getConnection(URL)) {
        					
        					try (final PreparedStatement statement = connection.prepareStatement("EXEC Department_add @transaction_no=?,@amount=?, @Account_no=?;")) {
        						// Populate the query template with the data collected from the user
        						statement.setInt(1, transaction_no);
        						statement.setInt(2, amount);
        						statement.setInt(3, Account_no);
        						
        						System.out.println("Dispatching the query...");
        						// Call the stored procedure
        						final int rows_inserted = statement.executeUpdate();
        						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}break; 
    				case 3:
    					    				
    					System.out.println("Connecting to the database...");
        				// Get a database connection and prepare a query statement
        				try (final Connection connection = DriverManager.getConnection(URL)) {
        					
        					try (final PreparedStatement statement = connection.prepareStatement("EXEC Process_add @transaction_no=?,@amount=?, @Account_no=?;")) {
        						// Populate the query template with the data collected from the user
        						statement.setInt(1, transaction_no);
        						statement.setInt(2, amount);
        						statement.setInt(3, Account_no);
        						
        						System.out.println("Dispatching the query...");
        						// Call the stored procedure
        						final int rows_inserted = statement.executeUpdate();
        						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
        					}
        				}break;
        				default: // Unrecognized option, re-prompt the user for the correct one
        					System.out.println(String.format("Unrecognized option: %s\n" + "Please try again!", option4));
        					break;

        				}
        				
                case "9": // Create new Customer option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Assembly ID: \n");
    				final int ass_id5 = sc.nextInt(); // Read in the user input of customer name
    				
    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Total_cost @ass_id5=?;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, ass_id5);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}
    				break;
    				
                case "11": // Create new Customer option
    				// Collect the new student data from the user
    				System.out.println("Please enter the Process ID: \n");
    				sc.nextLine();
    				final int pid5 = sc.nextInt(); // Read in the user input of customer name
    				
    			    			
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Processes @id=? ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, pid5);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
    					}
    				}
    				break;
    				
                case "12": // Create new Department option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the End date ordered (YYYY-mm-dd)"); // ask user for department data
    				final String end_date = sc.nextLine();
    				System.out.println("Please enter the Department No.: \n");
    				final int d_num11 = sc.nextInt(); // Read in the user input of customer name
    				System.out.println("Please Choose Type of Process: \n1)Cut Jobs\n2)Paint Jobs\n3)Fit Jobs");
    				final int option5 = sc.nextInt(); // Read in user input of student First Name (white-spaces allowed).
    				
    				switch (option5) {
    				case 1:
    					    				
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Cut_Jobs @end_date=?, @d_num=? ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setString(1, end_date);
    						statement.setInt(2, d_num11);
    						
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    					}
    				}break; 
    				case 2:
    					System.out.println("Connecting to the database...");
        				// Get a database connection and prepare a query statement
        				try (final Connection connection = DriverManager.getConnection(URL)) {
        					
        					try (final PreparedStatement statement = connection.prepareStatement("EXEC Paint_Jobs @end_date=?,@d_num=?;")) {
        						// Populate the query template with the data collected from the user
        						statement.setString(1, end_date);
        						statement.setInt(2, d_num11);
        						
        						System.out.println("Dispatching the query...");
        						// Call the stored procedure
        						final int rows_inserted = statement.executeUpdate();
    					}
    				}break; 
    				case 3:
    					    				
    					System.out.println("Connecting to the database...");
        				// Get a database connection and prepare a query statement
        				try (final Connection connection = DriverManager.getConnection(URL)) {
        					
        					try (final PreparedStatement statement = connection.prepareStatement("EXEC Fit_Jobs @end_date=?,@d_num=?;")) {
        						// Populate the query template with the data collected from the user
        						statement.setString(1, end_date);
        						statement.setInt(2, d_num11);
        						
        						System.out.println("Dispatching the query...");
        						// Call the stored procedure
        						final int rows_inserted = statement.executeUpdate();
        					}
        				}break;
        				default: // Unrecognized option, re-prompt the user for the correct one
        					System.out.println(String.format("Unrecognized option: %s\n" + "Please try again!", option5));
        					break;

        				}	
                case "13": // Create new Customer option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the category (must be an integeer between 1 and 10): \n");
    				//sc.nextLine(); // Consuming the trailing new line character left after nextFloat
    				final int category1 = sc.nextInt(); // Read in user input of student Major
    				
    			    			
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Categorical_Customer;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, category1);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    					}
    					catch(Exception ex){
    						 ex.printStackTrace();
    						}
    				}
    				break;	
    				
                case "14": // Create new Customer option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the Job Number you want to Delete record from: \n");//sc.nextLine(); // Consuming the trailing new line character left after nextFloat
    				final int from = sc.nextInt(); // Read in user input of student Major
    				System.out.println("Please enter the Job Number you want to Delete record to: \n");//sc.nextLine(); // Consuming the trailing new line character left after nextFloat
    				final int to = sc.nextInt(); // Read in user input of student Major
    			    			
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Remove_Cut_job @from=?, @to=? ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, from);
    						statement.setInt(2, to);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    					}
    				}
    				break;	
    				
                case "15": // Create new Customer option
    				// Collect the new student data from the user
    				sc.nextLine();
    				System.out.println("Please enter the old colour: \n");
    				final String from_colour = sc.nextLine(); // Read in the user input of customer name
    				System.out.println("Please enter the new colour: \n");
    				final String to_colour = sc.nextLine(); // Read in the user input of customer name
    			    			
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Change_colour @from_colour=?, @to_colour=? ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setString(1, from_colour);
    						statement.setString(2, to_colour);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final int rows_inserted = statement.executeUpdate();
    					}
    				}
    				break;
    				
                /*case "16":

                    try {
                      System.out.println("Enter import file name: ");  //Taking file name from user

                      String input_file = brr.readLine();
                      FileInputStream fin = new FileInputStream("D:/eclipse/project/A JOB-SHOP ACCOUNTING SYSTEM/src" + input_file); //path for the input file
                      DataInputStream in =new DataInputStream(fin);
                      BufferedReader brrr = new BufferedReader(new InputStreamReader( in ));
                      String strLine;
                      while ((strLine = brrr.readLine()) != null) {
                        String s[] = strLine.split(","); //Specifying the split symbol in the input d=file for the data
                        name = s[0];
                        address = s[1];
                        //category = s[2];
                        //Inserting values from the input file to the Customer table
                        //final PreparedStatement statement = connection.prepareStatement("INSERT INTO TEAM VALUES('" + name + "','" + address + "','" + category + "')");
                        //final Statement statement = connection.createStatement();
                        		                           // final ResultSet resultSet = 
                        		//statement.executeQuery(QUERY_TEMPLATE_2))
                      }

                    }
                    catch(SQLException e) {
                      e.printStackTrace();
                    }
                    break;

                  case "17":
                    System.out.println("Exporting data to a file.");
                    //Query for retriving name and address of a person who is on the mailing list
                    sc.nextLine();
    				System.out.println("Please enter the category (must be an integeer between 1 and 10): \n");
    				//sc.nextLine(); // Consuming the trailing new line character left after nextFloat
    				final int category2 = sc.nextInt(); // Read in user input of student Major
    				System.out.println("Enter output file name: "); //Taking file name from user
                    String output = brr.readLine();
    				
    			    			
    				System.out.println("Connecting to the database...");
    				// Get a database connection and prepare a query statement
    				try (final Connection connection = DriverManager.getConnection(URL)) {
    					
    					try (final PreparedStatement statement = connection.prepareStatement("EXEC Categorical_Customer @category2=? ;")) {
    						// Populate the query template with the data collected from the user
    						statement.setInt(1, category2);
    						System.out.println("Dispatching the query...");
    						// Call the stored procedure
    						final ResultSet rows_inserted = statement.executeQuery();
    						BufferedWriter op = new BufferedWriter(new FileWriter("D:/eclipse/project/A JOB-SHOP ACCOUNTING SYSTEM/src" + output)); //Path for output file
    						//while (resultSet.next()) {
    		                      //String str1 = ResultSet.getString("name");     
    		                      //String str2 = ResultSet.getString("address");
    		                      //op.write("\n");
    		                      //op.write(str1 + "\t");  //Exporting Name in the output file
    		                      //op.write(str2 + "\t");  ////Exporting adress in the output file
    		                    }
    		                    //op.close();
    					}
    				
                    break;
    				
        		*/case "18": // Do nothing, the while loop will terminate upon the next iteration
    				System.out.println("Exiting! Good-buy!");
    				break;
    			
                default: // Unrecognized option, re-prompt the user for the correctone
                    System.out.println(String.format(
                        "Unrecognized option: %s\n" + 
                        "Please try again!", 
                        option));
                    break;
            }
        }
        sc.close(); // Close the scanner before exiting the application
    }
}