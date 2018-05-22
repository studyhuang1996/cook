package cn.studyhuang.cook.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.service.CategoryService;
import cn.studyhuang.cook.service.MaterialService;
import cn.studyhuang.cook.service.MenuService;
import cn.studyhuang.cook.service.StepService;

@Controller
@RequestMapping("menu")
public class MenuController {

	@Resource
	private MenuService menuService;
	@Resource
	private CategoryService categoryService;

	@Resource
	private StepService stepService;

	@Resource
	private MaterialService materialService;

	@RequestMapping("basic")
	@ResponseBody
	public CallResult basic() {
		CallResult callResult = new CallResult();
		List<Category> findCateList = categoryService.findCateList();
		callResult.putData("cate", findCateList);
		return callResult;
	}
	
	
	@RequestMapping("save")
	@ResponseBody
	public CallResult save(Menu menu,BindingResult  bindingResult) {
		CallResult callResult = new CallResult();
         if (null == menu) {
			callResult.fail("请输入所要保存的信息");
			return callResult;
		}
         callResult = menuService.saveOrUpdate(menu);
		return callResult;
	}

	@RequestMapping("/listmenus/")
	@ResponseBody
	public CallResult getMenusByName(String querys) {
		CallResult callResult = new CallResult();
		System.out.println(querys + "============");
		List<Menu> menus = menuService.getMenusByName(querys);
		for (Menu menu : menus) {
			System.out.println(menu.getMname()+" ");
		}
		callResult.setData(menus);
		return callResult;
	}

	@RequestMapping("query/{querys}/")
	@ResponseBody
	public CallResult getQueryMenu(@PathVariable String querys,Integer page,Integer limit) {
		CallResult callResult = new CallResult();
		if (StringUtils.isEmpty(querys)) {
			callResult.fail("请输入所要查询的内容");
			return callResult;
		}
		if (page == null) {
			page =1;
		}
		if (limit == null) {
			limit =5;
		}
		PageHelper.startPage(page, limit);
		List<Menu> menus = menuService.getMenusByName(querys);
		List<Category> findCateList = categoryService.findCateList();
		callResult.putData("cate", findCateList);
		PageInfo pageInfo = new PageInfo<>(menus);
		callResult.setData(pageInfo);
		
		return callResult;
	}
	@RequestMapping("list")
	@ResponseBody
	public CallResult listMenus(Integer page,Integer limit) {
		CallResult callResult = new CallResult();
		if (page == null) {
			page =1;
		}
		if (limit == null) {
			limit =5;
		}
		PageHelper.startPage(page, limit);
		Integer row = (page-1) * limit;//getMenus(row,limit);
		List<Menu> menus =  menuService.getMenu();
		List<Category> findCateList = categoryService.findCateList();
		PageInfo<Menu> pageInfo = new PageInfo<>(menus);
		callResult.setData(pageInfo);
		
		callResult.putData("cate", findCateList);
		return callResult;
	}

	@RequestMapping("get/{mid}")
	@ResponseBody
	public CallResult getMenus(@PathVariable Long mid) {
		CallResult callResult = new CallResult();
		System.out.println("==============" + mid);
		Menu menu = menuService.get(mid);
		if (null == menu) {
			callResult.fail("没有该菜谱");
			return callResult;
		}
		List<Material> materials = materialService.findMaterialsByMid(mid);
		List<Step> steps = stepService.findStepsByMid(mid);

		callResult.setData(menu);
//		callResult.putData("menu", menu);
//		callResult.putData("material", materials);
//		callResult.putData("step", steps);
		 List<Category> findCateList = categoryService.findCateList();
		 callResult.putData("cate", findCateList);
		return callResult;
	}

	@RequestMapping("delete/{mid}")
	@ResponseBody
	public CallResult deleteMenus(@PathVariable Long mid) {
		CallResult callResult = new CallResult();
		
		menuService.deleteById(mid);

		return callResult;
	}

	@RequestMapping("list/{cid}")
	@ResponseBody
	public CallResult getMenusByCid(@PathVariable Long cid) {
		CallResult callResult = new CallResult();
		List<Menu> menus = menuService.getMenusByCid(cid);

		callResult.setData(menus);
		return callResult;
	}
}
