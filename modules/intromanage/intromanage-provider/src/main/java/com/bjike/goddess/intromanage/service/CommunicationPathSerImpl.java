package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.CommunicationPathBO;
import com.bjike.goddess.intromanage.dto.CommunicationPathDTO;
import com.bjike.goddess.intromanage.entity.CommunicationPath;
import com.bjike.goddess.intromanage.to.CommunicationPathTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 通讯途径业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 06:02 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class CommunicationPathSerImpl extends ServiceImpl<CommunicationPath, CommunicationPathDTO> implements CommunicationPathSer {

    /**
     * 分页查询通讯途径
     *
     * @return class CommunicationPathBO
     * @throws SerException
     */
    @Override
    public List<CommunicationPathBO> list(CommunicationPathDTO dto) throws SerException {
        List<CommunicationPath> list = super.findByPage(dto);
        List<CommunicationPathBO> listBO = BeanTransform.copyProperties(list, CommunicationPathBO.class);
        return listBO;
    }

    /**
     * 保存通讯途径
     *
     * @param to 通讯途径to
     * @return class CommunicationPathBO
     * @throws SerException
     */
    @Override
    @Transactional
    public CommunicationPathBO save(CommunicationPathTO to) throws SerException {
        CommunicationPath entity = BeanTransform.copyProperties(to, CommunicationPath.class, true);
        entity = super.save(entity);
        CommunicationPathBO bo = BeanTransform.copyProperties(entity, CommunicationPathBO.class);
        return bo;
    }

    /**
     * 更新通讯途径
     *
     * @param to 通讯途径to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(CommunicationPathTO to) throws SerException {
        CommunicationPath entity = BeanTransform.copyProperties(to, CommunicationPath.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除通讯途径
     *
     * @param id 通讯途径唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}