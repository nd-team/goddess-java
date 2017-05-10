package com.bjike.goddess.devicerepair.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.entity.DeviceRepair;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.type.MaterialState;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备维修业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [ 设备维修业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "devicerepairSerCache")
@Service
public class DeviceRepairSerImpl extends ServiceImpl<DeviceRepair, DeviceRepairDTO> implements DeviceRepairSer {

    @Autowired
    private UserAPI userAPI;

    /**
     * 分页查询设备维修
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<DeviceRepairBO> list(DeviceRepairDTO dto) throws SerException {
        List<DeviceRepair> list = super.findByPage(dto);
        List<DeviceRepairBO> boList = BeanTransform.copyProperties(list, DeviceRepairBO.class);
        return boList;
    }

    /**
     * 保存设备维修
     *
     * @param to 设备维修to
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public DeviceRepairBO save(DeviceRepairTO to) throws SerException {
        String materialCoding = to.getMaterialCoding();//获取物资编号

        DeviceRepair entity = BeanTransform.copyProperties(to, DeviceRepair.class, true);
        entity.setWelfareAuditState(AuditState.UNAUDITED);//福利模块未审核
        entity.setPmAuditState(AuditState.UNAUDITED);     //项目经理未审核
        entity.setWhetherPayment(Boolean.FALSE);
        entity = super.save(entity);
        DeviceRepairBO bo = BeanTransform.copyProperties(entity, DeviceRepairBO.class);
        return bo;
    }

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新设备维修
     *
     * @param to 设备维修to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(DeviceRepairTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            DeviceRepair model = super.findById(to.getId());
            if (model != null) {
                updateDeviceRepair(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新设备维修
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateDeviceRepair(DeviceRepairTO to, DeviceRepair model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 福利模块审核
     *
     * @param to 福利模块审核to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void welfareAudit(WelfareAuditTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            DeviceRepair model = super.findById(to.getId());
            if (model != null) {
                checkWelfareModule(model);//检测当前用户是否是福利模块负责人
                welfareAudit(to, model);
            } else {
                throw new SerException("福利模块审核对象不能为空.");
            }
        } else {
            throw new SerException("福利模块审核id不能为空.");
        }
    }

    /**
     * 项目经理审核状态
     *
     * @param id           设备维修唯一标识
     * @param pmAuditState 项目经理审核状态
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pmAudit(String id, AuditState pmAuditState) throws SerException {
        DeviceRepair model = super.findById(id);
        if (model != null) {
            checkPm(model); //检查当前用户是否是项目经理
            model.setPmAuditState(pmAuditState);//项目经理审核状态
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("项目经理审核对象不能为空.");
        }
    }

    /**
     * 检查当前用户是否是项目经理
     *
     * @param model 设备维修
     * @throws SerException
     */
    private void checkPm(DeviceRepair model) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        String pm = model.getPm();
        if (!curUsername.equals(pm)) {
            throw new SerException("当前用户不是项目经理,无法进行审核.");
        }
    }

    /**
     * 检测当前用户是否是福利模块负责人
     *
     * @param model 设备维修
     * @throws SerException
     */
    private void checkWelfareModule(DeviceRepair model) throws SerException {
        String welfareModule = model.getWelfareModule();
        String curUsername = userAPI.currentUser().getUsername();
        if (!curUsername.equals(welfareModule)) {
            throw new SerException("福利模块负责人为空或者当前用户不是福利模块负责人,无法执行审核");
        }
    }

    /**
     * 福利模块审核
     *
     * @param to    设备维修to
     * @param model 设备维修
     * @throws SerException
     */
    private void welfareAudit(WelfareAuditTO to, DeviceRepair model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deviceScrap(String id, String deviceIssue) throws SerException {
        DeviceRepair model = super.findById(id);
        if (model != null) {
            model.setMaterialState(MaterialState.SCRAPED);//设置状态为报废
            model.setDeviceIssue(deviceIssue);            //设备出现的问题
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空.");
        }
    }

    /**
     * 取回设备
     *
     * @param to 取回设备
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fetchDevice(FetchDeviceTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            DeviceRepair model = super.findById(to.getId());
            if (model != null) {
                fetchDevice(to, model);
            } else {
                throw new SerException("更新对象不能为空.");
            }
        } else {
            throw new SerException("设备维修id不能为空.");
        }
    }

    /**
     * 取回设备
     *
     * @param to    取回设备to
     * @param model 设备维修
     */
    private void fetchDevice(FetchDeviceTO to, DeviceRepair model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}