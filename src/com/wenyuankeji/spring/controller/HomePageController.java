package com.wenyuankeji.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.model.BasePictureModel;
import com.wenyuankeji.spring.service.IBaseConfigService;
import com.wenyuankeji.spring.service.IBasePictureService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Controller
public class HomePageController {

	@Autowired
	private IBaseConfigService baseConfigService;

	@Autowired
	private IBasePictureService basePictureService;

	@Value("#{configProperties['imgIpHost']}")
	private String imgIpHost;

	@Value("#{configProperties['projectName']}")
	private String projectName;
	
	/**
	 * 加载图片
	 * @param model
	 * @return
	 */
	public void setPageData(Model model) throws BaseException{
		
		//BaseConfigModel baseconfig1 = baseConfigService.searchBaseConfigByCode("homepage", "1");
		BasePictureModel basePicture1 = basePictureService
				.searchBasePictureByPicId(1);
		//BaseConfigModel baseconfig2 = baseConfigService.searchBaseConfigByCode("homepage", "2");
		BasePictureModel basePicture2 = basePictureService
				.searchBasePictureByPicId(2);
		//BaseConfigModel baseconfig3 = baseConfigService.searchBaseConfigByCode("homepage", "3");
		BasePictureModel basePicture3 = basePictureService
				.searchBasePictureByPicId(3);
		//BaseConfigModel baseconfig5 = baseConfigService.searchBaseConfigByCode("homepage", "5");
		BasePictureModel basePicture5 = basePictureService
				.searchBasePictureByPicId(5);

		model.addAttribute("fileName1", basePicture1.getPicturepath());
		model.addAttribute("fileName2", basePicture2.getPicturepath());
		model.addAttribute("fileName3", basePicture3.getPicturepath());
		model.addAttribute("fileName5", basePicture5.getPicturepath());
		model.addAttribute("imgIpHost", imgIpHost);
	}
	

	@RequestMapping("/goHomepageChange")
	/**
	 * 跳转首页图片更换
	 * @param model
	 * @return
	 */
	public String goHomepageChange(HttpSession session, Model model)
			throws BaseException {
		BaseAdmininfoModel userinfo = (BaseAdmininfoModel) session.getAttribute("sessionUserinfo");
		if(userinfo != null){
			setPageData(model);
			return "homepage_change";
		}else{

			return "login";
		}
	}

	@RequestMapping("/HomepageChange")
	/**
	 * 首页图片上传
	 * @param request
	 * @param model
	 * @return
	 */
	public String HomepageChange(HttpServletRequest request,
			Model model, String fileName1, String fileName2, String fileName3,
			String fileName5) throws BaseException, IOException {

		//String serverPath = request.getServletContext().getRealPath("/userImg/");
		Date createtime = new Date();
		
		BasePictureModel basePicture1 = new BasePictureModel();
		basePicture1.setPicid(1);
		basePicture1.setPicturepath(fileName1);
		basePicture1.setCreatetime(createtime);
		if(!basePictureService.updateBasePicture(basePicture1)){
			model.addAttribute("message", "发型图片,上传失败");
		}
		
		BasePictureModel basePicture2 = new BasePictureModel();
		basePicture2.setPicid(2);
		basePicture2.setPicturepath(fileName2);
		basePicture2.setCreatetime(createtime);
		if(!basePictureService.updateBasePicture(basePicture2)){
			model.addAttribute("message", "美发师图片,上传失败");
		}
		
		BasePictureModel basePicture3 = new BasePictureModel();
		basePicture3.setPicid(3);
		basePicture3.setPicturepath(fileName3);
		basePicture3.setCreatetime(createtime);
		if(!basePictureService.updateBasePicture(basePicture3)){
			model.addAttribute("message", "美发店图片,上传失败");			
		}
		
		BasePictureModel basePicture5 = new BasePictureModel();
		basePicture5.setPicid(5);
		basePicture5.setPicturepath(fileName5);
		basePicture5.setCreatetime(createtime);
		if(!basePictureService.updateBasePicture(basePicture5)){
			model.addAttribute("message", "预约图片,上传失败");
		}

/*		if (!baseConfigService.updateBaseConfig("homepage", "1", fileName1)) {
			model.addAttribute("message", "发型图片,上传失败");
		}

		if (!baseConfigService.updateBaseConfig("homepage", "2", fileName2)) {
			model.addAttribute("message", "美发师图片,上传失败");
		}

		if (!baseConfigService.updateBaseConfig("homepage", "3", fileName3)) {
			model.addAttribute("message", "美发店图片,上传失败");
		}

		if (!baseConfigService.updateBaseConfig("homepage", "5", fileName5)) {
			model.addAttribute("message", "预约图片,上传失败");
		}*/
		
		setPageData(model);

		return "homepage_change";
	}

