package cn.yangwanhao.news.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/3 12:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto implements Serializable {

    /** 标题 */
    private String title;
    /** 简介 */
    private String detail;
    /** 链接 */
    private String url;
    /** 排名 */
    private Integer rank;
    /** 热度值 */
    private Integer hotScore;
    /** 渠道编号 */
    private String channel;

}
