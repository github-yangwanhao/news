package cn.yangwanhao.news.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yangwanhao.news.NewsApplication;
import cn.yangwanhao.news.service.ICustomerMailInfoService;
import cn.yangwanhao.news.service.INewsBatchRecordService;
import cn.yangwanhao.news.service.IQueryNewsService;
import cn.yangwanhao.news.service.ISyncNewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/3 18:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NewsApplication.class})
@Slf4j
public class QueryNewsTest {

    @Autowired
    private IQueryNewsService queryNewsService;
    @Autowired
    private INewsBatchRecordService newsBatchRecordService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ICustomerMailInfoService customerMailInfoService;

    @Test
    public void testQueryNews() {
        List<String> batchIdList = newsBatchRecordService.queryHasNotMailedBatchId();
        String newsDetailStr = queryNewsService.getNewsDetailStr(batchIdList);
        System.out.println(newsDetailStr);
        System.out.println();
    }

    @Test
    public void testSendMail() {
        // 查询出所有的邮箱
        String[] emails = customerMailInfoService.queryMailAddress();
        if (emails == null || emails.length == 0) {
            log.warn("未配置要发送的邮箱地址");
            return;
        }
        log.info("本次要发送的email有{}", Arrays.asList(emails));
        // 查询出要发送的信息
        List<String> batchIdList = newsBatchRecordService.queryHasNotMailedBatchId();
        String mailText = queryNewsService.getNewsDetailStr(batchIdList);
        if (StringUtils.isBlank(mailText)) {
            log.info("本次没有更新的新闻内容");
            return;
        }
        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("新闻热搜磅");
        message.setFrom("yangwanhao126@126.com");
        message.setTo(emails);
        message.setSentDate(new Date());
        message.setText(mailText);
        javaMailSender.send(message);
        // 更新batchId为已发送
        newsBatchRecordService.updateMailedWithBatchIdAndChannelType(batchIdList);
    }
}
