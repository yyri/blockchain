package rong;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bubi4j.Account;
import bubi4j.Asset;
import bubi4j.OAuth;
import bubi4j.Status;

public class Bubi4j {
	private static final Logger log = LoggerFactory.getLogger(Bubi4j.class);

	@Test
	public void getAccountInfo() {
		try {
			String token = (new OAuth()).getAccessToken();
			log.debug("token:" + token);
			String accountInfo = new Account(token).info(Constants.BUBI_USERNAME);
			log.debug(accountInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void issueAsset() {
		try {
			String token = (new OAuth()).getAccessToken();
			log.debug("token:" + token);
			Asset asset = new Asset(token);
			log.debug(asset.issue(Constants.BUBI_ADDRESS, Constants.BUBI_USER_PASSWORD, Constants.BUBI_TRADENO,
					"Yueyang_Bubi_Stock_01", "cent", "100", "YueyangTestBubiAsset"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void assetInfo() {
		String token;
		try {
			token = (new OAuth()).getAccessToken();
			log.debug("token:" + token);
			Status status = new Status(token);
			String issueStatus = status.issueAsset(Constants.BUBI_TRADENO);
			log.debug("issueStatus:" + issueStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
