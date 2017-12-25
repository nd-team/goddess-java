package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.MainBusinessIntroBO;
import com.bjike.goddess.intromanage.dto.MainBusinessIntroDTO;
import com.bjike.goddess.intromanage.entity.MainBusinessIntro;
import com.bjike.goddess.intromanage.to.MainBusinessIntroTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 主业介绍业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:41 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class MainBusinessIntroSerImpl extends ServiceImpl<MainBusinessIntro, MainBusinessIntroDTO> implements MainBusinessIntroSer {

    /**
     * 分页查询主业业务
     *
     * @return class MainBusinessIntroBO
     * @throws SerException
     */
    @Override
    public List<MainBusinessIntroBO> list(MainBusinessIntroDTO dto) throws SerException {
        List<MainBusinessIntro> list = super.findByPage(dto);
        List<MainBusinessIntroBO> listBO = BeanTransform.copyProperties(list, MainBusinessIntroBO.class);
        return listBO;
    }

    /**
     * 保存主业业务
     *
     * @param to 主业业务to
     * @return class MainBusinessIntroBO
     * @throws SerException
     */
    @Override
    @Transactional
    public MainBusinessIntroBO save(MainBusinessIntroTO to) throws SerException {
        MainBusinessIntro entity = BeanTransform.copyProperties(to, MainBusinessIntro.class, true);
        entity = super.save(entity);
        MainBusinessIntroBO bo = BeanTransform.copyProperties(entity, MainBusinessIntroBO.class);
        return bo;
    }

    /**
     * 更新主业业务
     *
     * @param to 主业业务to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(MainBusinessIntroTO to) throws SerException {
        MainBusinessIntro entity = BeanTransform.copyProperties(to, MainBusinessIntro.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除主业业务
     *
     * @param id 主业业务唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}