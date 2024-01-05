package cn.yangwanhao.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import cn.yangwanhao.news.dto.NewsDto;
import cn.yangwanhao.news.enums.EnumBool;
import cn.yangwanhao.news.enums.EnumNewsChannelType;
import cn.yangwanhao.news.mapper.NewsChannelMapper;
import cn.yangwanhao.news.mapper.NewsMapper;
import cn.yangwanhao.news.mapper.biz.BizNewsMapper;
import cn.yangwanhao.news.model.News;
import cn.yangwanhao.news.model.NewsChannel;
import cn.yangwanhao.news.model.NewsChannelExample;
import cn.yangwanhao.news.model.NewsExample;
import cn.yangwanhao.news.service.ILoadNewsService;
import cn.yangwanhao.news.service.INewsBatchRecordService;
import cn.yangwanhao.news.service.ISyncNewsService;
import cn.yangwanhao.news.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/11/13 14:47
 */
@Slf4j
@Service(value = "syncNewsService")
public class SyncNewsServiceImpl implements ISyncNewsService {

    @Autowired
    private List<ILoadNewsService<?>> loadNewsServiceList;
    @Autowired
    private INewsBatchRecordService newsBatchRecordService;

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private BizNewsMapper bizNewsMapper;
    @Autowired
    private NewsChannelMapper newsChannelMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void syncNewsToDatabase() {
        // 查询数据库配置开启的渠道
        List<NewsChannel> newsChannels = newsChannelMapper.selectByExample(new NewsChannelExample());
        newsChannels = newsChannels.stream()
            .filter(s -> EnumBool.YES.getCode().equals(s.getEnabled()))
            .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(newsChannels)) {
            log.warn("数据库未配置开启的新闻渠道,请检查配置.");
            return;
        }
        // 查询过去7天的新闻头条 基本上不会有新闻头条活过7天
        NewsExample example = new NewsExample();
        NewsExample.Criteria criteria = example.createCriteria();
        criteria.andCreateTimeGreaterThanOrEqualTo(DateUtil.getPastDate(new Date(), 7));
        List<News> baiduTopNewsList = newsMapper.selectByExample(example);
        // 只获取title
        List<String> titleList = baiduTopNewsList.stream()
            .map(News::getTitle)
            .distinct()
            .collect(Collectors.toList());
        // 获取batchId
        String batchId = getBatchId();
        List<News> insertList = new ArrayList<>();
        Map<EnumNewsChannelType, ILoadNewsService> loadNewsServiceMap = loadNewsServiceList.stream()
            .collect(Collectors.toMap(ILoadNewsService::getChannelType, Function.identity()));
        for (NewsChannel newsChannel : newsChannels) {
            ILoadNewsService<NewsDto> newsDtoLoadNewsService = loadNewsServiceMap.get(EnumNewsChannelType.find(newsChannel.getChannelCode()));
            List<NewsDto> newsDtoList = newsDtoLoadNewsService.loadNews();
            // 向数据库同步
            for (int i = 0; i < newsDtoList.size(); i++) {
                NewsDto newsDto = newsDtoList.get(i);
                if (titleList.contains(newsDto.getTitle())) {
                    log.info("title[{}]已经在数据库中存在,不再同步", newsDto.getTitle());
                    continue;
                }
                News news = buildNews(newsDto, batchId, i);
                insertList.add(news);
            }
        }

        if (!CollectionUtils.isEmpty(insertList)) {
            transactionTemplate.execute(e -> {
                newsBatchRecordService.initOneRecord(batchId);
                bizNewsMapper.insertList(insertList);
                return true;
            });

        }
    }

    private News buildNews(NewsDto newsDto, String batchId, int index) {
        News news = new News();
        String id = batchId + "-" + newsDto.getChannel() + "-" + String.format("%03d", index + 1);
        news.setId(id);
        news.setTitle(newsDto.getTitle());
        news.setDetail(newsDto.getDetail());
        news.setUrl(newsDto.getUrl());
        news.setRank(newsDto.getRank());
        news.setHotScore(newsDto.getHotScore());
        news.setBatchId(batchId);
        news.setChannel(newsDto.getChannel());
        Date now = new Date();
        news.setCreateTime(now);
        news.setUpdateTime(now);
        return news;
    }

    private String getBatchId() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }

}
