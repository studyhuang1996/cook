package cn.studyhuang.cook.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.BaseResult;
import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.service.UserService;

/**
 * 用户控制层
 * @author huang
 *
 *2018年1月7日 下午4:43:14
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	  @Resource
      private UserService userService;
	
	/**
	 * 获取用户个人信息
	 * @return
	 */
	@RequestMapping("get/{uid}")
	@ResponseBody
	public CallResult getUser(@PathVariable Long uid) {
		CallResult result = new CallResult();
		User user = userService.get(uid);
		result.setData(user);
		return result;
	}
	@RequestMapping("login")
	@ResponseBody
	public CallResult login(String userName,String password,HttpSession session) {
		CallResult callResult = new CallResult();
		System.out.println("====================");
	     if (StringUtils.isEmpty(userName)) {
			callResult.setMsg("用户名不能为空");
			callResult.fail("用户名不能为空");
			return callResult;
		}
	     if (StringUtils.isEmpty(password)) {
	    	 callResult.setMsg("密码不能为空");
	    	 callResult.fail("密码不能为空");
				return callResult;
		}
	     
	    User user= userService.getUserByLogin(userName,password);
	    callResult.setData(user);
	    session.setAttribute("userinfo", user );
		return callResult;
	}
	
	
	@RequestMapping(value="save",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseResult save(String uname,String uemail,String upassword) {
		BaseResult callResult =new BaseResult();
		User user =new User();
		user.setUname(uname);
		user.setUpassword(upassword);
		user.setUemail(uemail);
		user.setUtel(uname);
		System.out.println(user.getUname()+"==========="+uname);
		System.out.println("====="+user.getUemail());
		user.setUstate("1");
	
		callResult = userService.saveOrUpdate(user);
		
		return callResult;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public CallResult listUsers(Integer page,Integer limit) {
		CallResult callResult =new CallResult();
		if (page == null ) {
			page=1;
		}
		if (limit == null ) {
			limit=5;
		}
		PageHelper.startPage(page, limit);
		 List<User> users = userService.getUsers();
		 if(null == users) {
			callResult.fail("没有用户"); 
			return callResult;
		 }
		 PageInfo pageInfo = new PageInfo(users);
		 callResult.setData( pageInfo);
		return callResult;
	}
	
	

	
	@RequestMapping("delete/{uid}")
	@ResponseBody
	public CallResult deleteUser(@PathVariable Long uid) {
		CallResult callResult =new CallResult();
		callResult = userService.deleteById(uid);
		return callResult;
	}
}
