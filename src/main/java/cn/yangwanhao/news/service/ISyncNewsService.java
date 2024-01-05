package cn.yangwanhao.news.service;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/11/13 14:44
 */
public interface ISyncNewsService {
    /**
     * 同步新闻到数据库
     */
    void syncNewsToDatabase();

}
