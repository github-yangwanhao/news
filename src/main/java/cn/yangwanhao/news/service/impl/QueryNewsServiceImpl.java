package cn.yangwanhao.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.yangwanhao.news.enums.EnumNewsChannelType;
import cn.yangwanhao.news.mapper.NewsMapper;
import cn.yangwanhao.news.model.News;
import cn.yangwanhao.news.model.NewsExample;
import cn.yangwanhao.news.service.IQueryNewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/4 12:18
 */
@Slf4j
@Service(value = "queryNewsService")
public class QueryNewsServiceImpl implements IQueryNewsService {

    private static final String SEPARATE_LINE = "---------------------------------------------%s---------------------------------------------";

    private String getSeparateLine(EnumNewsChannelType newsChannelType) {
        return String.format(SEPARATE_LINE, newsChannelType.getDescription());
    }

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public String getNewsDetailStr(List<String> batchIdList) {
        if (CollectionUtils.isEmpty(batchIdList)) {
            return null;
        }
        NewsExample newsExample = new NewsExample();
        NewsExample.Criteria criteria = newsExample.createCriteria();
        criteria.andBatchIdIn(batchIdList);
        newsExample.setOrderByClause("CHANNEL ASC, CREATE_TIME ASC");
        List<News> newsList = newsMapper.selectByExample(newsExample);
        return buildMailText(newsList);
    }

    private String buildMailText(List<News> newsList) {
        if (CollectionUtils.isEmpty(newsList)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (News news : newsList) {
            String separateLine = getSeparateLine(Objects.requireNonNull(EnumNewsChannelType.find(news.getChannel())));
            sb.append(separateLine)
                .append("\r\n");
            sb.append("热搜榜排行第")
                .append(news.getRank())
                .append("名")
                .append("\r\n")
                .append("标题 : ")
                .append(news.getTitle())
                .append("\r\n")
                .append("描述 : ")
                .append(news.getDetail())
                .append("\r\n")
                .append("链接 : ")
                .append(news.getUrl())
                .append("\r\n")
                .append("热搜指数 : ")
                .append(news.getHotScore())
                .append("\r\n")
                .append("同步时间 : ")
                .append(simpleDateFormat.format(news.getCreateTime()))
                .append("\r\n");
        }
        return sb.toString();
    }
}
