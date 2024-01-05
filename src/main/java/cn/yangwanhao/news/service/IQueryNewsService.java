package cn.yangwanhao.news.service;

import java.util.List;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/4 12:17
 */
public interface IQueryNewsService {
    /**
     * 获取新闻列表的字符串
     * @param batchIdList 批次号集合
     * @return str
     */
    String getNewsDetailStr(List<String> batchIdList);
}
