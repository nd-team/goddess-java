package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightalAdjustsTO extends BaseTO {


    /**
     * 比例调整数据集合
     */
    private List<WeightalAdjustTO> weightalAdjustTOs;

    public List<WeightalAdjustTO> getWeightalAdjustTOs() {
        return weightalAdjustTOs;
    }

    public void setWeightalAdjustTOs(List<WeightalAdjustTO> weightalAdjustTOs) {
        this.weightalAdjustTOs = weightalAdjustTOs;
    }

}