package cn.studyhuang.cook.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.AdminMapper;
import cn.studyhuang.cook.pojo.Admin;
import cn.studyhuang.cook.pojo.AdminExample;
import cn.studyhuang.cook.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
  
	@Resource
	private AdminMapper adminDao;
	
	@Override
	public CallResult saveOrUpdate(Admin admin) {
		CallResult callResult = new CallResult();
		if(null == admin.getAid()) {
			adminDao.insert(admin);
		}else {
			adminDao.updateByPrimaryKey(admin);
			
		}
		return callResult;
	}

	@Override
	public Admin get(Long id) {
		// TODO Auto-generated method stub
		return adminDao.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long id) {
	  adminDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Admin> findAdminList() {
		
		return adminDao.selectByExample(null);
	}

//	/**
//	 * 通过用户名来查询
//	 */
//	@Override
//	public Admin getAdminByName(String aname,String apassword) {
//		 AdminExample example = new AdminExample();
//		 example.createCriteria().andAnameEqualTo(aname);
//		List<Admin> admin= adminDao.selectByExample(example);
//		if(admin.size() < 0 && admin == null) {
//			return null;
//		}
//	  	return admin.get(0);
//	}

	@Override
	public Admin findAdminByName(String aname) {
		if (StringUtils.isEmpty(aname)) {
			return null;
		}
		 AdminExample example = new AdminExample();
		 example.createCriteria().andAnameEqualTo(aname);
		 List<Admin> admin= adminDao.selectByExample(example);
			if(admin.size() <= 0 || admin == null) {
				return null;
			}
			return admin.get(0);
	}

}
