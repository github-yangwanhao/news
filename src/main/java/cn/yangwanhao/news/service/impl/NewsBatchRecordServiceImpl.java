package cn.yangwanhao.news.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yangwanhao.news.enums.EnumBool;
import cn.yangwanhao.news.mapper.NewsBatchRecordMapper;
import cn.yangwanhao.news.model.NewsBatchRecord;
import cn.yangwanhao.news.model.NewsBatchRecordExample;
import cn.yangwanhao.news.service.INewsBatchRecordService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/11/15 17:28
 */
@Slf4j
@Service(value = "newsBatchRecordService")
public class NewsBatchRecordServiceImpl implements INewsBatchRecordService {

    @Autowired
    private NewsBatchRecordMapper newsBatchRecordMapper;

    @Override
    public List<String> queryHasNotMailedBatchId() {
        NewsBatchRecordExample example = new NewsBatchRecordExample();
        NewsBatchRecordExample.Criteria criteria = example.createCriteria();
        criteria.andHasSentEqualTo(EnumBool.NO.getCode());
        List<NewsBatchRecord> newsBatchRecords = newsBatchRecordMapper.selectByExample(example);
        return newsBatchRecords.stream()
            .map(NewsBatchRecord::getBatchId)
            .collect(Collectors.toList());
    }

    @Override
    public void updateMailedWithBatchIdAndChannelType(List<String> batchIdList) {

        NewsBatchRecordExample example = new NewsBatchRecordExample();
        NewsBatchRecordExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdIn(batchIdList);

        NewsBatchRecord record = new NewsBatchRecord();
        record.setHasSent(EnumBool.YES.getCode());

        newsBatchRecordMapper.updateByExampleSelective(record, example);
    }

    @Override
    public void initOneRecord(String batchId) {
        NewsBatchRecord record = new NewsBatchRecord();
        record.setBatchId(batchId);
        record.setHasSent(EnumBool.NO.getCode());
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        newsBatchRecordMapper.insertSelective(record);
    }

}
