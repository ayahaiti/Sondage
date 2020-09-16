package org.sondage.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseAccess {

	public static Properties properties;
	public static String DB_DRIVER ="";
	public static  String DB_CONNECTION ="";
	public static String DB_USER = "";
	public static String DB_PASSWORD = "";
	public static String DB_NAME = "";
	public static int numPort = 0;
	public static String bdd_IP = "";

	public static void readProperties(){
		if(properties == null){
			properties = new Properties();
			String resourceName = "database_connector_configfile.properties";
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
				properties.load(resourceStream);
				System.out.println(properties);
				DB_DRIVER = properties.getProperty("DB_DRIVER");
				DB_NAME = properties.getProperty("DB_NAME");
				DB_PASSWORD = properties.getProperty("DB_PASSWORD");
				DB_USER = properties.getProperty("DB_USER");
				numPort = Integer.parseUnsignedInt(properties.getProperty("numPort"));
				bdd_IP = properties.getProperty("bdd_IP");
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
