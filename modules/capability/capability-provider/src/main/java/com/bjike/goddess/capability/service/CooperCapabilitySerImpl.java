package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CooperCapabilityBO;
import com.bjike.goddess.capability.to.CooperCapabilityTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CooperCapabilityDTO;
import com.bjike.goddess.capability.entity.CooperCapability;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 合作对象商务展示业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:31 ]
 * @Description: [ 合作对象商务展示业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CooperCapabilitySerImpl extends ServiceImpl<CooperCapability, CooperCapabilityDTO> implements CooperCapabilitySer {

    @Override
    public Long counts(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {
        if (StringUtils.isNotBlank(cooperCapabilityDTO.getCompanyName() )) {
            cooperCapabilityDTO.getConditions().add(Restrict.like("companyName",cooperCapabilityDTO.getCompanyName()));
        }
        Long count = super.count(cooperCapabilityDTO);
        return count;
    }

    @Override
    public CooperCapabilityBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空哦");
        }
        CooperCapability selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability,CooperCapabilityBO.class);
    }

    @Override
    public List<CooperCapabilityBO> listCooperCapability(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {

        cooperCapabilityDTO.getSorts().add("createTime=desc");
        if (StringUtils.isNotBlank(cooperCapabilityDTO.getCompanyName() )) {
            cooperCapabilityDTO.getConditions().add(Restrict.like("companyName",cooperCapabilityDTO.getCompanyName()));
        }
        List<CooperCapability> list = super.findByCis(cooperCapabilityDTO, true);

        return BeanTransform.copyProperties(list, CooperCapabilityBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperCapabilityBO addCooperCapability(CooperCapabilityTO cooperCapabilityTO) throws SerException {
        CooperCapability cooperCapability = BeanTransform.copyProperties(cooperCapabilityTO,CooperCapability.class,true);
        cooperCapability.setCreateTime(LocalDateTime.now());

        CooperCapabilityDTO dto = new CooperCapabilityDTO();
        dto.getConditions().add(Restrict.eq("companyName",cooperCapabilityTO.getCompanyName().trim()) );
        List<CooperCapability> cooperCapabilityList = super.findByCis( dto );
        if( cooperCapabilityList != null && cooperCapabilityList.size() > 0 ){
            CooperCapability cooper = cooperCapabilityList.get(0);
            if(  cooper.getCompanyName().equals( cooperCapabilityTO.getCompanyName().trim() )
                    && (! cooper.getProfessionAuthen().equals( cooperCapabilityTO.getProfessionAuthen().trim() )
                    || ! cooper.getManageAuthen().equals( cooperCapabilityTO.getManageAuthen().trim() )
                    || ! cooper.getCompanyCertificate().equals( cooperCapabilityTO.getCompanyCertificate().trim() )
                    || ! cooper.getCompanyProject().equals( cooperCapabilityTO.getCompanyProject().trim() )
                    || ! cooper.getCompletePro().equals( cooperCapabilityTO.getCompletePro() )) ){

                throw  new SerException("公司名不能相同");
            }else{
                super.save( cooperCapability );
            }
        }else{
            super.save( cooperCapability );
        }

        return BeanTransform.copyProperties(cooperCapability, CooperCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperCapabilityBO editCooperCapability(CooperCapabilityTO cooperCapabilityTO) throws SerException {
        CooperCapability cooperCapability = super.findById( cooperCapabilityTO.getId() );

        CooperCapabilityDTO dto = new CooperCapabilityDTO();
        dto.getConditions().add(Restrict.eq("companyName",cooperCapability.getCompanyName().trim()) );
        List<CooperCapability> cooperCapabilityList = super.findByCis( dto );
        cooperCapabilityList.stream().forEach(str -> {
            str.setCompanyName( cooperCapabilityTO.getCompanyName() );
            str.setProfessionAuthen( cooperCapabilityTO.getProfessionAuthen() );
            str.setManageAuthen( cooperCapabilityTO.getManageAuthen() );
            str.setCompanyCertificate( cooperCapabilityTO.getCompanyCertificate() );
            str.setCompanyProject( cooperCapabilityTO.getCompanyProject() );
            str.setCompletePro( cooperCapabilityTO.getCompletePro() );
            str.setModifyTime(LocalDateTime.now());
        });
        super.update( cooperCapabilityList );
        return BeanTransform.copyProperties(cooperCapability, CooperCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCooperCapability(String id) throws SerException {
        super.remove(id);
    }

    
    @Override
    public CooperCapabilityBO getCompanyConnector(String id) throws SerException {
        CooperCapability cooperCapability = super.findById( id );
        return BeanTransform.copyProperties(cooperCapability, CooperCapabilityBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperCapabilityBO editCompanyConnector(CooperCapabilityTO cooperCapabilityTO) throws SerException {
        CooperCapability cooperCapability = super.findById( cooperCapabilityTO.getId() );

        cooperCapability.setContactName( cooperCapabilityTO.getContactName());
        cooperCapability.setSex( cooperCapabilityTO.getSex());
        cooperCapability.setContactWay( cooperCapabilityTO.getContactWay());
        cooperCapability.setEmailName( cooperCapabilityTO.getEmailName());
        cooperCapability.setQqOrWechat( cooperCapabilityTO.getQqOrWechat());
        cooperCapability.setNatives( cooperCapabilityTO.getNatives());
        cooperCapability.setHobby( cooperCapabilityTO.getHobby());
        cooperCapability.setCharact( cooperCapabilityTO.getCharact());
        cooperCapability.setFamily( cooperCapabilityTO.getFamily());
        cooperCapability.setFamilyRelation( cooperCapabilityTO.getFamilyRelation());
        cooperCapability.setStudyExperience( cooperCapabilityTO.getStudyExperience());
        cooperCapability.setConnectExperience( cooperCapabilityTO.getConnectExperience());
        cooperCapability.setOldWorkPlace( cooperCapabilityTO.getOldWorkPlace());
        cooperCapability.setLivePlace( cooperCapabilityTO.getLivePlace());
        cooperCapability.setGrowthPlace( cooperCapabilityTO.getGrowthPlace());
        super.update( cooperCapability );
        return BeanTransform.copyProperties(cooperCapability, CooperCapabilityBO.class);
    }

    
    @Override
    public List<CooperCapabilityBO> listCooperCapabilityByName(CooperCapabilityDTO cooperCapabilityDTO) throws SerException {
        List<CooperCapabilityBO> cooperCapabilityBOS = new ArrayList<>();
        if(StringUtils.isNotBlank(cooperCapabilityDTO.getCompanyName()) ){
            cooperCapabilityDTO.getConditions().add( Restrict.eq("companyName",cooperCapabilityDTO.getCompanyName().trim()) );
            List<CooperCapability> cooperCapabilityList =  super.findByPage( cooperCapabilityDTO );
            cooperCapabilityBOS = BeanTransform.copyProperties( cooperCapabilityList ,CooperCapabilityBO.class );
        }
        return cooperCapabilityBOS;
    }

    
    @Override
    public CooperCapabilityBO getCompany(String companyName) throws SerException {
        CooperCapabilityBO cooperCapabilityBO = new CooperCapabilityBO();

        String[] fields = new String[]{"companyName","professionAuthen","manageAuthen","companyCertificate","companyProject","completePro"};
        List<CooperCapabilityBO> cooperBOS =super.findBySql("select companyName ,professionAuthen,manageAuthen,companyCertificate, companyProject , " +
                " completePro from capability_coopercapability where companyName = '"+companyName.trim()+"' " , CooperCapabilityBO.class, fields);

        if( cooperBOS != null && cooperBOS.size() >0 ){
            cooperCapabilityBO = cooperBOS.get(0);
        }

        return cooperCapabilityBO;
    }

    
    @Override
    public List<String> listAllCompanyName() throws SerException {
        String[] fields = new String[]{"companyName"};
        List<CooperCapabilityBO> cooperBOS =super.findBySql("select companyName  from capability_coopercapability group by companyName " , CooperCapabilityBO.class, fields);

        List<String> name = cooperBOS.stream().map(CooperCapabilityBO::getCompanyName).collect(Collectors.toList());
        return name;
    }

    @Override
    public List<CooperCapabilityBO> listCompanyContact(String companyName) throws SerException {
        String[] fields = new String[]{"companyName","contactName","contactWay"};
        List<CooperCapabilityBO> cooperBOS =super.findBySql("select companyName,contactName,contactWay from capability_coopercapability where  companyName ='"+companyName+"'" , CooperCapabilityBO.class, fields);

        return BeanTransform.copyProperties( cooperBOS , CooperCapabilityBO.class );
    }
}