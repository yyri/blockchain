package com.yancy.ipfs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

/**
 * Servlet implementation class DoUpLoadServlet
 */
@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Uploader() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uploadFileName = ""; // 上传的文件名
		String fieldName = ""; // 表单字段元素的name属性值
		PrintWriter out = response.getWriter();// 获取out对象
		// 请求信息中的内容是否是multipart类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 上传文件的存储路径（服务器文件系统上的绝对文件路径）
		String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/");
		File uploadFile = new File(uploadFilePath); 
		if (!uploadFile.exists()) // 判断文件或目录是否存在
			uploadFile.mkdirs(); // 创建指定的目录，包括所有必需但不存在的父目录
		
		// 创建临时文件目录路径
		File tempPatchFile = new File("c:\\downloads\\buffer\\");
		if (!tempPatchFile.exists()) // 判断文件或目录是否存在
			tempPatchFile.mkdirs(); // 创建指定的目录，包括所有必需但不存在的父目录
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小4kb
			factory.setSizeThreshold(4096);
			// 设置上传文件用到临时文件存放路径
			factory.setRepository(tempPatchFile);
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置单个文件的最大限制
			upload.setSizeMax(1024 * 30);
			try {
				// 解析form表单中所有文件
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) { // 依次处理每个文件
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) { // 文件表单字段
						String fileName = item.getName();
						// 通过Arrays类的asList()方法创建固定长度的集合
						List<String> filType = Arrays.asList("gif", "bmp", "jpg");
						String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
						if (!filType.contains(ext)) // 判断文件类型是否在允许范围内
							out.print("Upload failed.Only gif、bmp、jpg allowed.");
						else {
							if (fileName != null && !fileName.equals("")) {
								File fullFile = new File(item.getName());
								File saveFile = new File(uploadFilePath, fullFile.getName());
								item.write(saveFile);
								uploadFileName = fullFile.getName();
								out.print("File Uploaded:" + uploadFileName + ".File Size:" + item.getSize() + "bytes!");

								// init the clinet
								IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
								ipfs.refs.local();
								NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(saveFile);
								MerkleNode addResult = ipfs.add(file).get(0);
								System.out.print(addResult.hashCode());
								System.out.print(addResult.toJSONString());
								out.print(addResult.hashCode());
							}
						}
					}
				}
			} catch (FileUploadBase.SizeLimitExceededException ex) {
				out.print("File too big to upload. Filesize limited to " + upload.getSizeMax() + " bytes!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
