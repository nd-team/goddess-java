package com.bjike.goddess.balancecard.to;

import com.bjike.goddess.balancecard.enums.CusPermissionType;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by haikuang on 17-7-17.
 */
public class DendrogramYearSetTO extends BaseTO {
    public interface TestAdd {
    }
    public interface TestSer {
    }

    /**
     * 年度指标名称
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
