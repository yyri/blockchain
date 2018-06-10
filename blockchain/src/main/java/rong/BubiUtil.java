package rong;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoftware.restclient.RestClient;
import org.jsoftware.restclient.RestClientResponse;
import org.jsoftware.restclient.impl.DefaultRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BubiUtil {

	/**
	 * Temporary client_id for test
	 */
	public static String client_id = "6d0e92030e9d63eb2c6f52aeb6d7e283";
	/**
	 * Temporary client_secret for test
	 */
	public static String client_secret = "31fb047098ebf639801f1268f40718d6";

	private static final Logger log = LoggerFactory.getLogger(BubiUtil.class);

	/**
	 * @param clientSecret
	 * @return
	 */
	public static String getToken(String clientId, String clientSecret) {
		String token = null;
		RestClient restClient = new DefaultRestClient();
		RestClientResponse response;
		try {
			response = restClient.post("https://test.api.bubi.cn/oauth2/token").parameter("client_id", clientId)
					.parameter("client_secret", clientSecret).parameter("grant_type", "client_credentials").execute();
			log.debug("response content:" + response.getContent());
			log.debug("access_token:" + (token = (String) response.json("access_token")));
			log.debug("expires_in:" + ((Integer) response.json("expires_in")).toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				restClient.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return token;
	}

	public static String issueAsset() {
		String result = null;

		return result;
	}

}
