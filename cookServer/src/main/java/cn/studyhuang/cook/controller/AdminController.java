package cn.studyhuang.cook.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.AdminMapper;
import cn.studyhuang.cook.pojo.Admin;
import cn.studyhuang.cook.service.AdminService;

/**
 * 管理员
 * @author huang
 *
 *2018年1月10日 上午12:30:34
 */
@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Resource
	private AdminService adminService;
	
	@RequestMapping(value="login",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public CallResult login(String aname,String apassword,HttpSession session) {
		CallResult callResult = new CallResult();
		  if (StringUtils.isEmpty(aname)) {
				callResult.setMsg("用户名不能为空");
				callResult.fail("用户名不能为空");
				return callResult;
			}
		     
		     if (StringUtils.isEmpty(apassword)) {
		    	 callResult.setMsg("密码不能为空");
		    	 callResult.fail("密码不能为空");
					return callResult;
			}
		
		Admin admin=adminService.findAdminByName(aname);
		if (admin == null) {
			callResult.fail("用户不存在");
			return callResult;
		}
		if (admin.getApassword().equals(apassword)) {
			session.setAttribute("admin", admin);
			System.out.println(admin.getBirthday());
			callResult.setData(admin);
			return callResult;
		}
		callResult.fail("密码错误，请重新输入");
		return callResult;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	@ResponseBody
	public CallResult save(String birthday,Admin admin,BindingResult bindingResult) {
		CallResult callResult =new CallResult();
		if (admin.getAname()!= null) {
			Admin admin2 = adminService.findAdminByName(admin.getAname());
			if (admin2 != null) {
				if (admin2.getAid()==null) {
					callResult.fail("该管理员已经存在，不能重复添加");
					return callResult;
				}
				
			}
			try {
				admin.setBirthday(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
			
		}
		callResult=adminService.saveOrUpdate(admin);
		return callResult;
		
	}
	
	@RequestMapping(value="list",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public CallResult listAdmins(Integer limit,Integer page) {
		CallResult callResult =new CallResult();
		if (page == null ) {
			page=1;
		}
		if (limit == null ) {
			limit=5;
		}
		
		PageHelper.startPage(page, limit);
		 List<Admin> admins=adminService.findAdminList();
		 PageInfo pageInfo = new PageInfo<>(admins);
		 callResult.setData(pageInfo);
		return callResult;
	}
	
	@RequestMapping(value="get/{id}", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public CallResult getAdmin(@PathVariable Long id) {
		CallResult callResult =new CallResult();
		if (id == null) {
			callResult.fail("参数错误");
			return null;
		}
		Admin admin = adminService.get(id);
		callResult.setData(admin);
		return callResult;
	}
	
	@RequestMapping("loginout")
	@ResponseBody
	public CallResult logout(HttpSession session,HttpServletResponse response) {
		CallResult result = new CallResult();
		session.removeAttribute("admin");
		try {
			response.sendRedirect("http://localhost:8080/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="editpass",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public CallResult editPassword(Long aid,String oldpass,String repass,String newpass,HttpServletResponse response) {
	   CallResult callResult =new CallResult();
	   Admin admin=adminService.get(aid);
	   if (admin.getApassword().equals(oldpass)) {
		   if (repass.equals(newpass)) {
			   admin.setApassword(newpass);
			   adminService.saveOrUpdate(admin);
			   callResult.succee("修改密码成功");
				 return callResult;
		  }else {
			callResult.fail("两次密码不一致");
			return callResult;
		}
		   
	   }else {
		   callResult.fail("旧密码错误");
		   return callResult;
	   }
	   
	}
	
	@RequestMapping(value="delete/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public CallResult  delete(@PathVariable Long id) {
		CallResult callResult = new CallResult();
		if (id == null ) {
			callResult.fail("参数传递错误");
			return callResult;			
		}
		adminService.delete(id);
		callResult.setSuccee(true);
		return callResult;
	}
}
