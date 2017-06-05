package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.type.ProjectCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 多项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectSingleUISerImpl extends ServiceImpl<MultipleProjectSingleUI, MultipleProjectSingleUIDTO> implements MultipleProjectSingleUISer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块权限
        Boolean permissionLevel = cusPermissionSer.busCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectSingleUIBO> list(MultipleProjectSingleUIDTO dto) throws SerException {
        checkPermission();
        List<MultipleProjectSingleUI> list = super.findByPage(dto);
        List<MultipleProjectSingleUIBO> listBO = BeanTransform.copyProperties(list, MultipleProjectSingleUIBO.class);
        return listBO;
    }

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目单个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MultipleProjectSingleUIBO save(MultipleProjectSingleUITO to) throws SerException {
        checkPermission();
        MultipleProjectSingleUI entity = BeanTransform.copyProperties(to, MultipleProjectSingleUI.class, true);
        entity.setProjectCategory(ProjectCategory.MULTIPLE_SINGLE);
        entity = super.save(entity);
        MultipleProjectSingleUIBO bo = BeanTransform.copyProperties(entity, MultipleProjectSingleUIBO.class);
        return bo;
    }

    /**
     * 更新多项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MultipleProjectSingleUITO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MultipleProjectSingleUI model = super.findById(to.getId());
            if (model != null) {
                updateMultipleProjectSingleUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新多项目单个界面
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMultipleProjectSingleUI(MultipleProjectSingleUITO to, MultipleProjectSingleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }
}