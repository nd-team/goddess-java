package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.bo.TemplateManageBO;
import com.bjike.goddess.recruit.dto.TemplateManageDTO;
import com.bjike.goddess.recruit.entity.TemplateManage;
import com.bjike.goddess.recruit.to.TemplateManageTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模板管理
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class TemplateManageSerImpl extends ServiceImpl<TemplateManage, TemplateManageDTO> implements TemplateManageSer {
    @Override
    public List<TemplateManageBO> list(TemplateManageTO templateManageTO) throws SerException {
        return null;
    }

    @Override
    public TemplateManageBO save(TemplateManageTO templateManageTO) throws SerException {
        return null;
    }

    @Override
    public void update(TemplateManageTO templateManageTO) throws SerException {

    }
}
