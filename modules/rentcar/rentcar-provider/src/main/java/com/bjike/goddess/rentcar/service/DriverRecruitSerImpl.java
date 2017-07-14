package com.bjike.goddess.rentcar.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentcar.bo.DriverRecruitBO;
import com.bjike.goddess.rentcar.bo.DriverRecruitBO;
import com.bjike.goddess.rentcar.dto.DriverRecruitDTO;
import com.bjike.goddess.rentcar.entity.DriverInfo;
import com.bjike.goddess.rentcar.entity.DriverRecruit;
import com.bjike.goddess.rentcar.entity.DriverRecruit;
import com.bjike.goddess.rentcar.to.DriverRecruitTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 司机招聘信息业务实现
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:28 ]
 * @Description: [ 司机招聘信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentcarSerCache")
@Service
public class DriverRecruitSerImpl extends ServiceImpl<DriverRecruit, DriverRecruitDTO> implements DriverRecruitSer {

    @Autowired
    private DriverInfoSer driverInfoSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverRecruitBO insertModel(DriverRecruitTO to) throws SerException {
        DriverRecruit model = BeanTransform.copyProperties(to, DriverRecruit.class, true);
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, DriverRecruitBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverRecruitBO updateModel(DriverRecruitTO to) throws SerException {
        DriverRecruit model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
            return BeanTransform.copyProperties(to, DriverRecruitBO.class);
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }

    @Override
    public List<DriverRecruitBO> pageList(DriverRecruitDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), DriverRecruitBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, String suggest, Boolean audit) throws SerException {
        DriverRecruit model = super.findById(id);
        if (model != null) {

            //TODO 未明确组织结构信息及账务模块审核对象

            Boolean auditTemp = model.getAudit();
            if (auditTemp == null || !auditTemp) {
                model.setSuggest(suggest);
                model.setAudit(audit);
                super.update(model);
                //未审核通过且本次审核通过则,如果
                if (audit) {
                    DriverInfo driverInfo = new DriverInfo();
                    BeanUtils.copyProperties(model,driverInfo);
                    driverInfoSer.save(driverInfo);
                }
            } else {
                throw new SerException("该司机招聘信息已审核!");
            }
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }
}