package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户信息业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketactivitymanageSerCache")
@Service
public class CustomerInfoSerImpl extends ServiceImpl<CustomerInfo, CustomerInfoDTO> implements CustomerInfoSer {

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
     * 分页查询客户信息
     *
     * @param dto 客户信息dto
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    public List<CustomerInfoBO> list(CustomerInfoDTO dto) throws SerException {
        List<CustomerInfo> list = super.findByPage(dto);
        List<CustomerInfoBO> boList = BeanTransform.copyProperties(list, CustomerInfoBO.class);
        return boList;
    }

    /**
     * 保存客户信息
     *
     * @param to 客户信息to
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public CustomerInfoBO save(CustomerInfoTO to) throws SerException {
//        checkPermission();
        CustomerInfo entity = BeanTransform.copyProperties(to, CustomerInfo.class, true);
        entity = super.save(entity);
        CustomerInfoBO customerInfoBO = BeanTransform.copyProperties(entity, CustomerInfoBO.class);
        return customerInfoBO;
    }

    /**
     * 更新客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(CustomerInfoTO to) throws SerException {
//        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())){
            CustomerInfo model = super.findById(to.getId());
            if (model != null) {
                updateCustomerInfo(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新客户信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateCustomerInfo(CustomerInfoTO to, CustomerInfo model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除客户信息
     *
     * @param id 客户信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
//        checkPermission();
        super.remove(id);
    }

    /**
     * 根据市场活动id查询客户信息
     *
     * @param id 市场活动唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<CustomerInfoBO> findByMarketServeId(String id) throws SerException {
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", id));
        List<CustomerInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, CustomerInfoBO.class);
    }
}