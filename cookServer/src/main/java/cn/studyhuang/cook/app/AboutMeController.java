package cn.studyhuang.cook.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import cn.studyhuang.cook.api.BaseResult;
import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.service.MenuService;
import cn.studyhuang.cook.service.UserService;

@Controller
@RequestMapping("my")
public class AboutMeController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;

	/**
	 * 我发布的菜谱
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("menu/{userId}")
	@ResponseBody
	public CallResult myMenu(@PathVariable Long userId) {
		CallResult result = new CallResult();
		if (userId == null) {
			result.fail("账号为空");
			return result;
		}
		Long id = Long.valueOf(userId);
		List<Menu> menus = menuService.getMenusByUserId(id);
		result.setData(menus);
		return result;
	}
	
	@RequestMapping(value="user/pwd",method=RequestMethod.POST )
	@ResponseBody
	public BaseResult editPassword(Long uid,String upassword) {
		BaseResult baseResult = new BaseResult();
		User user =userService.get(uid);
		if (user ==null) {
		   baseResult.fail("没有该用户");	
		   return baseResult;
		}
		user.setUpassword(upassword);
		userService.saveOrUpdate(user);
		return baseResult;
	}
	
	@RequestMapping(value="edit/user",method=RequestMethod.POST )
	@ResponseBody
	public BaseResult editUser(Long uid,String uname,String uemail,
			String udate) {
		BaseResult baseResult = new BaseResult();
		User user =userService.get(uid);
		if (user ==null) {
		   baseResult.fail("没有该用户");	
		   return baseResult;
		}
		user.setUemail(uemail);
		user.setUname(uname);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(udate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setUbirthday(date);
		userService.saveOrUpdate(user);
		return baseResult;
	}
	
	
}
