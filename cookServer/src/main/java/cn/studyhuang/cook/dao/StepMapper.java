package cn.studyhuang.cook.dao;

import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.pojo.StepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StepMapper {
    int countByExample(StepExample example);

    int deleteByExample(StepExample example);

    int deleteByPrimaryKey(Long sid);

    int insert(Step record);

    int insertSelective(Step record);

    List<Step> selectByExampleWithBLOBs(StepExample example);

    List<Step> selectByExample(StepExample example);

    Step selectByPrimaryKey(Long sid);

    int updateByExampleSelective(@Param("record") Step record, @Param("example") StepExample example);

    int updateByExampleWithBLOBs(@Param("record") Step record, @Param("example") StepExample example);

    int updateByExample(@Param("record") Step record, @Param("example") StepExample example);

    int updateByPrimaryKeySelective(Step record);

    int updateByPrimaryKeyWithBLOBs(Step record);

    int updateByPrimaryKey(Step record);
}