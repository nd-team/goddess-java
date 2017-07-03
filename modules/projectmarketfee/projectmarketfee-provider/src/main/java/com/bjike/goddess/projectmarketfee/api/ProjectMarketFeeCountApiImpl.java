package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.service.ProjectMarketFeeCountSer;
import com.bjike.goddess.projectmarketfee.to.GuidePermissionTO;
import com.bjike.goddess.projectmarketfee.to.ProjectMarketFeeCountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目前期的市场活动费汇总业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 01:50 ]
 * @Description: [ 项目前期的市场活动费汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectMarketFeeCountApiImpl")
public class ProjectMarketFeeCountApiImpl implements ProjectMarketFeeCountAPI {
    @Autowired
    private ProjectMarketFeeCountSer projectMarketFeeCountSer;

    @Override
    public ProjectMarketFeeCountBO save(ProjectMarketFeeCountTO to) throws SerException {
       return projectMarketFeeCountSer.save(to);
    }

    @Override
    public ProjectMarketFeeCountBO findByID(String id) throws SerException {
        return projectMarketFeeCountSer.findByID(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return projectMarketFeeCountSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectMarketFeeCountSer.guidePermission(guidePermissionTO);
    }
}