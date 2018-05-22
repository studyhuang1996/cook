package cn.studyhuang.cook.service;

import java.util.List;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Menu;

/**
 * 菜谱服务层
 * @author huang
 *
 *2018年1月7日 下午11:04:24
 */
public interface MenuService {


	   CallResult saveOrUpdate(Menu user);
	   
	   void  deleteById(Long uid);
	   
	   Menu get(Long uid);
	   
	   List<Menu> getMenus(Integer page,Integer limit);

	List<Menu> getMenusByName(String mname);

	List<Menu> getMenusByCid(Long cid);

	List<Menu> getMenusByUserId(Long userId);

	List<Menu> getMenu();
}
