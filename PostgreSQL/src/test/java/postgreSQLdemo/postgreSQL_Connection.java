package postgreSQLdemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class postgreSQL_Connection {
	 private final static String url = "jdbc:postgresql://localhost:5432/dvdrental";
	 private final static String user = "postgres";
	 private final static String password = "rekanv";

    public static void main(String[] args) throws SQLException {
	   	   
	    // CONNECT TO POSTGRESSQL DATABASE	    
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
	        }
	        
      //  CREATE STATEMENT and EXECUTE STATEMENT
	        //Statement st = conn.createStatement();
//	        ResultSet rs = st.executeQuery("SELECT first_name  FROM customer WHERE first_name LIKE 'A%'");
//			  while (rs.next()) {
//			      System.out.print("FirstName which starts with A : ");
//			      System.out.println(rs.getString("first_name"));
//			  }
	        PreparedStatement updateStmt =
	                conn.prepareStatement("UPDATE NEWMOVIES SET RENTAL_RATE=5555 WHERE RELEASE_YEAR>2022;");
//	        updateStmt.setString(1, "has a red crown");
//	        updateStmt.setString(2, "rooster");
	        updateStmt.executeUpdate();  
	        
      // CLOSE CONEECTION
			  //rs.close();
			  //st.close();
	    }
}
