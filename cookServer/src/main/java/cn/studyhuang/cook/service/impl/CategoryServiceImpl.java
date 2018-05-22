package cn.studyhuang.cook.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.CategoryMapper;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.pojo.CategoryExample;
import cn.studyhuang.cook.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{

	@Resource
	private CategoryMapper  categoryMapper;
	
	@Override
	public CallResult  saveOrUpdate(Category category) {
		CallResult result = new CallResult();
		  if(StringUtils.isEmpty(category.getCid())) {
			int row=  categoryMapper.insert(category);
		  }else {
			int row = categoryMapper.updateByPrimaryKey(category);
		}
		return result;
	}

	@Override
	public Category get(Long id) {
		Category category = categoryMapper.selectByPrimaryKey(id); 
		return category;
	}

	@Override
	public void delete(Long id) {
		 categoryMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Category> findCateList() {
		
		return categoryMapper.selectCategories();
	}

	@Override
	public List<Category> findCateLists(Integer row, Integer limit) {
		CategoryExample categoryExample = new CategoryExample();
		categoryExample.setPage(row);
		categoryExample.setPageSize(limit);
		return categoryMapper.selectByExample(categoryExample);
	}

}
