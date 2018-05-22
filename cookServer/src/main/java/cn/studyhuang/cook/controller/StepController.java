package cn.studyhuang.cook.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.service.StepService;
/**
 * 步骤 做法
 * @author huang
 *
 *2018年1月10日 上午12:24:46
 */

@Controller
@RequestMapping("step")
public class StepController {

	@Resource
	private StepService stepService;
	@RequestMapping("save")
	@ResponseBody
	public CallResult save(Step step) {
		CallResult callResult =new CallResult();
		
		return callResult;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public CallResult listSteps(Integer page,Integer limit) {
		CallResult callResult =new CallResult();
		if (page == null) {
			page =1;
		}
		if (limit == null) {
			limit =5;
		}
		//PageHelper.startPage(page,limit);
		Integer row = (page-1) * limit;
		List<Step> steps = stepService.listSteps(null,null);
		PageInfo pageInfo = new PageInfo<>(steps);
		callResult.setData(pageInfo);
		return callResult;
	}
	
	@RequestMapping("get")
	@ResponseBody
	public CallResult getSteps() {
		CallResult callResult =new CallResult();
		
		return callResult;
	}
	
	@RequestMapping("getStepsByMid/{mid}")
	@ResponseBody
	public CallResult getStepsByMid(@PathVariable Long mid) {
      CallResult callResult =new CallResult();
		List<Step> stepsByMid = stepService.findStepsByMid(mid);
		if(CollectionUtils.isEmpty(stepsByMid)) {
			
			callResult.fail("该菜谱还未上传步骤");
			return callResult;
		}
		callResult.setData(stepsByMid);
		return callResult;
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	@ResponseBody
	public CallResult deleteById(@PathVariable Long id) {
		CallResult callResult = new CallResult();
		stepService.delete(id);
		callResult.setMsg("删除成功");
		return callResult;
	}
	

}
