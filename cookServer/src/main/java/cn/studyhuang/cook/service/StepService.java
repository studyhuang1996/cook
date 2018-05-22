package cn.studyhuang.cook.service;

import java.util.List;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.pojo.Step;

/**
 * 做法步骤
 * @author huang
 *
 *2018年1月9日 下午11:20:30
 */
public interface StepService {
	
   CallResult saveOrUpdate(Step step);
	
	Step get(Long id);
	
	void delete(Long id);
	
	List<Step> listSteps(Integer page,Integer limit);
	
   /**
    * 通过菜谱id获取菜谱步骤
    * @param mid
    * @return
    */
	List<Step> findStepsByMid(Long mid);
}
