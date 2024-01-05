package cn.yangwanhao.news.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/3 11:50
 */
public class DateUtil {

    /**
     * 获取某个时间过去n天的时间
     * @param date 计算基于的时间
     * @param past n
     * @return 时间
     */
    public static Date getPastDate(Date date, Integer past) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, calendar.get(6) - past);
        return calendar.getTime();
    }

}
