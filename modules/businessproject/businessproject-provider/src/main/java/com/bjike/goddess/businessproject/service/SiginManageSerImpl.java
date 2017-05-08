package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.SiginManageBO;
import com.bjike.goddess.businessproject.entity.BaseInfoManage;
import com.bjike.goddess.businessproject.to.SiginManageTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessproject.dto.SiginManageDTO;
import com.bjike.goddess.businessproject.entity.SiginManage;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商务项目合同签订与立项管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T19:37:28.303 ]
 * @Description: [ 商务项目合同签订与立项管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class SiginManageSerImpl extends ServiceImpl<SiginManage, SiginManageDTO> implements SiginManageSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public Long countSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        searchCondition( siginManageDTO);
        Long count = super.count( siginManageDTO );
        return count;
    }

    @Override
    public SiginManageBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能呢为空");
        }
        SiginManage siginManage = super.findById(id);
        return BeanTransform.copyProperties(siginManage, SiginManageBO.class );
    }
    @Override
    public List<SiginManageBO> listSiginManage(SiginManageDTO siginManageDTO) throws SerException {
        searchCondition( siginManageDTO);
        List<SiginManage> list = super.findByPage(siginManageDTO);
        List<SiginManageBO> siginManageBOS = BeanTransform.copyProperties(list, SiginManageBO.class);
        return siginManageBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO addSiginManage(SiginManageTO siginManageTO) throws SerException {
        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class,true);
        siginManage.setCreateTime(LocalDateTime.now());
        super.save( siginManage );

        SiginManageBO siginManageBO = BeanTransform.copyProperties(siginManage , SiginManageBO.class);
        return siginManageBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO editSiginManage(SiginManageTO siginManageTO) throws SerException {
        SiginManage temp = super.findById( siginManageTO.getId() );
        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class,true);
        BeanUtils.copyProperties( siginManage , temp ,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );

        SiginManageBO siginManageBO = BeanTransform.copyProperties(temp , SiginManageBO.class);
        return siginManageBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSiginManage(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SiginManageBO auditSiginManage(SiginManageTO siginManageTO) throws SerException {
        SiginManage temp = super.findById( siginManageTO.getId() );

        siginManageTO.setManager( userAPI.currentUser().getUsername());
        SiginManage siginManage = BeanTransform.copyProperties(siginManageTO, SiginManage.class,true);

        BeanUtils.copyProperties( siginManage , temp ,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );

        SiginManageBO siginManageBO = BeanTransform.copyProperties(temp , SiginManageBO.class);
        return siginManageBO;
    }


    public void searchCondition(SiginManageDTO siginManageDTO) throws SerException {
        /**
         * 业务类型
         */
        if(siginManageDTO.getBusinessType() != null ){
            siginManageDTO.getConditions().add(Restrict.eq("businessType",siginManageDTO.getBusinessType()));
        }
        /**
         * 业务方向科目
         */
        if(StringUtils.isNotBlank(siginManageDTO.getBusinessSubject())){
            siginManageDTO.getConditions().add(Restrict.like("businessSubject",siginManageDTO.getBusinessSubject()));
        }
        /**
         * 合作方式
         */
        if(siginManageDTO.getBusinessCooperate() != null ){
            siginManageDTO.getConditions().add(Restrict.eq("businessCooperate",siginManageDTO.getBusinessCooperate()));
        }
        /**
         * 甲方公司
         */
        if(StringUtils.isNotBlank(siginManageDTO.getFirstCompany())){
            siginManageDTO.getConditions().add(Restrict.like("firstCompany",siginManageDTO.getFirstCompany()));
        }
        /**
         * 乙方公司
         */
        if(StringUtils.isNotBlank(siginManageDTO.getSecondCompany())){
            siginManageDTO.getConditions().add(Restrict.like("secondCompany",siginManageDTO.getSecondCompany()));
        }
        /**
         * 地区
         */
        if(StringUtils.isNotBlank(siginManageDTO.getArea())){
            siginManageDTO.getConditions().add(Restrict.like("area",siginManageDTO.getArea()));
        }
        /**
         * 合同属性
         */
        if(siginManageDTO.getContractProperty() != null ){
            siginManageDTO.getConditions().add(Restrict.eq("contractProperty",siginManageDTO.getContractProperty()));
        }
        /**
         * 立项情况
         */
        if(StringUtils.isNotBlank(siginManageDTO.getMakeProject() ) ){
            siginManageDTO.getConditions().add(Restrict.eq("makeProject",siginManageDTO.getMakeProject()));
        }


    }

    @Override
    public List<String> listArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<SiginManageBO> siginManageBOS =super.findBySql("select area,1 from businessproject_siginmanage order by area asc ", SiginManageBO.class, fields);

        List<String> areaList  = siginManageBOS.stream().map(SiginManageBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim())) ).distinct().collect(Collectors.toList());


        return areaList;
    }
}