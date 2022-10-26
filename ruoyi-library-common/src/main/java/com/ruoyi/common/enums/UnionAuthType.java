package com.ruoyi.common.enums;

import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 关联账号类型
 */
@Getter
@AllArgsConstructor
public enum UnionAuthType {
    /**
     * 微信小程序
     */
    WEIXIN_MINIAPP("weixin_miniapp");

    private final String unionAuthType;

    public static UnionAuthType getUnionAuthType(String str) {
        for (UnionAuthType value : values()) {
            if (StringUtils.contains(str, value.getUnionAuthType())) {
                return value;
            }
        }
        throw new RuntimeException("'UnionAuthType' not found By " + str);
    }
}
