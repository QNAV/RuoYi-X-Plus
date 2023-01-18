package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户和岗位关联 sys_user_post
 *
 * @author ruoyi
 * @author Lion Li
 */

@Data
@TableName("sys_user_post")
@Schema(description = "用户和岗位关联")
public class SysUserPost {

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 岗位ID
     */
    @Schema(description = "岗位ID")
    private Long postId;

}
