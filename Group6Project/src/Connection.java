import java.sql.Connection;
import java.sql.DriverManager;
import.java.sql.SQLException;

public class Connection {

   public static void main(String[] args) {

       try {
           Class.forname("Oracle.jdbc.driver.oracleDriver");
	 } catch (ClassNotFoundException e){
           
            e.printStackTrace();
       }
               
        String serverName = "cisvm-oracle.unfcsd.unf.edu";
        String portNumber = "1521";
        String sid = "orcl";
	  //
        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
        String username ="G6"  
        String password ="G6Spring2023" 
         try {
		 
		Connection conn = DriverManager.getConnection(url, username, password);
			
	        boolean reachable = conn.isValid(10);// 10 sec

	        if(reachable) {
	        	
	        	System.out.println("Successfully connected");
	        	conn.close();
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
        
	}
}


