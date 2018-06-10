package com.yancy.ipfs.actions;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.opensymphony.xwork2.ActionSupport;
import com.yancy.ipfs.mapper.UploadfileMapper;
import com.yancy.ipfs.pojo.Uploadfile;

public class UploadAction extends ActionSupport {
	private Uploadfile uploadfile;
	private List<Uploadfile> uploadfiles;

	public String execute() throws IOException {

		String resource = "mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();
		try {
			UploadfileMapper mapper = session.getMapper(UploadfileMapper.class);
			uploadfile = mapper.selectByExample(null).get(0);
			uploadfiles = mapper.selectByExample(null);
			System.out.print(uploadfile.getFilename());
		} finally {
			session.close();
		}

		return SUCCESS;
	}

	public Uploadfile getUploadfile() {
		return uploadfile;
	}

	public List<Uploadfile> getUploadfiles() {
		return uploadfiles;
	}

}