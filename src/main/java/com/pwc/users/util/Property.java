package com.pwc.users.util;

import java.io.FileReader;
import java.util.Properties;

public class Property {

	
	private static Properties props ; 
	private final static String PROPERTIES_FILE_PATH = "./src/main/resources/config.properties" ; 
	
	public static Properties getPropertyObjcet() {
		
		if(props == null) {
			props = getPropsObj();
		}
		 return props;
	}
	
	private static Properties getPropsObj() {
		props = new Properties();
		
        try(FileReader reader = new FileReader(PROPERTIES_FILE_PATH);){
        	props.load(reader);
        }
        catch (Exception e) {
        	//Error while reading props
        	e.printStackTrace();
		}
        
	 return props;
	}
	
	public static void reloadPropertyFile() {
		props = getPropsObj();
	}
	
	public static void main(String args[]) {
		Properties prop = Property.getPropertyObjcet();
		System.out.println(prop.getProperty("mysql.user"));
	}
	
}
