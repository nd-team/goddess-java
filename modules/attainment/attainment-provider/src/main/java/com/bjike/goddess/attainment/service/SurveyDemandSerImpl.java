package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyDemandBO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.DepartmentAPI;
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        bo.setDemand_id(entity.getDemand().getId());
        bo.setDemandName(entity.getDemand().getType());
        bo.setType_id(entity.getType().getId());
        bo.setTypeName(entity.getType().getType());
        return bo;
    }

    private List<SurveyDemandBO> transformBOList(List<SurveyDemand> list) throws SerException {
        List<SurveyDemandBO> bos = new ArrayList<>(list.size());
        for (SurveyDemand entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }


    @Override
    public SurveyDemandBO save(SurveyDemandTO to) throws SerException {
        SurveyDemand entity = BeanTransform.copyProperties(to, SurveyDemand.class, true);
        entity.setDemand(demandTypeSer.findById(to.getDemand_id()));
        entity.setType(attainmentTypeSer.findById(to.getType_id()));
        entity.setUsername(userAPI.currentUser().getUsername());
        entity.setLaunch(LocalDateTime.now());
        String scope = "";
        for (String name : to.getScopeNames())
            scope += name + ",";
        entity.setScopeName(scope);
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public SurveyDemandBO update(SurveyDemandTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SurveyDemand entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                entity.setDemand(demandTypeSer.findById(to.getDemand_id()));
                entity.setType(attainmentTypeSer.findById(to.getType_id()));
                entity.setModifyTime(LocalDateTime.now());
                String scope = "";
                for (String name : to.getScopeNames())
                    scope += name + ",";
                entity.setScopeName(scope);
                super.update(entity);
                return this.transformBO(entity);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public SurveyDemandBO delete(String id) throws SerException {
        SurveyDemand entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public SurveyDemandBO close(CloseDemandTO to) throws SerException {
        SurveyDemand entity = super.findById(to.getId());
        entity.setCloseReason(to.getCloseReason());
        entity.setHandle(userAPI.currentUser().getUsername());
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyDemandBO> findByStatus(SurveyStatus status) throws SerException {
        SurveyDemandDTO dto = new SurveyDemandDTO();
        dto.getConditions().add(Restrict.eq("surveyStatus", status));
        List<SurveyDemand> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}