package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.CommunicationTempleBO;
import com.bjike.goddess.projectprocing.dto.CommunicationTempleDTO;
import com.bjike.goddess.projectprocing.entity.CommunicationTemple;
import com.bjike.goddess.projectprocing.to.CommunicationTempleTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 各类沟通交流模板业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-25 05:41 ]
 * @Description: [ 各类沟通交流模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class CommunicationTempleSerImpl extends ServiceImpl<CommunicationTemple, CommunicationTempleDTO> implements CommunicationTempleSer {
    @Override
    public Long countCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        Long count = super.count(communicationTempleDTO);
        return count;
    }

    @Override
    public CommunicationTempleBO getOneById(String id) throws SerException {
        CommunicationTemple communicationTemple = super.findById(id);
        return BeanTransform.copyProperties(communicationTemple,CommunicationTempleBO.class);
    }

    @Override
    public List<CommunicationTempleBO> listCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        List<CommunicationTemple> communicationTempleList = super.findByCis(communicationTempleDTO,true);
        return BeanTransform.copyProperties(communicationTempleList,CommunicationTempleBO.class);
    }

    @Override
    public CommunicationTempleBO addCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        CommunicationTemple communicationTemple = BeanTransform.copyProperties(communicationTempleTO,CommunicationTemple.class,true);
        communicationTemple.setCreateTime(LocalDateTime.now());
        super.save(communicationTemple);
        return BeanTransform.copyProperties(communicationTemple,CommunicationTempleBO.class);
    }

    @Override
    public CommunicationTempleBO editCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        CommunicationTemple communicationTemple = super.findById(communicationTempleTO.getId());
        LocalDateTime dateTime = communicationTemple.getCreateTime();
        communicationTemple = BeanTransform.copyProperties(communicationTempleTO,CommunicationTemple.class,true);
        communicationTemple.setCreateTime(dateTime);
        communicationTemple.setModifyTime(LocalDateTime.now());
        super.update(communicationTemple);
        return BeanTransform.copyProperties(communicationTemple,CommunicationTempleBO.class);
    }

    @Override
    public void deleteNode(String id) throws SerException {
        super.remove(id);
    }
}