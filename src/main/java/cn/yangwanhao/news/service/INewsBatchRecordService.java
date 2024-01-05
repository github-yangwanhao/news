package cn.yangwanhao.news.service;

import java.util.List;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/11/15 17:28
 */
public interface INewsBatchRecordService {

    /**
     * 根据渠道类型查询没有发送邮件的batchId
     * @return batchIdList
     */
    List<String> queryHasNotMailedBatchId();

    /**
     * 根据batchId和channelType更新状态为已发送邮件
     * @param batchIdList batchIdList
     */
    void updateMailedWithBatchIdAndChannelType(List<String> batchIdList);

    /**
     * 初始化一条数据
     * @param batchId batchId
     * @return
     */
    void initOneRecord(String batchId);


}
