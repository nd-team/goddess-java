package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.entity.DriverInfo;
import com.bjike.goddess.carinfo.to.DriverInfoTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车辆信息管理业务实现
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:46 ]
 * @Description: [ 车辆信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "carinfoSerCache")
@Service
public class DriverInfoSerImpl extends ServiceImpl<DriverInfo, DriverInfoDTO> implements DriverInfoSer {


    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverInfoBO insertModel(DriverInfoTO to) throws SerException {
        DriverInfo driverInfo = findByIdCard(to.getIdCard());
        if (driverInfo == null) {
            DriverInfo model = BeanTransform.copyProperties(to, DriverInfo.class, true);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, DriverInfoBO.class);
        } else {
            throw new SerException("该身份证号码对应的司机已存在!");
        }
    }

    public DriverInfo findByIdCard(String idCard) throws SerException {
        DriverInfoDTO dto = new DriverInfoDTO();
        dto.getConditions().add(Restrict.eq("idCard", idCard));
        return super.findOne(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverInfoBO updateModel(DriverInfoTO to) throws SerException {
        DriverInfo model = super.findById(to.getId());
        if (model != null) {
            DriverInfo driverInfo = findByIdCard(to.getIdCard());
            if (driverInfo == null || (driverInfo != null && driverInfo.getId().equals(model.getId()))) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, DriverInfoBO.class);
            } else {
                throw new SerException("该身份证号码对应的司机已存在!");
            }
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }

    @Override
    public List<DriverInfoBO> pageList(DriverInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), DriverInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, String suggest, Boolean audit) throws SerException {
        DriverInfo model = super.findById(id);
        if (model != null) {
            //TODO 未明确组织结构信息及账务模块审核对象
            model.setSuggest(suggest);
            model.setAudit(audit);
            super.update(model);
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }

    @Override
    public DriverInfoBO findByDriver(String driver) throws SerException {
        DriverInfoDTO dto = new DriverInfoDTO();
        dto.getConditions().add(Restrict.eq("driver", driver));
        List<DriverInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list.get(0), DriverInfoBO.class);
    }
}