package yyri.test;

import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		log.debug(System.getProperty("java.home"));
		System.setProperty("javax.net.ssl.trustStore", System.getProperty("java.home") + "\\lib\\security\\cacerts");
//		System.setProperty("javax.net.ssl.keyStore", System.getProperty("java.home") + "\\lib\\security\\cacerts");
		System.setProperty("javax.net.ssl.keyStorePassword", "yyriyyri123");
		log.debug(System.getProperty("javax.net.ssl.trustStore"));
		
		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.setBypassHostnameVerification(true);		

		RestClient client = new RestClient(clientConfig);
		
		Resource resource = client.resource(
				"https://test.api.bubi.cn/oauth2/token?client_id=6d0e92030e9d63eb2c6f52aeb6d7e283&client_secret=31fb047098ebf639801f1268f40718d6&grant_type=client_credentials");
		String entity = resource.post(String.class).getMessage();
		log.debug("Entity Content:" + entity.toString());
	}
}
