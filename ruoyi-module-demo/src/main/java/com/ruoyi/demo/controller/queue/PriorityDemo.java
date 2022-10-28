package com.ruoyi.demo.controller.queue;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类 注意不允许使用内部类 否则会找不到类
 * !!!不推荐模块内写控制器
 * @author weibocy
 */
@Data
@NoArgsConstructor
public class PriorityDemo {
    private String name;
    private Integer orderNum;
}
