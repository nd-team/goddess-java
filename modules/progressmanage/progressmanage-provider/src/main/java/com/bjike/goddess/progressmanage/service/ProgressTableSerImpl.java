package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.ProgressTableBO;
import com.bjike.goddess.progressmanage.bo.TableListForHeadBO;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.to.ProgressTableTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 进度表业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class ProgressTableSerImpl extends ServiceImpl<ProgressTable, ProgressTableDTO> implements ProgressTableSer {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProgressTableBO insertModel(ProgressTableTO to) throws SerException {
        if (isExist(to) == null) {
            ProgressTable model = BeanTransform.copyProperties(to, ProgressTable.class, true);
            super.save(model);
            return BeanTransform.copyProperties(model, ProgressTableBO.class);
        } else {
            throw new SerException("该项目已经存在该进度表,请检查数据!");
        }

    }

    public ProgressTable isExist(ProgressTableTO to) throws SerException {
        ProgressTableDTO dto = new ProgressTableDTO();
        dto.getConditions().add(Restrict.eq("tabName", to.getTabName()));
        dto.getConditions().add(Restrict.eq("project_id", to.getProjectId()));
        dto.setLimit(1);
        return super.findOne(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProgressTableBO updateModel(ProgressTableTO to) throws SerException {
        ProgressTable model = super.findById(to.getId());
        if (model != null) {
            if (isExist(to) == null || (isExist(to) != null && to.getId().equals(model.getId()))) {
                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(model, ProgressTableBO.class);
            } else {
                throw new SerException("该项目已经存在该进度表,请检查数据!");
            }
        } else {
            throw new SerException("非法Id,编辑对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        ProgressTable model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.DELETE);
        } else {
            throw new SerException("非法Id,删除对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TableListForHeadBO> tables() throws SerException {

        ProgressTableDTO dto = new ProgressTableDTO();
        dto.getConditions().add(Restrict.ne("status", Status.DELETE));
        dto.getConditions().add(Restrict.ne("status", Status.CONGEAL));
        dto.getConditions().add(Restrict.ne("project.status", Status.DELETE));
        dto.getConditions().add(Restrict.ne("project.status", Status.CONGEAL));
        dto.getSorts().add("createTime=desc");

        List<ProgressTable> list = super.findByPage(dto);

        if (!CollectionUtils.isEmpty(list)) {
            List<TableListForHeadBO> boList = new ArrayList<TableListForHeadBO>();

            for (int i = 0; i < list.size(); i++) {
                TableListForHeadBO bo = BeanTransform.copyProperties(list.get(i), TableListForHeadBO.class);
                bo.setInProject(list.get(i).getProject().getInProject());
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    public List<ProgressTableBO> pageList(ProgressTableDTO dto) throws SerException {
        dto.getConditions().add(Restrict.ne("status", Status.DELETE));
        dto.getSorts().add("createTime=desc");

        return BeanTransform.copyProperties(super.findByPage(dto), ProgressTableBO.class);
    }
}