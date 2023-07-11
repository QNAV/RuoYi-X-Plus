package com.ruoyi.sms.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 短信模板统一对象
 * @Author Administrator
 * @Date 2023/7/11 11:02
 */
@Data
@NoArgsConstructor
@ToString
public class UnifySmsTemplatePageVo {

    //当前页码
    private Integer currentPage;

    //页数
    private Integer pageSize;

    //总页数
    private Long totalCount;

    //数据
   private List<UnifySmsTemplateVo> unifySmsTemplateVos;

}
