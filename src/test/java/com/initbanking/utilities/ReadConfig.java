package com.initbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig()
	{
		File src = new File("./ConfigurationFiles/config.properties");
		try {
			FileInputStream fic = new FileInputStream(src);
			prop = new Properties();
			try {
				prop.load(fic);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception is  : "+e );
		}
		
	}
	
	
	public String getApplicationURL()
	{
		String url = prop.getProperty("baseUrl");
		return url;
	}
	
	public String getUserName()
	{
		String uname = prop.getProperty("username");
		return uname;
	}
	
	public String getPassword()
	{
		String pwd = prop.getProperty("password");
		return pwd;
	}

	public String getChromePath()
	{
		String cpath = prop.getProperty("chromepath");
		return cpath;
	}
	
	public String getfirefoxPath()
	{
		String fpath = prop.getProperty("firefoxpath");
		return fpath;
	}

}
