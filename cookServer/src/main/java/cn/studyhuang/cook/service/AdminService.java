package cn.studyhuang.cook.service;

import java.util.List;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Admin;


public interface AdminService {
	
  CallResult saveOrUpdate(Admin admin);
	
	Admin get(Long id);
	
	void delete(Long id);
	
	List<Admin> findAdminList();

//	Admin getAdminByName(String aname,String apassword);
	
   Admin findAdminByName(String aname);
}
