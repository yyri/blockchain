package com.yancy.ipfs;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

public class TestCases {

	@Test
	public void getConf() {
		try {
			Configuration config = new PropertiesConfiguration("config.properties");
			String filestorepath = config.getString("filestorepath");
			System.out.print(filestorepath);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
