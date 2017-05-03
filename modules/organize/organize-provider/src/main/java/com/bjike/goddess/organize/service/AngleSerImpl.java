package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.dto.AngleDTO;
import com.bjike.goddess.organize.entity.Angle;
import com.bjike.goddess.organize.to.AngleTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角度业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class AngleSerImpl extends ServiceImpl<Angle, AngleDTO> implements AngleSer {

    @Autowired
    private PositionInstructionSer positionInstructionSer;

    @Override
    public List<AngleBO> findStatus() throws SerException {
        AngleDTO dto = new AngleDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Angle> angleList = super.findByCis(dto);
        List<AngleBO> angleBOList = BeanTransform.copyProperties(angleList, AngleBO.class);
        return angleBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AngleBO saveAsTo(AngleTO to) throws SerException {
        this.checkUnique(to);
        Angle angle = BeanTransform.copyProperties(to, Angle.class, true);
        super.save(angle);
        return BeanTransform.copyProperties(angle, AngleBO.class);
    }

    /**
     * 检测角度是否重复
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(AngleTO to) throws SerException {
        AngleDTO dto = new AngleDTO();
        dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (super.findOne(dto) != null)
            throw new SerException(to.getName() + ":该角度已存在,无法保存");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateAsTo(AngleTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException();
        Angle angle = super.findById(to.getId());
        if (null == angle)
            throw new SerException("数据对象不存在");
        if (!angle.getName().equals(to.getName()))
            this.checkUnique(to);
        angle.setName(to.getName());
        angle.setDescription(to.getDescription());
        angle.setModifyTime(LocalDateTime.now());
        super.update(angle);
    }

    @Override
    public AngleBO delete(String id) throws SerException {
        Angle angle = super.findById(id);
        if (null == angle)
            throw new SerException("数据对象不存在");
        if (positionInstructionSer.findByAngle(id).size() > 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(angle);
        return BeanTransform.copyProperties(angle, AngleBO.class);
    }

    @Override
    public AngleBO close(String id) throws SerException {
        Angle angle = super.findById(id);
        if (null == angle)
            throw new SerException("数据对象不存在");
        angle.setStatus(Status.CONGEAL);
        super.update(angle);
        return BeanTransform.copyProperties(angle, AngleBO.class);
    }

    @Override
    public AngleBO open(String id) throws SerException {
        Angle angle = super.findById(id);
        if (null == angle)
            throw new SerException("数据对象不存在");
        angle.setStatus(Status.THAW);
        super.update(angle);
        return BeanTransform.copyProperties(angle, AngleBO.class);
    }

    @Override
    public List<AngleBO> maps(AngleDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), AngleBO.class);
    }
}
