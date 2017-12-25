package com.bjike.goddess.user.dto;


import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户数据传输
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserDTO extends BaseDTO {

    public interface NAME {
    }

    /**
     * 用户名
     */
    @NotBlank(groups = {UserDTO.NAME.class},message = "用户名不能为空")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
