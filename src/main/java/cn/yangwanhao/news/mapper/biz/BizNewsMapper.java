package cn.yangwanhao.news.mapper.biz;

import java.util.List;

import cn.yangwanhao.news.model.News;

public interface BizNewsMapper {

    int insertList(List<News> newsList);

}