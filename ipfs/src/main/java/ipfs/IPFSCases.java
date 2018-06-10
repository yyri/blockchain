package ipfs;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

public class IPFSCases {

	private IPFS ipfs = null;

	@Before
	public void before()  {
		// init the clinet
		ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
	}

	@Test
	public void query()  {
		// query api
		try {
			ipfs.refs.local();
			NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("C:/downloads/yancy.txt"));
			MerkleNode addResult = ipfs.add(file).get(0);
			System.out.print(addResult.hashCode());
			System.out.print(addResult.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		// clean the env

	}

}
