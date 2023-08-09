package com.test.mongo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HedgeSpotAccount {
    @Id
    @NotNull(message = "id 不能为空")
    private Long id;
    @NotBlank(message = "name 不能为空")
    @ApiModelProperty("账户名")
    private String name;
    @ApiModelProperty("账户说明")
    private String comment;

    /**
     * 是否生效
     */
    @NotNull(message = "开关 不能为空")
    @ApiModelProperty("账户生效")
    private Boolean enabled;

    private String creatorId;
    private String updaterId;
    private Long createdTime;
    private Long updatedTime;

    // remove 标志，不持久化，用于 pub remove 消息，默认是 false
    @Transient
    private boolean removed;

}
