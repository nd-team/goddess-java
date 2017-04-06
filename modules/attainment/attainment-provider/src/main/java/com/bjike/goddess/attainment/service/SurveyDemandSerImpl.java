package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyDemandBO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.DepartmentAPI;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调研需求业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyDemandSerImpl extends ServiceImpl<SurveyDemand, SurveyDemandDTO> implements SurveyDemandSer {

    @Autowired
    private DemandTypeSer demandTypeSer;
    @Autowired
    private AttainmentTypeSer attainmentTypeSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionAPI positionAPI;
    @Autowired
    private DepartmentAPI departmentAPI;

    private SurveyDemandBO transformBO(SurveyDemand entity) throws SerException {
        SurveyDemandBO bo = BeanTransform.copyProperties(entity, SurveyDemandBO.class);
        StringBuilder sb;
        switch (bo.getScope()) {
            case DEPARTMENT:
                sb = new StringBuilder(0);
                for (String id : bo.getScope_ids().split(","))
                    sb.append(departmentAPI.).append(",");
                break;
            case POSITION:
                sb = new StringBuilder(0);
                for (String id : bo.getScope_ids().split(","))
                    sb.append(positionAPI.findById(id).getName()).append(",");
                break;
            case PERSONAL:
                sb = new StringBuilder(0);
                for (String id : bo.getScope_ids().split(","))
                    sb.append(id).append(",");
                break;
            default:
                sb = new StringBuilder("全部");
                break;
        }
        bo.setScopeName(sb.toString());
    }


    @Override
    public SurveyDemandBO save(SurveyDemandTO to) throws SerException {
        SurveyDemand entity = BeanTransform.copyProperties(to, SurveyDemand.class, true);
        entity.setDemand(demandTypeSer.findById(to.getDemand_id()));
        entity.setType(attainmentTypeSer.findById(to.getType_id()));
        entity.setUsername(userAPI.currentUser().getUsername());
        entity.setLaunch(LocalDateTime.now());
        String scope = "";
        for (String id : to.getScopeIds())
            scope += id + ",";
        entity.setScope_ids(scope);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SurveyDemandBO.class);
    }

    @Override
    public SurveyDemandBO update(SurveyDemandTO to) throws SerException {
        SurveyDemand entity = BeanTransform.copyProperties(to, SurveyDemand.class, true), demand = super.findById(to.getId());
        if (null == demand)
            throw new SerException("程序错误,请刷新重试");
        entity.setUsername(demand.getUsername());
        entity.setLaunch(demand.getLaunch());
        entity.setCreateTime(demand.getCreateTime());
        entity.setDemand(demandTypeSer.findById(to.getDemand_id()));
        entity.setType(attainmentTypeSer.findById(to.getType_id()));
        entity.setModifyTime(LocalDateTime.now());
        String scope = "";
        for (String id : to.getScopeIds())
            scope += id + ",";
        entity.setScope_ids(scope);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SurveyDemandBO.class);
    }

    @Override
    public SurveyDemandBO delete(String id) throws SerException {
        SurveyDemand entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SurveyDemandBO.class);
    }

    @Override
    public SurveyDemandBO close(CloseDemandTO to) throws SerException {
        return null;
    }

    @Override
    public List<SurveyDemand> findByStatus(SurveyStatus status) throws SerException {
        return null;
    }
}