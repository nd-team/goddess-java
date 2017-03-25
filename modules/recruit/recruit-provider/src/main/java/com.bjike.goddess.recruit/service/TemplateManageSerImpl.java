package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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

    /**
     * 分页查询模板管理
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<TemplateManageBO> list(TemplateManageDTO dto) throws SerException {
        List<TemplateManage> templateManageList = super.findByPage(dto);
        List<TemplateManageBO> templateManageBOList = BeanTransform.copyProperties(templateManageList, TemplateManageBO.class);
        return templateManageBOList;
    }

    /**
     * 保存模板管理
     *
     * @param templateManageTO
     * @return
     * @throws SerException
     */
    @Override
    public TemplateManageBO save(TemplateManageTO templateManageTO) throws SerException {
        TemplateManage templateManage = BeanTransform.copyProperties(templateManageTO, TemplateManage.class, true);
        templateManage = super.save(templateManage);
        TemplateManageBO templateManageBO = BeanTransform.copyProperties(templateManage, TemplateManageBO.class);
        return templateManageBO;
    }

    /**
     * 更新模板管理
     *
     * @param templateManageTO
     * @throws SerException
     */
    @Override
    public void update(TemplateManageTO templateManageTO) throws SerException {
        TemplateManage templateManage = BeanTransform.copyProperties(templateManageTO, TemplateManage.class, true);
        super.update(templateManage);
    }
}
