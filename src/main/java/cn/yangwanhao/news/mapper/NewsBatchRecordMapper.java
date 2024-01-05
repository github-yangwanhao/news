package cn.yangwanhao.news.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.yangwanhao.news.model.NewsBatchRecord;
import cn.yangwanhao.news.model.NewsBatchRecordExample;

public interface NewsBatchRecordMapper {
    int countByExample(NewsBatchRecordExample example);

    int deleteByExample(NewsBatchRecordExample example);

    int deleteByPrimaryKey(String batchId);

    int insert(NewsBatchRecord record);

    int insertSelective(NewsBatchRecord record);

    List<NewsBatchRecord> selectByExample(NewsBatchRecordExample example);

    NewsBatchRecord selectByPrimaryKey(String batchId);

    int updateByExampleSelective(@Param("record") NewsBatchRecord record, @Param("example") NewsBatchRecordExample example);

    int updateByExample(@Param("record") NewsBatchRecord record, @Param("example") NewsBatchRecordExample example);

    int updateByPrimaryKeySelective(NewsBatchRecord record);

    int updateByPrimaryKey(NewsBatchRecord record);
}