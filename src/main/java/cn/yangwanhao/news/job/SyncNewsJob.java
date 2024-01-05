package cn.yangwanhao.news.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.yangwanhao.news.service.ISyncNewsService;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/14 19:38
 */
@Slf4j
@Component
public class SyncNewsJob {

    @Autowired
    private ISyncNewsService syncNewsServices;

    @Scheduled(cron = "${cn.yangwanhao.send_email.cron.syncNewsJob}")
    public synchronized void process(){
        try {
            syncNewsServices.syncNewsToDatabase();
        } catch (Exception e) {
            log.error("同步新闻发生异常,异常信息是:", e);
        }

    }

}
