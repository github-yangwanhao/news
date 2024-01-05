package cn.yangwanhao.news.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.yangwanhao.news.enums.EnumBool;
import cn.yangwanhao.news.mapper.CustomerMailInfoMapper;
import cn.yangwanhao.news.model.CustomerMailInfo;
import cn.yangwanhao.news.model.CustomerMailInfoExample;
import cn.yangwanhao.news.service.ICustomerMailInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/4 11:48
 */
@Slf4j
@Service(value = "customerMailInfoService")
public class CustomerMailInfoServiceImpl implements ICustomerMailInfoService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private CustomerMailInfoMapper customerMailInfoMapper;

    @Override
    public String[] queryMailAddress() {
        CustomerMailInfoExample example = new CustomerMailInfoExample();
        CustomerMailInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEnabledEqualTo(EnumBool.YES.getCode());
        criteria.andExpireDateGreaterThanOrEqualTo(Integer.valueOf(DATE_FORMAT.format(new Date())));
        List<CustomerMailInfo> customerMailInfos = customerMailInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(customerMailInfos)) {
            return null;
        }
        return customerMailInfos.stream()
            .distinct()
            .map(CustomerMailInfo::getEmail)
            .toArray(String[]::new);
    }
}
