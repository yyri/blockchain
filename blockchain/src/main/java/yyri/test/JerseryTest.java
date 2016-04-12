package yyri.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.wink.common.internal.MultivaluedMapImpl;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JerseryTest {

	private static final Logger log = LoggerFactory.getLogger(JerseryTest.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ClientConfig clientConfig = new ClientConfig();
			Client client = ClientBuilder.newClient(clientConfig);

			WebTarget webTarget = client.target("https://test.api.bubi.cn");
			WebTarget resourceWebTarget = webTarget.path("oauth2").path("token");

			MultivaluedMapImpl params = new MultivaluedMapImpl();
			params.add("client_id", "6d0e92030e9d63eb2c6f52aeb6d7e283");
			params.add("client_secret", "31fb047098ebf639801f1268f40718d6");
			params.add("grant_type", "client_credentials");

			WebTarget resourceWebTargetWithQueryParam = resourceWebTarget
					.queryParam("client_id", "6d0e92030e9d63eb2c6f52aeb6d7e283")
					.queryParam("client_secret", "31fb047098ebf639801f1268f40718d6")
					.queryParam("grant_type", "client_credentials");
			Invocation.Builder invocationBuilder = resourceWebTargetWithQueryParam
					.request(MediaType.APPLICATION_FORM_URLENCODED);
			// String result =
			// resourceWebTarget.type(MediaType.APPLICATION_FORM_URLENCODED).post(String.class,
			// params);
			// System.out.println(result);
			// Response response =
			// resourceWebTarget.request().accept(MediaType.APPLICATION_FORM_URLENCODED)
			// .post(String.class, params);
			Response response = invocationBuilder.get();
			System.out.println(response.getStatus());
			// System.out.println(response.readEntity(String.class));
			// System.out.println(response.getStatus());
			// System.out.println(response.readEntity(String.class));
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
