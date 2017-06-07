package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.bo.WarnBO;
import com.bjike.goddess.projectmarketfee.dto.WarnDTO;
import com.bjike.goddess.projectmarketfee.entity.Warn;
import com.bjike.goddess.projectmarketfee.to.WarnTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 预警设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:53 ]
 * @Description: [ 预警设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class WarnSerImpl extends ServiceImpl<Warn, WarnDTO> implements WarnSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public WarnBO save(WarnTO to) throws SerException {
        Warn warn = BeanTransform.copyProperties(to, Warn.class, true);
        super.save(warn);
        return BeanTransform.copyProperties(warn, WarnBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(WarnTO to) throws SerException {
        Warn warn = super.findById(to.getId());
        LocalDateTime a = warn.getCreateTime();
        LocalDateTime b = warn.getModifyTime();
        warn = BeanTransform.copyProperties(to, Warn.class, true);
        warn.setCreateTime(a);
        warn.setModifyTime(b);
        super.update(warn);
    }

    @Override
    public List<WarnBO> list(WarnDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Warn> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, WarnBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public WarnBO findByID(String id) throws SerException {
        Warn warn = super.findById(id);
        return BeanTransform.copyProperties(warn, WarnBO.class);
    }

    @Override
    public WarnBO countNum(WarnDTO dto) throws SerException {
        WarnBO bo = new WarnBO();
        bo.setNum(super.count(dto));
        return bo;
    }
}