	@RequestMapping("UploadImages")
	/*
	 * 上传图片
	 */
	public void UploadImages(HttpServletRequest request,
			@RequestParam("fileName") CommonsMultipartFile file,
			String fileType, Model model) throws IOException {
		HttpSession session = request.getSession();
		String path = "";
		if (!file.isEmpty()) {

			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));

			Map<String, String> fileTypeMap = new HashMap<String, String>();
			fileTypeMap.put(".jpg", ".jpg");
			fileTypeMap.put(".jepg", ".jepg");
			fileTypeMap.put(".png", ".png");

			if (fileTypeMap.get(type) == null) {
				model.addAttribute("errorMsg", "上传的文件格式错误！");
				return;
			}

			// 2MB
//			long fileSize = 2097152;
//
//			if (file.getSize() > fileSize) {
//				model.addAttribute("errorMsg", "上传的文件不能大于2MB！");
//				return;
//			}

			try {
				String fileName = System.currentTimeMillis() + type;
				String tempPath = request.getServletContext().getRealPath("/userImg/");
				
				//tempPath = tempPath.replaceAll("shunjianmeimanage", "shunjianmei");				
				tempPath = tempPath.replaceAll("shunjianmeimanage", projectName);
				
				File pathFile = new File(tempPath);
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				path = tempPath + "/" + fileName;
				File localFile = new File(path);
				// 写文件到本地
				file.transferTo(localFile);
				if (ShunJianMeiUtil.compressPic(path, path)) {
					session.setAttribute(fileType, "/userImg/" + fileName);
				}else {
					model.addAttribute("errorMsg", "上传失败！");
				}
				
			} catch (Exception e) {
				model.addAttribute("errorMsg", "上传失败！");
			}
		}
	}

	@RequestMapping("getSessionImageId")
	/*
	 * 获取session里的图片ID
	 */
	public @ResponseBody Map<String, Object> getSessionImageId(
			HttpServletRequest request, Model model, String fileType) {
		Map<String, Object> fileNameMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String fileName = (String) session.getAttribute(fileType);
		if (fileName != null && fileName.length() > 0) {
			fileNameMap.put("info", true);
			fileNameMap.put("fileName", fileName);
		} else {
			fileNameMap.put("info", false);
			fileNameMap.put("msg", "上传文件失败！");
		}

		return fileNameMap;
	}
	
	@RequestMapping("UploadUserInfoImages")
	public void UploadUserInfoImages(HttpServletRequest request,
			@RequestParam("fileName") CommonsMultipartFile file, String fileType,
			Model model) throws IOException {
		HttpSession session = request.getSession();
		String path = "";
		if (!file.isEmpty()) {

			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().indexOf("."));

			Map<String, String> fileTypeMap = new HashMap<String, String>();
			fileTypeMap.put(".jpg", ".jpg");
			fileTypeMap.put(".jepg", ".jepg");
			fileTypeMap.put(".png", ".png");
			fileTypeMap.put(".doc", ".doc");

			if (fileTypeMap.get(type) == null) {
				model.addAttribute("errorMsg", "上传的文件格式错误！");
				return;
			}
			
			//3MB
//			long fileSize = 2097152;
//			
//			if(file.getSize() > fileSize){ 
//				model.addAttribute("errorMsg", "上传的文件不能大于2MB！");
//				return;
//			}

			try {
				String fileName = System.currentTimeMillis() + type;
				String tempPath = request.getServletContext().getRealPath("/userImg/");

				//tempPath = tempPath.replaceAll("shunjianmeimanage", "shunjianmei");				
				tempPath = tempPath.replaceAll("shunjianmeimanage", projectName);
				
				File pathFile = new File(tempPath);
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				path = tempPath + "/" + fileName;
				File localFile = new File(path);
				// 写文件到本地
				file.transferTo(localFile);
				if (ShunJianMeiUtil.compressPic(path, path)) {
					session.setAttribute(fileType, fileName);
				}else {
					model.addAttribute("errorMsg", "上传失败！");
				}
				
			} catch (Exception e) {
				model.addAttribute("errorMsg", "上传失败！");
			}
		}
	}
	
	@RequestMapping("getSessionUserInfoImageId")
	/*
	 * 获取session里的图片ID
	 */
	public @ResponseBody Map<String, Object> getSessionUserInfoImageId(HttpServletRequest request, Model model, String fileType) {
		Map<String, Object> fileNameMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String fileName = (String)session.getAttribute(fileType);
		if(fileName != null &&  fileName.length() > 0){
			fileNameMap.put("info", true);
			fileNameMap.put("fileName", fileName);
		}else {
			fileNameMap.put("info", false);
			fileNameMap.put("msg", "上传文件不能大于2M");
		}
		
		return fileNameMap;
	}
	

}