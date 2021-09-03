package com.pwc.users.dataacess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

import com.pwc.users.util.Property;
public class MySqlConnectionPool {

    private static ArrayBlockingQueue<Connection> connectionPool ;
    private static int numberOfConnections;
    static {
    	
        numberOfConnections = Integer.parseInt(Property.getPropertyObjcet().getProperty("mysql.poolSize")); 
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
        if(connectionPool == null)
			try {
				createConnectionPool(numberOfConnections);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    	
    }
}
