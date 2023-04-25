package postgreSQLdemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class postgreSQL_Commands {
	//private final String url = "jdbc:postgresql://localhost/myDB";
	private final static String url = "jdbc:postgresql://localhost:5432/dvdrental";
    private final static String user = "postgres";
    private final static String password = "rekanv";
    
	
	 public  Connection connect() {
	 Connection conn = null;
     try {
        conn = DriverManager.getConnection(url, user, password);

        if (conn != null) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } else {
            System.out.println("Failed to make connection!");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } return conn;
	 }
		  
/*	 public void executeQury() {
		 Statement st = conn.createStatement();	  
	 ResultSet rs = st.executeQuery("SELECT first_name  FROM customer WHERE first_name LIKE 'A%'");
		  while (rs.next()) {
		      System.out.print("Column 1 returned ");
		      System.out.println(rs.getString(1));
		  }
		  rs.close();
		   // close connection
			  st.close();
	 }
*/	
 
	public static void main(String[] args) throws SQLException
		{
		
//		Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental","postgres","rekanv");
//		System.out.println("connected");		  
//		 execute statement
//		  ResultSet result= stmt.executeQuery(s);
//		  while(result.next()) {
//			  System.out.println(result.getString(1));
//		}
		postgreSQL_Commands app = new postgreSQL_Commands();
	        app.connect();
	    //    app.executeQury();
		
		// execute statement
		  	  
	    // stmt.execute ( select 'Patient_ID' from public.'Patients');
  
	}
}
