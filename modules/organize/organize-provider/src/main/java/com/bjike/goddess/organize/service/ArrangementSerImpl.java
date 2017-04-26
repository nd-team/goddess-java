package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.dto.ArrangementDTO;
import com.bjike.goddess.organize.entity.Arrangement;
import com.bjike.goddess.organize.enums.ArrangementType;
import com.bjike.goddess.organize.to.ArrangementTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位层级业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ArrangementSerImpl extends ServiceImpl<Arrangement, ArrangementDTO> implements ArrangementSer {


    @Override
    public List<ArrangementBO> findStatus() throws SerException {
        ArrangementDTO dto = new ArrangementDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Arrangement> list = super.findByCis(dto);
        List<ArrangementBO> bos = BeanTransform.copyProperties(list, ArrangementBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArrangementBO save(ArrangementTO to) throws SerException {
        Arrangement arrangement = BeanTransform.copyProperties(to, Arrangement.class);
        arrangement.setCreateTime(LocalDateTime.now());
        arrangement.setStatus(Status.THAW);
        if (StringUtils.isNotBlank(to.getParentId()))
            arrangement.setParent(super.findById(to.getParentId()));
        this.checkLayer(arrangement, 1);
        super.save(arrangement);
        return BeanTransform.copyProperties(arrangement, ArrangementBO.class);
    }

    /**
     * 检测层次
     *
     * @param entity 岗位层级实体
     * @param layer  层级数(通常情况下传入 1 )
     * @return
     * @throws SerException
     */
    private Integer checkLayer(Arrangement entity, Integer layer) throws SerException {
        if (layer > 3)
            throw new SerException("选择的上级层级已经是最底层无法添加/修改");
        if (null != entity.getParent())
            this.checkLayer(entity.getParent(), layer++);
        return layer;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArrangementBO update(ArrangementTO to) throws SerException {
        Arrangement arrangement = super.findById(to.getId());
        if (null == arrangement)
            throw new SerException("数据对象不存在");
        if (StringUtils.isNotBlank(to.getParentId())) arrangement.setParent(super.findById(to.getParentId()));
        arrangement.setArrangement(to.getArrangement());
        arrangement.setSerialNumber(to.getSerialNumber());
        arrangement.setDescription(to.getDescription());
        arrangement.setModifyTime(LocalDateTime.now());
        this.checkLayer(arrangement, 1);
        super.update(arrangement);
        return BeanTransform.copyProperties(arrangement, ArrangementBO.class);
    }

    @Override
    public List<ArrangementBO> findChild(String id) throws SerException {
        ArrangementDTO dto = new ArrangementDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        return BeanTransform.copyProperties(super.findByCis(dto), ArrangementBO.class);
    }

    @Override
    public ArrangementBO delete(String id) throws SerException {
        Arrangement arrangement = super.findById(id);
        if (null == arrangement)
            throw new SerException("数据对象不存在");
        try {
            super.remove(arrangement);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return BeanTransform.copyProperties(arrangement, ArrangementBO.class);
    }

    @Override
    public List<ArrangementBO> maps(ArrangementDTO dto) throws SerException {
        dto.getSorts().add("modifyTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), ArrangementBO.class);
    }

    @Override
    public ArrangementType getLayer(String id) throws SerException {
        if (StringUtils.isBlank(id))
            throw new SerException("数据id不能为空");
        switch (this.checkLayer(super.findById(id), 1)) {
            case 1:
                return ArrangementType.ET;
            case 2:
                return ArrangementType.MG;
            case 3:
                return ArrangementType.DM;
            default:
                return ArrangementType.ET;
        }
    }
}
