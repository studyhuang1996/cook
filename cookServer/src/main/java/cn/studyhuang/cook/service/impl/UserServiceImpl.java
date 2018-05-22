package cn.studyhuang.cook.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.studyhuang.cook.api.BaseResult;
import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.UserMapper;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.pojo.UserExample;
import cn.studyhuang.cook.service.UserService;
/**
 * 用户服务层实现
 * @author huang
 *
 *2018年1月7日 下午6:50:33
 */
@Service
public class UserServiceImpl  implements UserService{

	@Resource
	private UserMapper userMapper;
	@Override
	public BaseResult saveOrUpdate(User user) {
		BaseResult callResult = new BaseResult();
		if(user.getUid() == null) {
		  user.setUbirthday(new Date());
			userMapper.insert(user);
		}else {
			userMapper.updateByPrimaryKey(user);
		}
		return callResult;
	}

	@Override
	public CallResult deleteById(Long uid) {
		CallResult callResult = new CallResult();
		int row = userMapper.deleteByPrimaryKey(uid);
		if(row == 0) {
			callResult.fail("删除失败");
			return callResult;
		}
		return callResult;
	}

	@Override
	public User get(Long uid) {
		User user = userMapper.selectByPrimaryKey(uid);
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = userMapper.selectByExample(null);
		return users;
	}

	@Override
	public User getUserByLogin(String userName, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUnameEqualTo(userName);
		List<User> users = userMapper.selectByExample(userExample);
		if (null != users && users.size()>0) {
		  User user=users.get(0);
			if (password.equals(user.getUpassword())) {
				return user;
			}else {
			   System.out.println("密码不正确");
				return null;
			}
		}
		return null;
	}

	@Override
	public User getUserByName(String uname) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUnameEqualTo(uname);
		List<User> users = userMapper.selectByExample(userExample);
		if (null != users && users.size()>0) {
		  User user=users.get(0);
		  return user;
		}
		return null;
	}

}
