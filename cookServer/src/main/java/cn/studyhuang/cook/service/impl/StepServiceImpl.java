package cn.studyhuang.cook.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.StepMapper;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.pojo.StepExample;
import cn.studyhuang.cook.service.StepService;

@Service
public class StepServiceImpl implements StepService{

	@Resource
	private StepMapper stepMapper;
	@Override
	public CallResult saveOrUpdate(Step step) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Step get(Long id) {
		// TODO Auto-generated method stub
		return stepMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		stepMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Step> listSteps(Integer page,Integer limit) {
		StepExample stepExample = new StepExample();
		stepExample.setPage(page);
		stepExample.setPageSize(limit);
		return stepMapper.selectByExampleWithBLOBs(stepExample);
	}

	@Override
	public List<Step> findStepsByMid(Long mid) {
	
		StepExample stepExample = new StepExample();
		stepExample.createCriteria().andMenuIdEqualTo(mid);
		return stepMapper.selectByExampleWithBLOBs(stepExample);
	}

}
