package cn.yangwanhao.news.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.yangwanhao.news.model.NewsChannel;
import cn.yangwanhao.news.model.NewsChannelExample;

public interface NewsChannelMapper {
    int countByExample(NewsChannelExample example);

    int deleteByExample(NewsChannelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewsChannel record);

    int insertSelective(NewsChannel record);

    List<NewsChannel> selectByExample(NewsChannelExample example);

    NewsChannel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewsChannel record, @Param("example") NewsChannelExample example);

    int updateByExample(@Param("record") NewsChannel record, @Param("example") NewsChannelExample example);

    int updateByPrimaryKeySelective(NewsChannel record);

    int updateByPrimaryKey(NewsChannel record);
}