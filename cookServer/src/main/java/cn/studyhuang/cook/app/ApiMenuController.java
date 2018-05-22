package cn.studyhuang.cook.app;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.studyhuang.cook.api.BaseResult;
import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.EvaluateMapper;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.service.CategoryService;
import cn.studyhuang.cook.service.EvaluateService;
import cn.studyhuang.cook.service.MaterialService;
import cn.studyhuang.cook.service.MenuService;
import cn.studyhuang.cook.service.StepService;
/**
 * android端的接口
 * @author huang
 *
 */
@RequestMapping("api")
@Controller
public class ApiMenuController {
	
	@Resource
	private MenuService menuService;
	@Resource
	private CategoryService categoryService;

	@Resource
	private StepService stepService;

	@Resource
	private MaterialService materialService;
	
	@Resource
	private EvaluateService evaluateService;
	
	
	
	@RequestMapping("category/list/")
	@ResponseBody
	public CallResult listCategories() {
		CallResult result = new CallResult();
	    List<Category> cateLists = categoryService.findCateList();
	    result.setData(cateLists);
	    return result;
	}
	
	@RequestMapping("menu/list/")
	@ResponseBody
	public CallResult listMenus(Integer page,Integer limit) {
		CallResult callResult = new CallResult();
		List<Menu> menus = menuService.getMenu();
		List<Category> findCateList = categoryService.findCateList();
		callResult.setData(menus);
		callResult.putData("cate", findCateList);
		return callResult;
	}
	
	/**
	 * 获取某个固定的菜谱的评价
	 * @param mid
	 * @return
	 */
	@RequestMapping("commond/{mid}/")
	@ResponseBody
	public CallResult gEvaluates(@PathVariable Long mid) {
		CallResult result = new CallResult();
		if (mid==null) {
			 result.fail("参数传递错误");
			 return result;
		}
		List<Evaluate> evaluates = evaluateService.getEvaluates(mid);
		if (evaluates == null) {
			result.setMsg("没有评价");
			return result;
		}
		result.setData(evaluates);
		return result;
	}

	/**
	 * 发表评论
	 * @param evaluate
	 * @return
	 */
	@RequestMapping(value="evaluate/save",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public BaseResult  save(String evaluateContent,Long mid,
			Long  userId,Float score,Long replyId ) {
		BaseResult result =new BaseResult();
		 Evaluate evaluate = new Evaluate();
		 evaluate.setEvaluateContent(evaluateContent);
		 evaluate.setEvaluteDate(new Date());
		 evaluate.setMid(mid);
		 evaluate.setScore(score);
		 evaluate.setUserId(userId);
		 evaluate.setReplyId(replyId);
		 evaluateService.saveOrUpdate(evaluate);
		 
		return result;
	}
	
	/**
	 * 添加菜谱，涉及android中的图片上传
	 */
	@RequestMapping(value="menu/add/",method=RequestMethod.POST)
	@ResponseBody
	public CallResult AddMenu() {
		CallResult result = new CallResult();
		
		return result;
	}
	
	/**
	 * huqo某条评论下具体的回复      
	 * @return
	 */
	@RequestMapping("reply/{replyId}/")
	@ResponseBody
	public CallResult getReply(@PathVariable Long replyId) {
		CallResult result = new CallResult();
		if(replyId == null) {
		  result.fail("参数传递");
		  return result;
		}
		List<Evaluate> evaluates = 
				evaluateService.getReply(replyId);
		if (CollectionUtils.isEmpty(evaluates)) {
			result.setMsg("暂时没有评论");
			return result;
		}
		result.setData(evaluates);
		return result;
	}
}
