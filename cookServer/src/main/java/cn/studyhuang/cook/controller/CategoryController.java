package cn.studyhuang.cook.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Resource
	private CategoryService categoryService;
	
	@RequestMapping(value="get/{id}" ,method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public CallResult get(@PathVariable Long id) {
		CallResult callResult = new CallResult();
		System.out.println("getfeilei");
		Category category = categoryService.get(id);
		callResult.setData(category);
		return callResult;
	}
	
	@RequestMapping(value="delete/{id}")
	@ResponseBody
	public CallResult delete(@PathVariable Long id) {
		CallResult callResult = new CallResult();
		categoryService.delete(id);
		callResult.succee("删除成功");
		return  callResult;
		
	}
	
	@RequestMapping("save")
	@ResponseBody
	public CallResult saveOrUpdate(Category category) {
		CallResult result = new CallResult();
	//	Category category = new Category();
//		category.setCname(cname);
		 if(StringUtils.isEmpty(category.getCname())) {
			 result.fail("请输入类别");
			 return result;
		 }else {
			 categoryService.saveOrUpdate(category);
		 }
		 System.out.println(category);
		 result.setData("zhazha");
		return result;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public CallResult listCategories(Integer page , Integer limit) {
		CallResult result = new CallResult();
		if (page == null ) {
			page=1;
		}
		if (limit == null ) {
			limit=5;
		}
		
		PageHelper.startPage(page, limit);
		Integer row = (page-1) * limit;
	    List<Category> cateLists = categoryService.findCateList();
	// 	result.setData(cateLists);

	 	PageInfo<Category> pageInfo = new PageInfo<Category>(cateLists,limit);
	 //	pageInfo.setPageNum(page-1);
	  //  pageInfo.setList(cateLists);
	    result.setData(cateLists);
	    return result;
	}
}