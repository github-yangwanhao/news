package cn.yangwanhao.news.job;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.yangwanhao.news.service.ICustomerMailInfoService;
import cn.yangwanhao.news.service.INewsBatchRecordService;
import cn.yangwanhao.news.service.IQueryNewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/15 14:45
 */
@Slf4j
@Component
public class SendNewsEmailJob {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ICustomerMailInfoService customerMailInfoService;
    @Autowired
    private IQueryNewsService queryNewsService;
    @Autowired
    private INewsBatchRecordService newsBatchRecordService;

    /**
     * 周一到周五 - 9:05,14:05,18:05 执行发送邮件的任务
     * @param
     * @return
     */
    @Scheduled(cron = "${cn.yangwanhao.send_email.cron.sendNewsEmailJob}")
    public synchronized void process() {
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
        message.setBcc(emails);
        message.setSentDate(new Date());
        message.setText(mailText);
        javaMailSender.send(message);
        // 更新batchId为已发送
        newsBatchRecordService.updateMailedWithBatchIdAndChannelType(batchIdList);
    }


}
