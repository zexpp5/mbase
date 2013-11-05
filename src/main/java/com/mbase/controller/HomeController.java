package com.mbase.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mbase.domain.Article;
import com.mbase.service.ArticleService;

@Controller
public class HomeController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView showHomePage() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = { "/captcha" }, method = RequestMethod.GET)
	public ModelAndView getCaptcha() {
		return new ModelAndView("image");
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String postArticle(@RequestParam("title") String title,
			@RequestParam("content") String content) {
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		articleService.createArticle(article);
		System.out.println(title);
		System.out.println(content);
		return "index";
	}

	@RequestMapping(value = "/editor/upload", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody
	Object editorUpload(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 文件保存目录路径
		String savePath = "/Users/lianghongyun/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/mbase/resources/img/userimg/";

		// 文件保存目录URL
		String saveUrl = "static/img/userimg/";
		Map map = new HashMap();

		// 最大文件大小
		long maxSize = 1000000;

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");

		if (!ServletFileUpload.isMultipartContent(request)) {
			map.put("error", 1);
			map.put("message", "请选择文件。");
			return map;
		}
		FileOutputStream fos = null;

		try {
			Iterator<String> fileNames = request.getFileNames();
			while (fileNames.hasNext()) {
				MultipartFile file = request.getFile(fileNames.next());
				String filename = file.getOriginalFilename();
				String mimetype = file.getContentType();
				long size = file.getSize();

				// 检查文件大小
				if (size > maxSize) {
					map.put("error", 1);
					map.put("message", "上传文件大小超过限制。");
					return map;
				}

				// 检查扩展名
				String fileExt = filename.substring(
						filename.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get("image").split(","))
						.contains(fileExt)) {
					map.put("error", 1);
					map.put("message",
							"上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image")
									+ "格式。");
					return map;
				}

				InputStream is;
				try {
					is = file.getInputStream();
				} catch (IOException e) {
					map.put("error", 1);
					map.put("message", "无法读取文件");
					return map;
				}

				File userDirectory = new File(savePath);
				if (!userDirectory.exists()) {
					boolean created = userDirectory.mkdir();
					if (!created) {
						map.put("error", 1);
						map.put("message", "无法创建文件夹");
						return map;
					}
				}
				
				String uuid = UUID.randomUUID().toString();
				String fileNameFull = FilenameUtils.concat(
						userDirectory.getPath(), filename);
				File localfile = new File(fileNameFull);
				if (localfile.exists()) {
					map.put("error", 1);
					map.put("message", "存在同名文件");
					return map;
				}
				fos = new FileOutputStream(localfile);
				IOUtils.copy(is, fos);
				fos.flush();
				map.put("error", 0);
				map.put("url", saveUrl + filename);
			}
		} catch (Exception e) {
			map.put("error", 1);
			map.put("message", "内部错误。");
			return map;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// ignore this
				}
			}
		}
		return map;
	}

	@RequestMapping(value = "/editor/manager")
	public String editorManager() {
		return "file_manager_json";
	}

	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
