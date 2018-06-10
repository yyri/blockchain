package yyri.test;

import static us.monoid.web.Resty.data;
import static us.monoid.web.Resty.form;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import us.monoid.web.Resty;

public class RestyTest {

	public static void main(String[] args) throws URISyntaxException {
		String baseUrl = "https://test.api.bubi.cn/oauth2/token";
		String queryParam = "client_id=6d0e92030e9d63eb2c6f52aeb6d7e283&client_secret=31fb047098ebf639801f1268f40718d6&grant_type=client_credentials";
		String fullUrl = baseUrl + "?" + queryParam;
		URI baseUri = new URI(baseUrl);
		URI fullUri = new URI(fullUrl);
		try {
			// new Resty().json(baseUri).form(queryParam);
			new Resty().json(fullUrl,
					form(data("client_id", "6d0e92030e9d63eb2c6f52aeb6d7e283"),
							data("client_secret", "31fb047098ebf639801f1268f40718d6"),
							data("grant_type", "client_credentials")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
