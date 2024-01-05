package cn.yangwanhao.news.service;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/4 11:46
 */
public interface ICustomerMailInfoService {
    /**
     * 获取邮箱数组
     * @return 邮箱数组
     */
    String[] queryMailAddress();
}
