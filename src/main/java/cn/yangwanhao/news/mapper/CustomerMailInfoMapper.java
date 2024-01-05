package cn.yangwanhao.news.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.yangwanhao.news.model.CustomerMailInfo;
import cn.yangwanhao.news.model.CustomerMailInfoExample;

public interface CustomerMailInfoMapper {
    int countByExample(CustomerMailInfoExample example);

    int deleteByExample(CustomerMailInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(CustomerMailInfo record);

    int insertSelective(CustomerMailInfo record);

    List<CustomerMailInfo> selectByExample(CustomerMailInfoExample example);

    CustomerMailInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CustomerMailInfo record, @Param("example") CustomerMailInfoExample example);

    int updateByExample(@Param("record") CustomerMailInfo record, @Param("example") CustomerMailInfoExample example);

    int updateByPrimaryKeySelective(CustomerMailInfo record);

    int updateByPrimaryKey(CustomerMailInfo record);
}