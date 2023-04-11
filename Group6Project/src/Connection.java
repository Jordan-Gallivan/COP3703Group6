import java.sql.Connection;

public class Connection{

   public static void main(String[] args){

       try{
          Class.forname("Oracle.jdbc.driver.oracleDriver");

       } catch (ClassNotFoundException e){
           
            e.printStackTrace();
       }
               
        String serverName = "cisvm-oracle.unfcsd.unf.edu";
        String portNumber = "1521";
        String sid = "orcl";
        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
        String username ="G1"
        String password ="G6Spring2023"
         try {
			Connection conn = DriverManager.getConnection(url, username, password);
			
	        boolean reachable = conn.isValid(10);// 10 sec

	        if(reachable) {
	        	
	        	System.out.println("Sucess");
	        	conn.close();
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
        
	}
}


