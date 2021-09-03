package com.pwc.users.dataacess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
public class MySqlConnectionPool {

    private static ArrayBlockingQueue<Connection> connectionPool ;
    
    static {
    	/*should be read from configrations*/
    	int numberOfConnections = 10; 
    	try {
    	MySqlConnectionPool.createConnectionPool(numberOfConnections);
    	}
    	catch(SQLException e) {
    		//unable to create connections
    		e.printStackTrace();
    	}
    	  
    }

   
   private static void createConnectionPool(int numberOfConnections) throws SQLException {
	   connectionPool = new ArrayBlockingQueue<Connection>(numberOfConnections);
       for(int i= 0 ;i < numberOfConnections ;i++)
           connectionPool.add(MySqlConnection.getConnection());
   }

    
    public static Connection getConnectoin(){

        return connectionPool.poll() ;
    }

   
    public static void releaseConnection(Connection connection) throws SQLException{

        connectionPool.add(connection);
        connection.close();
    }

    /**
     * This method is used to clear the connection pool
     */
    public static void shutdown(){
         for(Connection conn : connectionPool )
         try{
        	 conn.close();

         }
         catch (Exception e) {
        	 e.printStackTrace();
         }
          connectionPool.clear();

    }
    
    public static void main(String args[]) throws Exception {
    	MySqlConnectionPool.createConnectionPool(10);
    	Connection conn = MySqlConnectionPool.getConnectoin();
    	String query = " select * from user" ;
    	try(PreparedStatement ps = conn.prepareStatement(query);)
    	{
    		ResultSet rs=ps.executeQuery();  
    		while(rs.next()){  
    		System.out.println(rs.getInt(1)+" "+rs.getString(2));  
    		}  
    		
    	}
    	catch(Exception ex) {
    		
    	}
    	
    	MySqlConnectionPool.shutdown();
    }
}
