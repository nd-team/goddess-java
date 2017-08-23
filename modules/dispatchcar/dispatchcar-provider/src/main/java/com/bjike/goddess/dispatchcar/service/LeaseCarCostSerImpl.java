package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.bo.LeaseCarCostBO;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.entity.LeaseCarCost;
import com.bjike.goddess.dispatchcar.to.LeaseCarCostTO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租车费用基本信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dispatchcarSerCache")
@Service
public class LeaseCarCostSerImpl extends ServiceImpl<LeaseCarCost, LeaseCarCostDTO> implements LeaseCarCostSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

    @Override
    public LeaseCarCostBO insertModel(LeaseCarCostTO to) throws SerException {
        LeaseCarCostDTO dto = new LeaseCarCostDTO();
        dto.getConditions().add(Restrict.eq("area", to.getArea()));
        dto.getConditions().add(Restrict.eq("group", to.getGroup()));
        List<LeaseCarCost> list = super.findByCis(dto);
        if (list != null && !list.isEmpty()) {
            throw new SerException(to.getArea() + to.getGroup() + "的租车费用已经存在");
        }
        LeaseCarCost model = BeanTransform.copyProperties(to, LeaseCarCost.class, true);
        model.setCreateUser(userAPI.currentUser().getUsername());
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, LeaseCarCostBO.class);
    }

    @Override
    public LeaseCarCostBO updateModel(LeaseCarCostTO to) throws SerException {
        LeaseCarCost model = super.findById(to.getId());
        if (model != null) {
            LeaseCarCostDTO dto = new LeaseCarCostDTO();
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
            dto.getConditions().add(Restrict.eq("group", to.getGroup()));
            List<LeaseCarCost> list = super.findByCis(dto);
            if (list != null && !list.isEmpty()) {
                throw new SerException(to.getArea() + to.getGroup() + "的租车费用已经存在");
            }
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            model.setUpdateUser(userAPI.currentUser().getUsername());
            super.update(model);
        } else {
            throw new SerException("更新对象不能为空");
        }
        return BeanTransform.copyProperties(to, LeaseCarCostBO.class);
    }

    @Override
    public List<LeaseCarCostBO> pageList(LeaseCarCostDTO dto) throws SerException {
        dto.getSorts().add("createTime");
        List<LeaseCarCost> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, LeaseCarCostBO.class);
    }

    @Override
    public List<OpinionBO> findDeapartment() throws SerException {
        List<OpinionBO> boList = departmentDetailAPI.findThawOpinion();
        return boList;
    }

    @Override
    public List<AreaBO> findArea() throws SerException {
        List<AreaBO> boList = departmentDetailAPI.findArea();
        return boList;
    }
}