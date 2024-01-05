package cn.yangwanhao.news.service;

import java.util.List;

import cn.yangwanhao.news.dto.NewsDto;
import cn.yangwanhao.news.enums.EnumNewsChannelType;

/**
 * 同步新闻服务
 *
 * @author 杨万浩
 * @version V1.0
 * @since 2021/11/13 14:11
 */
public interface ILoadNewsService<T extends NewsDto> {

    /**
     * 从不同的网站同步新闻头条
     * @return 当前时间点同步到的新闻头条
     */
    List<T> loadNews();

    /**
     * 定义新闻的渠道枚举
     * @return 渠道枚举
     */
    EnumNewsChannelType getChannelType();

}
