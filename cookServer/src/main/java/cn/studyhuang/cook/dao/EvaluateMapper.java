package cn.studyhuang.cook.dao;

import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.EvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluateMapper {
    int countByExample(EvaluateExample example);

    int deleteByExample(EvaluateExample example);

    int deleteByPrimaryKey(Long evaluateId);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    List<Evaluate> selectByExample(EvaluateExample example);

    Evaluate selectByPrimaryKey(Long evaluateId);

    int updateByExampleSelective(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByExample(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
    
    List<Evaluate> listEvaluates();
    
    List<Evaluate> getEvaluates(Long mid);
    /**
     * 获取某条评论下的回复信息
     * @param replyId
     * @return
     */
    List<Evaluate> getReply(Long replyId);
}