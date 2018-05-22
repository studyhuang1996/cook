package cn.studyhuang.cook.service;
/**
 * 评价服务
 * @author huang
 *
 *2018年1月9日 下午10:48:27
 */


import java.util.List;

import cn.studyhuang.cook.api.CallResult;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.EvaluateExample;

public interface EvaluateService {
	
	/**
	 * 发布评价
	 */
    void saveOrUpdate(Evaluate evaluate);

    /**
     * 获取所有评价
     * @return
     */
    List<Evaluate> getEvaluatesByCondition(EvaluateExample evaluateExample);
    /**
     * 获取某条评价
     * @param id
     * @return
     */
    Evaluate get(Long id);
    
    void delete(Long id);
    
    /**
     * 查询该菜谱的所有评价列表
     * @param mid
     * @return
     */
    List<Evaluate> getEvaluates(Long mid);
    
    /**
     * 获取某条评论下的回复信息
     * @param replyId
     * @return
     */
    List<Evaluate> getReply(Long replyId);
}
