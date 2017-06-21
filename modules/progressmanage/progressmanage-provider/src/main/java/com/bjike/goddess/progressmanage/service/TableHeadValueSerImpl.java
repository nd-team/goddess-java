package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.TableHeadValueBO;
import com.bjike.goddess.progressmanage.dto.TableHeadRowSignDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.entity.TableHeadRowSign;
import com.bjike.goddess.progressmanage.entity.TableHeadValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 进度表表头对应Value业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class TableHeadValueSerImpl extends ServiceImpl<TableHeadValue, TableHeadValueDTO> implements TableHeadValueSer {

    @Autowired
    private ProgressTableSer progressTableSer;
    @Autowired
    private TableHeadSer tableHeadSer;
    @Autowired
    TableHeadRowSignSer tableHeadRowSignSer;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TableHeadValueBO> pageList(TableHeadValueDTO dto) throws SerException {
        ProgressTable table = progressTableSer.findById(dto.getTableId());
        if (table != null) {

            TableHeadRowSignDTO signDTO = new TableHeadRowSignDTO();
            List<TableHeadRowSign> signList = tableHeadRowSignSer.findByCis(signDTO);

            dto.getConditions().add(Restrict.eq("tableHead.progressTable.id", dto.getTableId()));
            dto.getSorts().add("tableHeadRowSign.createTime=asc");
            dto.getSorts().add("tableHead.sortIndex=asc");

            return BeanTransform.copyProperties(super.findByPage(dto), TableHeadValueBO.class);
        } else {
            throw new SerException("非法tableId,进度表对象不能为空!");
        }
    }
    /*  @Override
    @Transactional(rollbackFor = SerException.class)
    public List<TableHeadValueBO> pageList(TableHeadValueDTO dto) throws SerException {
        ProgressTable table = progressTableSer.findById(dto.getTableId());
        if (table != null) {

            dto.getConditions().add(Restrict.eq("tableHead.progressTable.id",dto.getTableId()));
            dto.getSorts().add("tableHeadRowSign.createTime=desc");
            dto.getSorts().add("tableHead.sortIndex=asc");

            return BeanTransform.copyProperties(super.findByPage(dto),TableHeadValueBO.class);
        } else {
            throw new SerException("非法tableId,进度表对象不能为空!");
        }
    }*/


}