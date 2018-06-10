package rong;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BubiUtilTests {

	private static final Logger log = LoggerFactory.getLogger(BubiUtilTests.class);

	@Test
	public void getToken() {
		log.debug("Bubi Util Tests - GetToken.");
		String token = BubiUtil.getToken(BubiUtil.client_id, BubiUtil.client_secret);
		log.debug("Token Returned:" + token);
	}

}
