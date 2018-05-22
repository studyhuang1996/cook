package cn.studyhuang.cook.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.dao.EvaluateMapper;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.EvaluateExample;
import cn.studyhuang.cook.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Resource
	private EvaluateMapper evaluateMapper;

	@Override
	public void saveOrUpdate(Evaluate evaluate) {
		
		if (null == evaluate.getEvaluateId()) {
			evaluateMapper.insert(evaluate);
		} else {
			evaluateMapper.updateByPrimaryKey(evaluate);
		}
		
	}

	@Override
	public List<Evaluate> getEvaluatesByCondition(EvaluateExample evaluateExample) {

		return evaluateMapper.selectByExample(evaluateExample);
	}

	@Override
	public Evaluate get(Long id) {

		return evaluateMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long id) {
		evaluateMapper.deleteByPrimaryKey(id);

	}

	@Override
	public List<Evaluate> getEvaluates(Long mid) {
		
		return evaluateMapper.getEvaluates(mid);
	}

	@Override
	public List<Evaluate> getReply(Long replyId) {
		
		return evaluateMapper.getReply(replyId);
	}
	

}
