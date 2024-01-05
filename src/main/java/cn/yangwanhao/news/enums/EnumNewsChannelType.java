package cn.yangwanhao.news.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/11/15 17:12
 */
@Getter
@AllArgsConstructor
public enum EnumNewsChannelType {
    /** 描述 */
    BAI_DU("001", "百度热搜"),
    ;

    /** 状态码 */
    private String code;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumNewsChannelType } 实例
     **/
    public static EnumNewsChannelType find(String code) {
        for (EnumNewsChannelType instance : EnumNewsChannelType.values()) {
            if (instance.getCode()
                .equals(code)) {
                return instance;
            }
        }
        return null;
    }
}

