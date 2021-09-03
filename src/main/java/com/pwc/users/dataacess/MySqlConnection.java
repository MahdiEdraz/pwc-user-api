package com.pwc.users.dataacess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pwc.users.util.Property;
public class MySqlConnection {
static Connection getConnection() throws SQLException{
		
		String user = Property.getPropertyObjcet().getProperty("mysql.user");
		String pass = Property.getPropertyObjcet().getProperty("mysql.pass");
        String host = Property.getPropertyObjcet().getProperty("mysql.host");
        String port = Property.getPropertyObjcet().getProperty("mysql.port");
        String db = Property.getPropertyObjcet().getProperty("mysql.db");

		return DriverManager.getConnection(  
		"jdbc:mysql://"+host+":"+port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",user,pass);  
	

		
	}
}
