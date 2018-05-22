package cn.studyhuang.cook.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.api.PropertiesUtil;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.service.IFileService;
import cn.studyhuang.cook.service.MenuService;

@Controller
@RequestMapping("upload")
public class uploadController {

	@Autowired
	private IFileService iFileService;
	@Autowired
	private MenuService menuService;

	// 文本上传文件
	@RequestMapping(value = "image", method = RequestMethod.POST)
	@ResponseBody
	public CallResult richtextUpload(HttpServletRequest request, HttpServletResponse response) {
		CallResult result = new CallResult();
	
		String path = request.getSession().getServletContext().getRealPath("upload");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// @RequestParam(value="mpic",required = false)
		MultipartFile multipartFile = multipartRequest.getFile("file");
		String targetFile = iFileService.upload(multipartFile, path);
		if (StringUtils.isBlank(targetFile)) {
			result.fail("上传失败");
			return result;
		}
		String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFile;
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("msg", "上传文件成功");
		resultMap.put("url", url);
        
		
		response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
		result.setDatas(resultMap);
		return result;
	}
	
	//TODO:
	/**
	 * 安卓上传图片菜名等到服务端
	 * @param menu
	 * @param multipartFile
	 * @param request
	 * @param response
	 * @return
	 */
	//
	@RequestMapping(value = "api/addmenu/", method = RequestMethod.POST
			,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	@ResponseBody
	public CallResult apiUpload(String mname,String mdesc,
			Long cid,Long userId,@RequestParam(value="mpic") MultipartFile multipartFile,
			HttpServletRequest request, HttpServletResponse response) {
		CallResult result = new CallResult();
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isEmpty(mname)) {
			resultMap.put("msg", "操作失败，传参错误");
			result.setData(resultMap);
			return result;
		}
		String path = request.getSession().getServletContext().getRealPath("upload");
	//	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// @RequestParam(value="mpic",required = false)
	//	MultipartFile multipartFile = multipartRequest.getFile("mpic");
		String targetFile = iFileService.upload(multipartFile, path);
		System.out.println(targetFile);
		if (StringUtils.isBlank(targetFile)) {
			result.fail("上传失败");
			return result;
		}
		String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFile;
		resultMap.put("msg", "上传文件成功");
		resultMap.put("url", url);
		Menu menu = new Menu();
		menu.setMname(mname);
		menu.setMpic(url);
		menu.setCid(cid);
		menu.setMdesc(mdesc);
		menu.setUserid(userId);
		menuService.saveOrUpdate(menu);
		response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
		result.setDatas(resultMap);
		return result;
	}
	
    
}
