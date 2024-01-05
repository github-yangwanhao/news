package cn.yangwanhao.news.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yangwanhao.news.NewsApplication;
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
public class SyncNewsTest {

    @Autowired
    private ISyncNewsService syncNewsService;

    @Test
    public void testSyncNews() {
        syncNewsService.syncNewsToDatabase();
    }
}
