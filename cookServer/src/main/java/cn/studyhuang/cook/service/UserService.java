package cn.studyhuang.cook.service;
/**
 * 用户信息服务
 * @author huang
 *
 *2018年1月7日 下午4:46:34
 */



import java.util.List;

import cn.studyhuang.cook.api.BaseResult;
import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.User;

/**
 * 用户服务
 * @author huang
 *
 *2018年1月7日 下午6:48:08
 */
public interface UserService {

	BaseResult saveOrUpdate(User user);
   
   CallResult deleteById(Long uid);
   
   User get(Long uid);
   
   List<User> getUsers();
   User getUserByName(String uname);

  User getUserByLogin(String userName, String password);
}
