package cn.yangwanhao.news.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author 杨万浩
 * @version V1.0
 * @since 2024/1/3 12:19
 */
@Getter
@AllArgsConstructor
public enum EnumBool {
    /** 描述 */
    YES("Y", "是"),
    NO("N", "否"),
    ;

    /** 状态码 */
    private String code;

    /** 状态描述 */
    private String description;

    /**
     * 根据编码查找枚举
     *
     * @param code 编码
     * @return {@link EnumBool } 实例
     **/
    public static EnumBool find(String code) {
        for (EnumBool instance : EnumBool.values()) {
            if (instance.getCode()
                .equals(code)) {
                return instance;
            }
        }
        return null;
    }
}

