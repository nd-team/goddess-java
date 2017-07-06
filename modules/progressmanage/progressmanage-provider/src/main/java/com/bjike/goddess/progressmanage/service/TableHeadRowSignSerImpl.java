package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.bo.TableHeadForValueBO;
import com.bjike.goddess.progressmanage.bo.TableHeadRowSignBO;
import com.bjike.goddess.progressmanage.bo.TableHeadValueBO;
import com.bjike.goddess.progressmanage.dto.TableHeadDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadRowSignDTO;
import com.bjike.goddess.progressmanage.dto.TableHeadValueDTO;
import com.bjike.goddess.progressmanage.entity.ProgressTable;
import com.bjike.goddess.progressmanage.entity.TableHead;
import com.bjike.goddess.progressmanage.entity.TableHeadRowSign;
import com.bjike.goddess.progressmanage.entity.TableHeadValue;
import com.bjike.goddess.progressmanage.to.TableHeadRowSignTO;
import com.bjike.goddess.progressmanage.to.TableHeadValueTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 进度表表头对应值的行标记业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "progressmanageSerCache")
@Service
public class TableHeadRowSignSerImpl extends ServiceImpl<TableHeadRowSign, TableHeadRowSignDTO> implements TableHeadRowSignSer {

    @Autowired
    private ProgressTableSer progressTableSer;
    @Autowired
    private TableHeadSer tableHeadSer;
    @Autowired
    private TableHeadValueSer tableHeadValueSer;


    @Override
    public List<TableHeadForValueBO> heads(TableHeadRowSignDTO dto) throws SerException {
        ProgressTable table = progressTableSer.findById(dto.getTableId());
        if (table != null) {

            TableHeadDTO tableHeadDTO = new TableHeadDTO();
            tableHeadDTO.getConditions().add(Restrict.eq("progressTable.id", dto.getTableId()));
            tableHeadDTO.getSorts().add("sortIndex=asc");
            List<TableHead> tableHeadList = tableHeadSer.findByCis(tableHeadDTO, true);

            return BeanTransform.copyProperties(tableHeadList, TableHeadForValueBO.class);
        } else {
            throw new SerException("非法tableId,进度表对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void insertModel(TableHeadRowSignTO to) throws SerException {

        if (!CollectionUtils.isEmpty(to.getToList())) {

            ProgressTable table = progressTableSer.findById(to.getTableId());
            if (table != null) {
                TableHeadRowSign tableHeadRowSign = new TableHeadRowSign();
                tableHeadRowSign.setProgressTable(table);

                Set<TableHeadValue> tableHeadValueSet = new HashSet<TableHeadValue>();

                if (!CollectionUtils.isEmpty(to.getToList())) {
                    for (TableHeadValueTO valueTO : to.getToList()) {

                        TableHead tableHead = tableHeadSer.findById(valueTO.getTableHeadId());
                        if (tableHead != null) {
                            //判断是否必填,且是否赋值
                            if (tableHead.getRequired() && StringUtils.isEmpty(valueTO.getValue())) {
                                throw new SerException("[" + tableHead.getHeadName() + "],不能为空!");
                            }

                            TableHeadValue model = BeanTransform.copyProperties(valueTO, TableHeadValue.class);
                            model.setTableHead(tableHead);
                            model.setTableHeadRowSign(tableHeadRowSign);
                            tableHeadValueSet.add(model);
                        } else {
                            throw new SerException("非法表Id, " + valueTO.getValue() + " 值对应的表头对象不存在!");
                        }
                    }
                }

                if (!tableHeadValueSet.isEmpty()) {
                    tableHeadRowSign.setTableHeadValueSet(tableHeadValueSet);
                    super.save(tableHeadRowSign);
                } else {
                    throw new SerException("保存失败,请联系管理员!");
                }
            } else {
                throw new SerException("非法表头Id, 进度表对象不能为空!");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void updateModel(TableHeadRowSignTO to) throws SerException {
        TableHeadRowSign model = super.findById(to.getId());
        if (model != null) {
            if (!CollectionUtils.isEmpty(to.getToList())) {

                List<TableHeadValue> valueList = new ArrayList<TableHeadValue>();
                for (TableHeadValueTO valueTO : to.getToList()) {

                    TableHead tableHead = tableHeadSer.findById(valueTO.getTableHeadId());
                    if (tableHead != null) {
                        //判断是否必填,且是否赋值
                        if (tableHead.getRequired() && StringUtils.isEmpty(valueTO.getValue())) {
                            throw new SerException("[" + tableHead.getHeadName() + "],不能为空!");
                        }

                        TableHeadValueDTO valueDTO = new TableHeadValueDTO();
                        valueDTO.getConditions().add(Restrict.eq("tableHead.id", valueTO.getTableHeadId()));
                        valueDTO.getConditions().add(Restrict.eq("tableHeadRowSign.id", to.getId()));
                        TableHeadValue value = tableHeadValueSer.findOne(valueDTO);
                        BeanTransform.copyProperties(valueTO, value);
                        valueList.add(value);

                    } else {
                        throw new SerException("非法表头Id, " + valueTO.getValue() + " 值对应的表头对象不能为空!");
                    }
                }

                if (!valueList.isEmpty()) {
                    tableHeadValueSer.update(valueList);
                }
            } else {
                throw new SerException("非法表头Id, 进度表对象不能为空!");
            }
        } else {
            throw new SerException("非法Id,行对象不能为空!");
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        TableHeadRowSign model = super.findById(id);
        if (model != null) {
            super.remove(model);
        } else {
            throw new SerException("非法行Id,行对象不能为空!");
        }
    }

    @Override
    public List<TableHeadRowSignBO> pageList(TableHeadRowSignDTO dto) throws SerException {

        ProgressTable table = progressTableSer.findById(dto.getTableId());
        if (table != null) {
            dto.getConditions().add(Restrict.eq("progressTable.id", dto.getTableId()));
            dto.getSorts().add("createTime=asc");

            List<TableHeadRowSign> list = super.findByPage(dto);
            if (!CollectionUtils.isEmpty(list)) {
                //排序
                sortByHead(list);

                List<TableHeadRowSignBO> boList = new ArrayList<TableHeadRowSignBO>();

                for (TableHeadRowSign model : list) {
                    TableHeadRowSignBO bo = new TableHeadRowSignBO();
                    bo.setId(model.getId());
                    bo.setBoList(BeanTransform.copyProperties(model.getTableHeadValueList(), TableHeadValueBO.class));
                    boList.add(bo);
                }
                return boList;
            } else {
                return null;
            }
        } else {
            throw new SerException("非法tableId,进度表对象不能为空!");
        }
    }

    //按表头排序
    public List<TableHeadRowSign> sortByHead(List<TableHeadRowSign> list) {
        for (TableHeadRowSign model : list) {
            model.setTableHeadValueList(new ArrayList<>());
            model.getTableHeadValueList().addAll(model.getTableHeadValueSet());
        }
        for (TableHeadRowSign model : list) {
            Collections.sort(model.getTableHeadValueList(), new Comparator<TableHeadValue>() {

                @Override
                public int compare(TableHeadValue o1, TableHeadValue o2) {
                    return o1.getTableHead().getSortIndex().compareTo(o2.getTableHead().getSortIndex());
                }
            });
        }
        return list;
    }

}