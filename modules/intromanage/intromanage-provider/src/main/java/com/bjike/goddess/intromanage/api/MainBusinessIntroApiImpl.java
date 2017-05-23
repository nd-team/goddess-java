package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.MainBusinessIntroBO;
import com.bjike.goddess.intromanage.dto.MainBusinessIntroDTO;
import com.bjike.goddess.intromanage.entity.MainBusinessIntro;
import com.bjike.goddess.intromanage.service.MainBusinessIntroSer;
import com.bjike.goddess.intromanage.to.MainBusinessIntroTO;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("mainBusinessIntroApiImpl")
public class MainBusinessIntroApiImpl implements MainBusinessIntroAPI {

    @Autowired
    private MainBusinessIntroSer mainBusinessIntroSer;

    /**
     * 根据id查询主业介绍
     *
     * @param id 主业介绍唯一标识
     * @return class MainBusinessIntroBO
     * @throws SerException
     */
    @Override
    public MainBusinessIntroBO findById(String id) throws SerException {
        MainBusinessIntro model = mainBusinessIntroSer.findById(id);
        return BeanTransform.copyProperties(model, MainBusinessIntroBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 主业介绍dto
     * @throws SerException
     */
    @Override
    public Long count(MainBusinessIntroDTO dto) throws SerException {
        return mainBusinessIntroSer.count(dto);
    }

    /**
     * 分页查询主业业务
     *
     * @return class MainBusinessIntroBO
     * @throws SerException
     */
    @Override
    public List<MainBusinessIntroBO> list(MainBusinessIntroDTO dto) throws SerException {
        return mainBusinessIntroSer.list(dto);
    }

    /**
     * 保存主业业务
     *
     * @param to 主业业务to
     * @return class MainBusinessIntroBO
     * @throws SerException
     */
    @Override
    public MainBusinessIntroBO save(MainBusinessIntroTO to) throws SerException {
        return mainBusinessIntroSer.save(to);
    }

    /**
     * 根据id删除主业业务
     *
     * @param id 主业业务唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        mainBusinessIntroSer.remove(id);
    }

    /**
     * 更新主业业务
     *
     * @param to 主业业务to
     * @throws SerException
     */
    @Override
    public void update(MainBusinessIntroTO to) throws SerException {
        mainBusinessIntroSer.update(to);
    }
}