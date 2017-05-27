package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 单个项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class SingleProjectSingleUISerImpl extends ServiceImpl<SingleProjectSingleUI, SingleProjectSingleUIDTO> implements SingleProjectSingleUISer {

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
        if ( !permissionLevel) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken( userToken );

    }

    /**
     * 分页查询单个项目单个界面
     *
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectSingleUIBO> list(SingleProjectSingleUIDTO dto) throws SerException {
        checkPermission();
        List<SingleProjectSingleUI> list = super.findByPage(dto);
        List<SingleProjectSingleUIBO> listBO = BeanTransform.copyProperties(list, SingleProjectSingleUIBO.class);
        return listBO;
    }

    /**
     * 保存单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public SingleProjectSingleUIBO save(SingleProjectSingleUITO to) throws SerException {
        checkPermission();
        SingleProjectSingleUI entity = BeanTransform.copyProperties(to, SingleProjectSingleUI.class, true);
        entity = super.save(entity);
        SingleProjectSingleUIBO bo = BeanTransform.copyProperties(entity, SingleProjectSingleUIBO.class);
        return bo;
    }

    /**
     * 更新单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(SingleProjectSingleUITO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            SingleProjectSingleUI model = super.findById(to.getId());
            if (model != null) {
                updateSingleProjectSingleUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新单个项目单个界面
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateSingleProjectSingleUI(SingleProjectSingleUITO to, SingleProjectSingleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }
}