package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.entity.SelfCapabilitySocial;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.entity.SelfCapability;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个人能力展示业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class SelfCapabilitySerImpl extends ServiceImpl<SelfCapability, SelfCapabilityDTO> implements SelfCapabilitySer {

    @Autowired
    private SelfCapabilitySocialSer selfCapabilitySocialSer;

    @Override
    public Long counts(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        Long count = super.count(selfCapabilityDTO);
        return count;
    }

    @Override
    public SelfCapabilityBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空哦");
        }
        SelfCapability selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability,SelfCapabilityBO.class);
    }

    @Override
    public List<SelfCapabilityBO> listSelfCapability(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        selfCapabilityDTO.getSorts().add("createTime=desc");
        List<SelfCapability> list = super.findByCis(selfCapabilityDTO, true);

        return BeanTransform.copyProperties(list, SelfCapabilityBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfCapabilityBO addSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        SelfCapability selfCapability = BeanTransform.copyProperties(selfCapabilityTO,SelfCapability.class,true);
        selfCapability.setCreateTime(LocalDateTime.now());

        SelfCapabilityDTO dto = new SelfCapabilityDTO();
        dto.getConditions().add(Restrict.eq("name",selfCapabilityTO.getName().trim()) );
        List<SelfCapability> selfCapabilityList = super.findByCis( dto );
        if( selfCapabilityList != null && selfCapabilityList.size() > 0 ){
            SelfCapability self = selfCapabilityList.get(0);
            if(  self.getName().equals( selfCapabilityTO.getName().trim() )
                    && ( ! self.getSelfJobTitle().equals( selfCapabilityTO.getSelfJobTitle().trim() )
                    || ! self.getPositionTitle().equals( selfCapabilityTO.getPositionTitle().trim() )
                    || ! self.getWorkYear().equals( selfCapabilityTO.getWorkYear().trim() )
                    || ! self.getSelfProject().equals( selfCapabilityTO.getSelfProject().trim() )) ){

                throw  new SerException("个人姓名不能相同");
            }else{
                super.save( selfCapability );
            }
        }else{
            super.save( selfCapability );
        }

        return BeanTransform.copyProperties(selfCapability, SelfCapabilityBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfCapabilityBO editSelfCapability(SelfCapabilityTO selfCapabilityTO) throws SerException {
        if( StringUtils.isBlank(selfCapabilityTO.getName())){
            throw new SerException("失败，姓名不能为空");
        }
        SelfCapability selfCapability = super.findById( selfCapabilityTO.getId() );

        SelfCapabilityDTO dto = new SelfCapabilityDTO();
        dto.getConditions().add(Restrict.eq("name",selfCapability.getName().trim()) );
        List<SelfCapability> selfCapabilityList = super.findByCis( dto );
        selfCapabilityList.stream().forEach(str -> {
            str.setName( selfCapabilityTO.getName() );
            str.setSelfJobTitle( selfCapabilityTO.getSelfJobTitle() );
            str.setPositionTitle( selfCapabilityTO.getPositionTitle() );
            str.setWorkYear( selfCapabilityTO.getWorkYear() );
            str.setSelfProject( selfCapabilityTO.getSelfProject() );
            str.setModifyTime(LocalDateTime.now());
        });
        super.update( selfCapabilityList );
        return BeanTransform.copyProperties(selfCapability, SelfCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSelfCapability(String id) throws SerException {
        //先删社交
        SelfCapabilitySocialDTO ssDTO = new SelfCapabilitySocialDTO();
        ssDTO.getConditions().add(Restrict.eq("selfCapabilityId",id));
        List<SelfCapabilitySocial> selfCapabilitySocialList = selfCapabilitySocialSer.findByCis( ssDTO );
        selfCapabilitySocialSer.remove( selfCapabilitySocialList );

        super.remove(id);
    }


    
    @Override
    public List<SelfCapabilityBO> listSelfCapabilityByName(SelfCapabilityDTO selfCapabilityDTO) throws SerException {
        List<SelfCapabilityBO> selfCapabilityBOS = new ArrayList<>();
        if(StringUtils.isNotBlank(selfCapabilityDTO.getName()) ){
            selfCapabilityDTO.getConditions().add( Restrict.eq("name",selfCapabilityDTO.getName().trim()) );
            List<SelfCapability> selfCapabilityList =  super.findByPage( selfCapabilityDTO );
            selfCapabilityBOS = BeanTransform.copyProperties( selfCapabilityList ,SelfCapabilityBO.class );
        }
        return selfCapabilityBOS;
    }

    
    @Override
    public SelfCapabilityBO getSelf(String name) throws SerException {
        SelfCapabilityBO selfCapabilityBO = new SelfCapabilityBO();

        String[] fields = new String[]{"name","selfJobTitle","positionTitle","workYear","selfProject"};
        List<SelfCapabilityBO> selfBOS =super.findBySql("select name ,selfJobTitle,positionTitle,workYear, selfProject " +
                " from capability_selfcapability where name = '"+name.trim()+"' " , SelfCapabilityBO.class, fields);

        if( selfBOS != null && selfBOS.size() >0 ){
            selfCapabilityBO = selfBOS.get(0);
        }

        return selfCapabilityBO;
    }

    
    @Override
    public List<String> listAllSelfName() throws SerException {

        String[] fields = new String[]{"name"};
        List<SelfCapabilityBO> selfBOS =super.findBySql("select name  from capability_selfcapability group by name " , SelfCapabilityBO.class, fields);

        List<String> name = selfBOS.stream().map(SelfCapabilityBO::getName).collect(Collectors.toList());
        return name;
    }
}