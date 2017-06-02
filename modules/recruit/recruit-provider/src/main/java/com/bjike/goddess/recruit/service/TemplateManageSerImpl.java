package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.TemplateManageBO;
import com.bjike.goddess.recruit.dto.TemplateManageDTO;
import com.bjike.goddess.recruit.entity.TemplateManage;
import com.bjike.goddess.recruit.to.TemplateManageTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 模板管理
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
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
        List<TemplateManage> list = super.findByPage(dto);
        List<TemplateManageBO> listBO = BeanTransform.copyProperties(list, TemplateManageBO.class);
        return listBO;
    }

    /**
     * 保存模板管理
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public TemplateManageBO save(TemplateManageTO to) throws SerException {
        TemplateManage model = BeanTransform.copyProperties(to, TemplateManage.class, true);
        model = super.save(model);
        TemplateManageBO bo = BeanTransform.copyProperties(model, TemplateManageBO.class);
        return bo;
    }

    /**
     * 更新模板管理
     *
     * @param to 模板管理to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(TemplateManageTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            TemplateManage model = super.findById(to.getId());
            if (model != null) {
                updateTemplateManage(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新模板管理
     *
     * @param to    模板管理to
     * @param model 模板管理实体
     */
    private void updateTemplateManage(TemplateManageTO to, TemplateManage model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除模板管理
     *
     * @param id 模板管理唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
