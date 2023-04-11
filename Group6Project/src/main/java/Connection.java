//
//
//
//import java.sql.Connection;
//import java.sql.Drivermanager;
import.java.sql.*;
public class Connection {

 public static void main(String[] args) {

    try {
	  //Load JDBC driver
      Class.forname("Oracle.jdbc.driver.oracleDriver");
      } catch (ClassNotFoundException e){

            e.printStackTrace();
      }

       String serverName = "cisvm-oracle.unfcsd.unf.edu";
       String portNumber = "1521";
       String sid = "orcl";
       String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
       String username ="G6"
       String password ="G6Spring2023"
         try {
	          //Create a connection using given url,username and password
		Connection conn = DriverManager.getConnection(url, username, password);

	        boolean reachable = conn.isValid(10);/

	        if(reachable) {

	        	System.out.println("Successfully connected");
	        	conn.close();
	        }
	} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}


