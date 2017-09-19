package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ReflectBO;
import com.bjike.goddess.organize.dto.ReflectDTO;
import com.bjike.goddess.organize.entity.Reflect;
import com.bjike.goddess.organize.to.ReflectTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 体现类型操作业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class ReflectSerImpl extends ServiceImpl<Reflect, ReflectDTO> implements ReflectSer {

    @Autowired
    private PositionInstructionSer positionInstructionSer;
    @Autowired
    private InstructionClassifySer instructionClassifySer;

    private ReflectBO transform(Reflect entity) throws SerException {
        ReflectBO bo = BeanTransform.copyProperties(entity, ReflectBO.class);
        bo.setClassifyId(entity.getClassify().getId());
        if (null != entity.getClassify()) {
            bo.setClassifyName(entity.getClassify().getName());
        }
        return bo;
    }

    private List<ReflectBO> transformList(List<Reflect> list) throws SerException {
        List<ReflectBO> bos = new ArrayList<>(0);
        for (Reflect entity : list)
            bos.add(this.transform(entity));
        return bos;
    }

    @Override
    public List<ReflectBO> findStatus() throws SerException {
        ReflectDTO dto = new ReflectDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        return this.transformList(super.findByPage(dto));
    }

    /**
     * 检测是否有重复体现类型
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(ReflectTO to) throws SerException {
        ReflectDTO dto = new ReflectDTO();
        dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (super.findOne(dto) != null)
            throw new SerException(to.getName() + ":该体现类型已存在,无法保存");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReflectBO save(ReflectTO to) throws SerException {
        this.checkUnique(to);
        Reflect reflect = BeanTransform.copyProperties(to, Reflect.class);
        reflect.setClassify(instructionClassifySer.findById(to.getClassifyId()));
        if (null == reflect.getClassify())
            throw new SerException("选择分类不存在,无法保存");
        reflect.setStatus(Status.THAW);
        super.save(reflect);
        return this.transform(reflect);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReflectBO update(ReflectTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        Reflect entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (!entity.getName().equals(to.getName()))
            this.checkUnique(to);
        BeanTransform.copyProperties(to, entity, true);
        entity.setClassify(instructionClassifySer.findById(to.getClassifyId()));
        if (null == entity.getClassify())
            throw new SerException("选择分类不存在,无法保存");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public ReflectBO delete(String id) throws SerException {
        Reflect entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (positionInstructionSer.findByReflect(id).size() > 0)
            throw new SerException("此处已被引用,无法删除");
        super.remove(entity);
        return this.transform(entity);
    }

    @Override
    public ReflectBO close(String id) throws SerException {
        Reflect entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public ReflectBO open(String id) throws SerException {
        Reflect entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transform(entity);
    }

    @Override
    public List<ReflectBO> maps(ReflectDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return this.transformList(super.findByPage(dto));
    }

    @Override
    public ReflectBO getById(String id) throws SerException {
        Reflect entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        return this.transform(entity);
    }

    @Override
    public List<ReflectBO> findByClassify(String classifyId) throws SerException {
        if (StringUtils.isBlank(classifyId))
            return new ArrayList<>(0);
        ReflectDTO dto = new ReflectDTO();
        dto.getConditions().add(Restrict.eq("classify.id", classifyId));
        List<Reflect> list = super.findByCis(dto);
        return this.transformList(list);
    }
}
