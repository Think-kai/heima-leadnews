package com.heima.model.user.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Project: heima-leadnews </p>
 * <p>Description: </p>
 * <p>Copyright (c) 2024 Karrytech (Shanghai/Beijing) Co., Ltd.</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhangkai@karrytech.com">Zhang Kai</a>
 */
@Data
public class LoginDto {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

}