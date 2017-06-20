package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.SelfCapabilitySocialBO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.entity.SelfCapability;
import com.bjike.goddess.capability.entity.SelfCapabilitySocial;
import com.bjike.goddess.capability.to.SelfCapabilitySocialTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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
public class SelfCapabilitySocialSerImpl extends ServiceImpl<SelfCapabilitySocial, SelfCapabilitySocialDTO> implements SelfCapabilitySocialSer {

    @Autowired
    private CusPermissionSer cusPermissionSer ;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public Long counts(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws SerException {
        if (StringUtils.isBlank(selfCapabilitySocialDTO.getSelfCapabilityId())) {
            throw new SerException("个人能力id不能为空哦");
        }
        selfCapabilitySocialDTO.getConditions().add(Restrict.eq("selfCapabilityId", selfCapabilitySocialDTO.getSelfCapabilityId()));
        Long count = super.count(selfCapabilitySocialDTO);
        return count;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SelfCapabilitySocialBO getOne(String id) throws SerException {


        if (StringUtils.isBlank(id)) {
            throw new SerException("社交id不能为空哦");
        }
        SelfCapabilitySocial selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability, SelfCapabilitySocialBO.class);
    }

    @Override
    public List<SelfCapabilitySocialBO> listSelfCapabilitySocial(SelfCapabilitySocialDTO selfCapabilitySocialDTO) throws SerException {
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        if (StringUtils.isBlank(selfCapabilitySocialDTO.getSelfCapabilityId())) {
            throw new SerException("个人能力id不能为空哦");
        }
        selfCapabilitySocialDTO.getConditions().add(Restrict.eq("selfCapabilityId", selfCapabilitySocialDTO.getSelfCapabilityId()));
        selfCapabilitySocialDTO.getSorts().add("createTime=desc");
        List<SelfCapabilitySocial> list = super.findByCis(selfCapabilitySocialDTO, true);

        return BeanTransform.copyProperties(list, SelfCapabilitySocialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfCapabilitySocialBO addSelfCapabilitySocial(SelfCapabilitySocialTO selfCapabilitySocialTO) throws SerException {
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加个人社交能力操作");
        }

        if (StringUtils.isBlank(selfCapabilitySocialTO.getSelfCapabilityId())) {
            throw new SerException("个人能力id不能为空哦");
        }
        SelfCapabilitySocial temp = BeanTransform.copyProperties(selfCapabilitySocialTO, SelfCapabilitySocial.class, true);
        temp.setCreateTime(LocalDateTime.now());
        temp.setModifyTime(LocalDateTime.now());
        super.save(temp);
        return BeanTransform.copyProperties(temp, SelfCapabilitySocialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SelfCapabilitySocialBO editSelfCapabilitySocial(String id ,SelfCapabilitySocialTO selfCapabilitySocialTO) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑个人社交能力操作");
        }
        if (StringUtils.isBlank(id)) {
            throw new SerException("社交id不能为空哦");
        }
        SelfCapabilitySocial temp = super.findById(id);
        SelfCapabilitySocial selfCapabilitySocial = BeanTransform.copyProperties(selfCapabilitySocialTO, SelfCapabilitySocial.class, true);

        BeanUtils.copyProperties( selfCapabilitySocial , temp ,"id","selfCapabilityId","createTime");

        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
        return BeanTransform.copyProperties(temp, SelfCapabilitySocialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSelfCapabilitySocial(String id) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if ( !permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除个人社交能力操作");
        }
        if (StringUtils.isBlank(id)) {
            throw new SerException("社交id不能为空哦");
        }
        super.remove(id);
    }


}