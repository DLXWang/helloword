package com.test.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: 对冲过滤器配置
 * @author: wangxinxing
 * @date: 2023/5/30
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class HedgeUserFilter {
    @Id
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 被选择对冲的账户ID
     */
    private Long selectedAccountId;


    private String role;

    private List<Long> symbolIncludePlateIds;

    private List<Long> symbolExcludePlateIds;

    /**
     * 对冲交易对自选包含列表
     */
    private List<Long> symbolInclude;
    /**
     * 对冲交易对自选不包含列表
     */
    private List<Long> symbolExclude;

    /**
     * 是否排除跟单
     */
    private Boolean excludeFollow;
    /**
     * 是否自动
     */
    private Boolean autoEnabled;
    /**
     * 是否生效
     */
    private Boolean enabled;

    /**
     * 对冲比例
     */
    private BigDecimal percentage;

    private String creatorId;
    private String updaterId;
    private Long createdTime;

    private Long updatedTime;

}
