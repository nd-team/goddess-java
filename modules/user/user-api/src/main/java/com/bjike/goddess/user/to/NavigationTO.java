package com.bjike.goddess.user.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 首页导航
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-21 09:37 ]
 * @Description: [ 首页导航 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NavigationTO extends BaseTO {
    /**
     * 导航list
     */
    private List<HomeNavigationTO> tos;

    public List<HomeNavigationTO> getTos() {
        return tos;
    }

    public void setTos(List<HomeNavigationTO> tos) {
        this.tos = tos;
    }
}