package com.yancy.ipfs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.yancy.ipfs.mapper.UploadfileMapper;
import com.yancy.ipfs.pojo.Uploadfile;

public class CRUDCases {

	SqlSessionFactory sqlSessionFactory = null;

	@Before
	public void init() {
		String resource = "mybatis.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void query() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UploadfileMapper mapper = session.getMapper(UploadfileMapper.class);
			Uploadfile uploadfile = mapper.selectByExample(null).get(0);
			System.out.print(uploadfile.getFilename());
		} finally {
			session.close();
		}
	}

	@Test
	public void insert() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UploadfileMapper mapper = session.getMapper(UploadfileMapper.class);
			for (int i = 3; i < 100; i++) {
				Uploadfile uploadfile = new Uploadfile();
				uploadfile.setFilename(i + ".LICENSE");
				uploadfile.setIpfshash("QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC");
				mapper.insert(uploadfile);
			}
			session.commit();

		} finally {
			session.close();
		}
	}

}
