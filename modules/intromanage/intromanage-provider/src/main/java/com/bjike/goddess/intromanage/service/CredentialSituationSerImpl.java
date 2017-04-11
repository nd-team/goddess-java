package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.CredentialSituationBO;
import com.bjike.goddess.intromanage.dto.CredentialSituationDTO;
import com.bjike.goddess.intromanage.entity.CredentialSituation;
import com.bjike.goddess.intromanage.to.CredentialSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 证书情况业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:52 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class CredentialSituationSerImpl extends ServiceImpl<CredentialSituation, CredentialSituationDTO> implements CredentialSituationSer {

    /**
     * 分页查询证书情况
     *
     * @return class CredentialSituationBO
     * @throws SerException
     */
    @Override
    public List<CredentialSituationBO> list(CredentialSituationDTO dto) throws SerException {
        List<CredentialSituation> list = super.findByPage(dto);
        List<CredentialSituationBO> listBO = BeanTransform.copyProperties(list, CredentialSituationBO.class);
        return listBO;
    }

    /**
     * 保存证书情况
     *
     * @param to 证书情况to
     * @return class CredentialSituationBO
     * @throws SerException
     */
    @Override
    @Transactional
    public CredentialSituationBO save(CredentialSituationTO to) throws SerException {
        CredentialSituation entity = BeanTransform.copyProperties(to, CredentialSituation.class, true);
        entity = super.save(entity);
        CredentialSituationBO bo = BeanTransform.copyProperties(entity, CredentialSituationBO.class);
        return bo;
    }

    /**
     * 更新证书情况
     *
     * @param to 证书情况to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(CredentialSituationTO to) throws SerException {
        CredentialSituation entity = BeanTransform.copyProperties(to, CredentialSituation.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除证书情况
     *
     * @param id 证书情况唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}