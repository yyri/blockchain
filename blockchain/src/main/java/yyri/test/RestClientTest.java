package yyri.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoftware.restclient.RestClient;
import org.jsoftware.restclient.RestClientResponse;
import org.jsoftware.restclient.impl.DefaultRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClientTest {
	private static final Logger log = LoggerFactory.getLogger(RestClientTest.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestClient restClient = new DefaultRestClient(); // new instance of
															// RestClient
		try {
			RestClientResponse response = restClient.post("https://test.api.bubi.cn/oauth2/token") // API
					// URL
					// (http
					// post)
					.parameter("client_id", "6d0e92030e9d63eb2c6f52aeb6d7e283") // Http
																				// form
																				// parameters
					.parameter("client_secret", "31fb047098ebf639801f1268f40718d6")
					.parameter("grant_type", "client_credentials").execute();
			log.debug(response.getContent());
			log.debug((String) response.json("access_token"));
			log.debug(((Integer) response.json("expires_in")).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
