package com.pwc.users.dataacess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySqlConnection {
static Connection getConnection() throws SQLException{
		
		
        /*Make the connection string parameterized*/

		return DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/pwc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","1234Prog");  
	

		
	}
}
