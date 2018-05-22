package cn.studyhuang.cook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.EvaluateExample;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.service.EvaluateService;
import cn.studyhuang.cook.service.MenuService;
import cn.studyhuang.cook.service.UserService;
/**
 * 评价控制层
 * @author huang
 *
 *2018年1月9日 下午10:44:22
 */
@Controller
@RequestMapping("evaluate")
public class EvaluateController {
	@Resource
	private EvaluateService evaluateService;
	@Resource
	private MenuService menuService;
	@Resource
	private UserService UserService;
	
	@RequestMapping("save")
	@ResponseBody
	public CallResult  save(Evaluate evaluate) {
		CallResult result = new CallResult();
		 if (null == evaluate) {
			result.fail("请填写保存数据");
			return result;
		}
		 evaluateService.saveOrUpdate(evaluate);
		return result;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public CallResult listEvaluates(Integer page,Integer limit) {
		CallResult result = new CallResult();
		if (page == null ) {
			page=1;
		}
		if (limit == null ) {
			limit=5;
		}
		PageHelper.startPage(page, limit);
		Integer row = (page-1) * limit;
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		EvaluateExample example =new EvaluateExample();
//		example.setPage(row);
//		example.setPageSize(limit);
		List<Evaluate> evaluates = evaluateService.getEvaluatesByCondition(example);
		for (Evaluate evaluate : evaluates) {
	       Map<String,Object> map = new HashMap<String,Object>();
	       Menu menu = menuService.get(evaluate.getMid());
	       User user = UserService.get(evaluate.getUserId());
	       map.put("mname", menu.getMname()+" ");
	       map.put("uname", user.getUname());
	       map.put("evaluateId", evaluate.getEvaluateId());
	       map.put("evaluateContent", evaluate.getEvaluateContent());
	       map.put("evaluteDate", evaluate.getEvaluteDate());
	       map.put("score", evaluate.getScore());
	       maps.add(map);
		}
		PageInfo pageInfo = new PageInfo(maps);
		//result.setData(pageInfo);
		result.setData(maps);
		return result;
	}

	@RequestMapping(value="get/{id}",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public CallResult getEvaluate(@PathVariable Long id) {
		CallResult result = new CallResult();
		Evaluate evaluates = evaluateService.get(id);
		result.setData(evaluates);
		return result;
	}
	
	@RequestMapping("delete/{id}")
	@ResponseBody
	public CallResult  delete(@PathVariable Long id) {
		CallResult result = new CallResult();
		 if (null == id) {
			result.fail("参数传递错误");
			return result;
		}
		evaluateService.delete(id); 
		return result;
	}
}
