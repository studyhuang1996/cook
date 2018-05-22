package cn.studyhuang.cook.service;

import java.util.List;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Category;

/**
 * 菜谱分类
 * @author huang
 *
 */
public interface CategoryService {
 
	CallResult saveOrUpdate(Category category);
	
	Category get(Long id);
	
	void delete(Long id);
	
	List<Category> findCateList();

	List<Category> findCateLists(Integer row, Integer limit);
	

}
