package com.alzz.demo.utils;

import com.alzz.demo.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/** 功能描述:  文件上传控制器
* @Author: lzx
* @Date: 2020/1/16 15:15
*/
public class UploadActionUtil {

	public static List<String> uploadFile(HttpServletRequest request) throws Exception {
		List<String> list = new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			while (iterator.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iterator.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (!StringUtils.isEmpty(myFileName)) {
						String fileType = myFileName.substring(myFileName.lastIndexOf("."));
						// String tempName="demo"+fileType;
						String tempName = UUID.randomUUID().toString() + fileType;
						// 创建文件夹
						String folderPath = Constants.SAVE_FILE_PATH + File.separator + folderName();
						File fileFolder = new File(folderPath);
						if (!fileFolder.exists() && !fileFolder.isDirectory()) {
							fileFolder.mkdir();
						}
						File uploadFile = new File(folderPath + File.separator + tempName);
						file.transferTo(uploadFile);
						myFileName = folderName() + File.separator + tempName;
						list.add(Constants.SAVE_FILE_PATH + "//" + myFileName);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 得年月日的文件夹名称
	 * 
	 * @return
	 */
	public static String getCurrentFolderName()  throws Exception{
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) + "" + (now.get(Calendar.MONTH) + 1) + "" + now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 创建文件夹
	 * 
	 * @param  folderName
	 */
	public static void createFolder(String folder) throws Exception {
		File file = new File(folder);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String extFile(String fileName)  throws Exception{
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 当前日期当文件夹名
	 * 
	 * @return
	 */
	public static String folderName() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(new Date());
		return str;
	}
}
