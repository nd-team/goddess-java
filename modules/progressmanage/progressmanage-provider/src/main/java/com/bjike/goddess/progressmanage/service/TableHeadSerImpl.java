package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.TableHeadBO;
import com.bjike.goddess.progressmanage.dto.TableHeadDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.entity.TableHead;
import com.bjike.goddess.progressmanage.to.TableHeadTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 进度表表头业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class TableHeadSerImpl extends ServiceImpl<TableHead, TableHeadDTO> implements TableHeadSer {

    @Autowired
    private ProgressTableSer progressTableSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public TableHeadBO insertModel(TableHeadTO to) throws SerException {

        ProgressTable table = progressTableSer.findById(to.getTableId());
        if (table != null) {

            if ((isExistHeadName(to) != null)) {
                throw new SerException("该进度表已经存在该表头名字段，请检查数据!");
            }
            if ((isExistSortIndex(to) != null)) {
                throw new SerException("该进度表已经存在该顺序字段，请检查数据!");
            }
            TableHead model = BeanTransform.copyProperties(to, TableHead.class, true);
            model.setProgressTable(table);
            super.save(model);
            return BeanTransform.copyProperties(model, TableHeadBO.class);
        } else {
            throw new SerException("非法tableId, 进度表对象不存在!");
        }
    }

    public TableHead isExistHeadName(TableHeadTO to) throws SerException {
        TableHeadDTO dto = new TableHeadDTO();
        dto.getConditions().add(Restrict.eq("progressTable.id", to.getTableId()));
        dto.getConditions().add(Restrict.eq("headName", to.getHeadName()));
        dto.setLimit(1);
        return super.findOne(dto);
    }

    public TableHead isExistSortIndex(TableHeadTO to) throws SerException {
        TableHeadDTO dto = new TableHeadDTO();
        dto.getConditions().add(Restrict.eq("progressTable.id", to.getTableId()));
        dto.getConditions().add(Restrict.eq("sortIndex", to.getSortIndex()));
        dto.setLimit(1);
        return super.findOne(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public TableHeadBO editModel(TableHeadTO to) throws SerException {
        TableHead model = super.findById(to.getId());
        if (model != null) {
            TableHead judgeName = isExistHeadName(to);
            TableHead judgeIndex = isExistSortIndex(to);
            if (judgeName == null || (judgeName != null && to.getId().equals(judgeName.getId()))) {

            } else {
                throw new SerException("该进度表已经存在该表头名字段，请检查数据!");
            }
            if (judgeIndex == null || (judgeIndex != null && to.getId().equals(judgeIndex.getId()))) {

            } else {
                throw new SerException("该进度表已经存在该顺序字段，请检查数据!");
            }

            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
            return BeanTransform.copyProperties(model, TableHeadBO.class);

        } else {
            throw new SerException("非法Id,编辑对象不存在!");
        }
    }

    @Override
    public List<TableHeadBO> pageList(TableHeadDTO dto) throws SerException {
        if(!StringUtils.isEmpty(dto.getTableId())){
            dto.getSorts().add("sortIndex=asc");
            dto.getConditions().add(Restrict.eq("progressTable.id",dto.getTableId()));
            return BeanTransform.copyProperties(super.findByPage(dto), TableHeadBO.class);
        }else{
            throw new SerException("进度表Id不能为空!");
        }

    }
}